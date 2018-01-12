package top.slipkinem.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.slipkinem.admin.po.Post;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.PostService;
import top.slipkinem.common.beans.PageBean;
import top.slipkinem.common.beans.ResultBean;

import javax.servlet.http.HttpSession;

/**
 * Created by slipkinem on 5/19/2017.
 */
@RequestMapping("/post")
@RestController
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    /**
     * 获取登录用户全部文章
     *
     * @param pageNum 页码
     * @param pageSize    数据量
     * @param httpSession session
     * @return map
     */
    @GetMapping
    public ResultBean<PageBean<Post>> getPosts(Integer pageNum, Integer pageSize, HttpSession httpSession) {
        Integer userId = ((User)httpSession.getAttribute("user")).getUserId();
        return new ResultBean<>(postService.getPostsByUserId(userId, pageNum, pageSize));
    }

    /**
     * 通过postId获取具体文章详情
     *
     * @param postId postId
     * @return map
     */
    @GetMapping("/{postId}")
    public ResultBean<Post> getPostById(@PathVariable Integer postId) {
        return new ResultBean<>(postService.getPostByPostId(postId));
    }

    /**
     * 新建一篇文章
     *
     * @param httpSession 获取用户登录ID
     * @param post        文章
     * @return result
     */
    @PostMapping
    public ResultBean<Boolean> createPost(HttpSession httpSession, @RequestBody Post post) {
        Integer userId = ((User) httpSession.getAttribute("user")).getUserId();
        post.setUserId(userId);
        return new ResultBean<>(postService.insertPost(post));
    }
}
