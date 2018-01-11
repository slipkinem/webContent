package top.slipkinem.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.slipkinem.admin.po.Comment;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.CommentService;
import top.slipkinem.admin.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slipkinem on 5/23/2017.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * 根据postId获取文章的留言
     *
     * @param postId 文章ID
     * @return result
     */
    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCommentsByPostId(@PathVariable Integer postId) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("postId===>" + postId);
        try {
            List<Comment> comments = commentService.getCommentsByPostId(postId);
            map.put("comments", comments);
            map.put("errorCode", 0);
            map.put("errorMessage", "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            map.put("errorMessage", e.getMessage());
            map.put("errorCode", 9);
        }
        return map;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertComment(@RequestBody Comment comment, HttpServletRequest httpRequest) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = (User) httpRequest.getSession().getAttribute("user");
            comment.setUserId(user.getUserId());
            comment.setUsername(user.getUsername());
            Integer code = commentService.insertComment(comment);
            if (code == 1) {
                map.put("errorCode", 0);
                map.put("errorMessage", "留言成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            map.put("errorMessage", e.getMessage());
            map.put("errorCode", 9);
        }
        return map;
    }
}
