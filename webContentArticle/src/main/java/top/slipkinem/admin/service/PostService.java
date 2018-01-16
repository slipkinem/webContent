package top.slipkinem.admin.service;


import top.slipkinem.admin.po.Post;
import top.slipkinem.common.beans.PageBean;

/**
 * Created by slipkinem on 5/19/2017.
 */
public interface PostService {
    Boolean insertPost(Post post);
    PageBean<Post> getPostsByUserId(Integer pageNum, Integer pageSize);

    Post getPostByPostId(Integer postId);
}
