package top.slipkinem.admin.service.impl;

import top.slipkinem.admin.mapper.PostMapper;
import top.slipkinem.admin.po.Post;
import top.slipkinem.admin.po.PostExample;
import top.slipkinem.admin.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by slipkinem on 5/19/2017.
 */
@Service
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    @Autowired
    private PostMapper postMapper;

    @Override
    public Integer insertPost(Post post) {
        Date date = new Date();
        post.setCreateTime(date);
        Integer code = postMapper.insertSelective(post);
        return code;
    }

    @Override
    public List<Post> getPostsByUserId(Integer UserId) {
        List<Post> list = new ArrayList<Post>();
        PostExample postExample = new PostExample();
        postExample.createCriteria().andUserIdEqualTo(UserId);
        try {
            list = postMapper.selectByExampleWithBLOBs(postExample);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public Post getPostByPostId(Integer postId) {
        Post post = new Post();
        try {
            post = postMapper.selectByPrimaryKey(postId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Error("数据库查询错误====> " + e.getMessage());
        }
        return post;
    }
}
