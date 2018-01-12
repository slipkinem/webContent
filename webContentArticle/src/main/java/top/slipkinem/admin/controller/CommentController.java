package top.slipkinem.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.slipkinem.admin.po.Comment;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.CommentService;
import top.slipkinem.common.beans.ResultBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by slipkinem on 5/23/2017.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 根据postId获取文章的留言
     *
     * @param postId 文章ID
     * @return result
     */
    @GetMapping("/{postId}")
    public ResultBean<Collection<Comment>> getCommentsByPostId(@PathVariable Integer postId) {
        return new ResultBean<>(commentService.getCommentsByPostId(postId));
    }

    @PostMapping
    public ResultBean<Boolean> insertComment(@RequestBody Comment comment, HttpServletRequest httpRequest) {
        User user = (User) httpRequest.getSession().getAttribute("user");
        comment.setUserId(user.getUserId());
        comment.setUsername(user.getUsername());

        return new ResultBean<>(commentService.insertComment(comment));
    }
}
