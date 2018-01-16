package top.slipkinem.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.slipkinem.admin.mapper.PostMapper;
import top.slipkinem.admin.po.Post;
import top.slipkinem.admin.po.PostExample;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.PostService;
import top.slipkinem.admin.util.SubjectUtil;
import top.slipkinem.common.beans.PageBean;
import top.slipkinem.common.excepitons.UnloginException;

import java.util.Date;
import java.util.List;

import static top.slipkinem.common.utils.CheckUtil.notNull;

/**
 * Created by slipkinem on 5/19/2017.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public Boolean insertPost(Post post) {
        User user = (User) SubjectUtil.getSession("user");
        post.setUserId(user != null ? user.getUserId() : null);

        notNull(post, "param.is.null");
        notNull(post.getUserId(), "param.is.null");

        post.setCreateTime(new Date());
        return postMapper.insertSelective(post) > 0;
    }

    @Override
    public PageBean<Post> getPostsByUserId(Integer pageNum, Integer pageSize) {
        User user = (User) SubjectUtil.getSession("user");

        if (user == null) {
            throw new UnloginException("登录已经过期，请重新登录");
        }

        Integer userId = user.getUserId();

        notNull(pageNum, "param.is.null");
        notNull(pageSize, "param.is.null");

        PageHelper.startPage(pageNum, pageSize);
        PostExample postExample = new PostExample();
        postExample.createCriteria().andUserIdEqualTo(userId);

        List<Post> list = postMapper.selectByExampleWithBLOBs(postExample);
        PageInfo<Post> pageInfo = new PageInfo<>(list);
        return new PageBean<>(pageInfo);
    }

    @Override
    public Post getPostByPostId(Integer postId) {
        notNull(postId, "param.is.null");

        Post post = postMapper.selectByPrimaryKey(postId);

        notNull(postId, "param.is.null");
        return post;
    }
}
