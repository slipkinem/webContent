package cn.lvsen.test.service;

import cn.lvsen.test.po.Comment;

import java.util.List;

/**
 * Created by slipkinem on 5/24/2017.
 */
public interface CommentService {
    List<Comment> getCommentsByPostId(Integer postId);

    Integer insertComment(Comment comment);
}
