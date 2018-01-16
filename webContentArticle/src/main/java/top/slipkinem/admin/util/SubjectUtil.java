package top.slipkinem.admin.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SubjectUtil {
    public static void setSession (Object key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Session session = subject.getSession();

            if (session != null) {
                session.setAttribute(key, value);
            }
        }
    }

    public static void getSession (Object key) {
        Subject subject = SecurityUtils.getSubject();

        if (subject != null) {
            Session session = subject.getSession();

            if (session != null) {
                session.getAttribute(key);
            }
        }
    }
}
