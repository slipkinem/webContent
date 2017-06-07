package cn.lvsen.test.service.impl;

import cn.lvsen.test.mapper.CommentMapper;
import cn.lvsen.test.po.Comment;
import cn.lvsen.test.po.CommentExample;
import cn.lvsen.test.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<Comment> comments = new ArrayList<Comment>();
        CommentExample commentExample = new CommentExample();
        try {
            commentExample
                    // 生成一个sql条件语句实例
                    .createCriteria()
                    // 给选择条件里面添加一个postId
                    .andPostIdEqualTo(postId);
            comments = commentMapper.selectByExampleWithBLOBs(commentExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Integer insertComment(Comment comment) {
        Integer code;
        try {
            // insert没有值的数据会用null填充
            // insertSelective 只会添加有数据的字段，不会覆盖默认值
           code = commentMapper.insertSelective(comment);
        } catch (Exception e) {
            logger.error(e.getMessage());
            code = 9;
        }
        return code;
    }
}
