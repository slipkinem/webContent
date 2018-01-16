package top.slipkinem.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.slipkinem.admin.mapper.CommentMapper;
import top.slipkinem.admin.po.Comment;
import top.slipkinem.admin.po.CommentExample;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.CommentService;
import top.slipkinem.admin.util.SubjectUtil;

import java.util.List;

import static top.slipkinem.common.utils.CheckUtil.notNull;

/**
 * Created by slipkinem on 5/24/2017.
 */
@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        notNull(postId, "param.is.null");
        List<Comment> comments;
        CommentExample commentExample = new CommentExample();

        commentExample
                // 生成一个sql条件语句实例
                .createCriteria()
                // 给选择条件里面添加一个postId
                .andPostIdEqualTo(postId);
        comments = commentMapper.selectByExampleWithBLOBs(commentExample);

        return comments;
    }

    @Override
    public Boolean insertComment(Comment comment) {
        User user = (User) SubjectUtil.getSession("user");
        notNull(user, "param.is.null");
        comment.setUserId(user.getUserId());
        comment.setUsername(user.getUsername());
        return commentMapper.insertSelective(comment) > 0;
    }
}
