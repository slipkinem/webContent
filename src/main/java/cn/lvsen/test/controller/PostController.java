package cn.lvsen.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by slipkinem on 5/19/2017.
 */
@RequestMapping("api/post")
@Controller
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPosts(Integer currentPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPostById(@PathVariable String postId) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info(postId);
        System.out.println(postId);
        try {
            map.put("errorMessage", "请求成功");
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return map;
    }
}
