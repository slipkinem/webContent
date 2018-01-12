package top.slipkinem.admin.service;

import top.slipkinem.admin.po.Comment;

import java.util.List;

/**
 * Created by slipkinem on 5/24/2017.
 */
public interface CommentService {
    List<Comment> getCommentsByPostId(Integer postId);

    Boolean insertComment(Comment comment);
}
