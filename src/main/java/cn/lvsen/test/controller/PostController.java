package cn.lvsen.test.controller;

import cn.lvsen.test.po.Post;
import cn.lvsen.test.po.User;
import cn.lvsen.test.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slipkinem on 5/19/2017.
 */
@RequestMapping("/post")
@Controller
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    /**
     * 获取登录用户全部文章
     *
     * @param currentPage 页码
     * @param pageSize    数据量
     * @param httpSession session
     * @return map
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPosts(Integer currentPage, Integer pageSize, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Post> list = new ArrayList<Post>();
        try {
            Integer userId = ((User) httpSession.getAttribute("user")).getUserId();
            list = postService.getPostsByUserId(userId);
            map.put("posts", list);
            map.put("errorMessage", "查询成功");
            map.put("errorCode", 0);
        } catch (Exception e) {
            logger.error(e.getMessage());
            map.put("errorMessage", e.getMessage());
            map.put("errorCode", 9);
        }
        return map;
    }

    /**
     * 通过postId获取具体文章详情
     *
     * @param postId postId
     * @return map
     */
    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPostById(@PathVariable Integer postId) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info(String.valueOf(postId));
        try {
            Post post = postService.getPostByPostId(postId);
            map.put("errorMessage", "请求成功");
            map.put("errorCode", 0);
            map.put("post", post);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            map.put("errorCode", 9);
            map.put("errorMessage", e.getMessage());
        }
        return map;
    }

    /**
     * 新建一篇文章
     *
     * @param httpSession 获取用户登录ID
     * @param post        文章
     * @return result
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createPost(HttpSession httpSession, @RequestBody Post post) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info(post.toString());
        try {
            Integer userId = ((User) httpSession.getAttribute("user")).getUserId();
            logger.info("userId====>" + userId.toString());
            post.setUserId(userId);
            postService.insertPost(post);
            map.put("errorCode", 0);
            map.put("errorMessage", "添加成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            map.put("errorCode", 9);
            map.put("errorMessage", "服务器错误");
        }
        return map;
    }
}
