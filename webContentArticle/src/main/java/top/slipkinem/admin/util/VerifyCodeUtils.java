package top.slipkinem.admin.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by slipkinem on 2017/4/10.
 */
public class VerifyCodeUtils {
    // 验证码所用字符的集合
    private static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static Random random = new Random();

    /**
     * 生成验证码
     * @param verifySize 验证码字符串个数
     * @return 返回生成的验证码
     */
    public static String generateVerifyCode(int verifySize) {
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 生成验证码
     * @param verifySize 大小
     * @param sources 验证码的字符串集合
     * @return 返回一个随机的size大小的验证码数字
     */
    private static String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }

        int codesLength = sources.length();

        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);

        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLength - 1)));
        }

        return verifyCode.toString();
    }

    public static String outputVerifyImage(int w, int h, OutputStream outputStream, int verifySize) {
        String verifyCode = generateVerifyCode(verifySize);
        try {
            outputImage(w, h, outputStream, verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }

    public static void outputImage(int w, int h, File outputFile, String code) {
        if (outputFile == null) return;

        File dir = outputFile.getParentFile();

        if (!dir.exists()) dir.mkdirs();

        try {
            outputFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            outputImage(w, h, fileOutputStream, code);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void outputImage(int w, int h, OutputStream outputStream, String code) throws IOException {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random rand = new Random();
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];

        Color[] colorSpaces = new Color[]{
                Color.white,
                Color.CYAN,
                Color.GRAY,
                Color.LIGHT_GRAY,
                Color.MAGENTA,
                Color.ORANGE,
                Color.PINK,
                Color.YELLOW
        };

        float[] floats = new float[colors.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            floats[i] = rand.nextFloat();
        }

        Arrays.sort(floats);

        graphics2D.setColor(Color.GRAY);
        graphics2D.fillRect(0, 0, w, h);

        Color c = getRandColor(200, 250);
        graphics2D.setColor(c);
        graphics2D.fillRect(0, 2, w, h - 4);

        Random random = new Random();
        graphics2D.setColor(getRandColor(160, 200));

        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(w - 1),
                    y = random.nextInt(w - 1),
                    x1 = random.nextInt(6) + 1,
                    y1 = random.nextInt(12) + 1;

            graphics2D.drawLine(x, y, x + x1 + 40, y + y1 + 20);
        }

        float yawpRate = 0.05f;
        int area = (int) (yawpRate * w * h);

        for (int i = 0; i < area; i++) {
            int x = random.nextInt(w),
                    y = random.nextInt(h),
                    rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        shear(graphics2D, w, h, c);

        graphics2D.setColor(getRandColor(100, 160));
        int fontSize = h - 4;
        Font font = new Font("algerian", Font.ITALIC, fontSize);

        graphics2D.setFont(font);

        char[] chars = code.toCharArray();

        for (int i = 0; i < verifySize; i++) {
            AffineTransform affineTransform = new AffineTransform();

            affineTransform.setToRotation(Math.PI / 4
                            * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
                    (w / verifySize) * i + fontSize / 2, h / 2);

            graphics2D.setTransform(affineTransform);
            graphics2D.drawChars(chars, i, 1, (w - 10) / verifySize * i + 5, h / 2 + fontSize / 2 - 10);
        }
        graphics2D.dispose();
        ImageIO.write(image, "jpg", outputStream);

    }

    /**
     * 获取随机颜色值 RGB
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);

    }

    private static int getRandomIntColor() {
        int rgb[] = getRandomRgb();
        int color = 0;
        for (int c :
                rgb) {
            color = color << 8;
            color = color | c;
        }

        return color;
    }

    private static int[] getRandomRgb() {
        int rgb[] = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics graphics, int w1, int h1, Color color) {
        int period = random.nextInt(2);
        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period)
                    + (6.2831853071795862D * (double) phase
                    / (double) frames);

            graphics.copyArea(0, i, w1, 1, (int) d, 0);

            if (borderGap) {
                graphics.setColor(color);
                graphics.drawLine((int) d, i, 0, i);
                graphics.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    private static void shearY(Graphics graphics, int w1, int h1, Color color) {
        int period = random.nextInt(40) + 10;

        boolean borderGap = true;

        int frames = 20;
        int phase = 7;

        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period)
                    + (6.2831853071795862D * (double) phase
                    / (double) frames);
            graphics.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                graphics.setColor(color);
                graphics.drawLine(i, (int) d, i, 0);
                graphics.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }

//    public static void main(String[] args) {
//        File dir = new File("C:/verifies");
//        int w = 200, h = 80;
//
//        for (int i = 0; i < 5; i++) {
//            String verifyCode = generateVerifyCode(4);
//            File file = new File(dir, verifyCode + ".jpg");
//            outputImage(w, h, file, verifyCode);
//        }
//    }

}
