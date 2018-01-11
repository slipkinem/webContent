package top.slipkinem.admin.service;


import top.slipkinem.admin.po.Post;

import java.util.List;

/**
 * Created by slipkinem on 5/19/2017.
 */
public interface PostService {
    Integer insertPost(Post post);
    List<Post> getPostsByUserId(Integer userId);

    Post getPostByPostId(Integer postId);
}
