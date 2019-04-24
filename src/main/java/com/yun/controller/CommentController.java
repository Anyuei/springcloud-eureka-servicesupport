package com.yun.controller;

import com.yun.entity.Comment;
import com.yun.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: CommentController
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/24 19:19
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @RequestMapping("/getCommentsByObjectID")
    public @ResponseBody List<Comment> getCommentsByObjectID(@RequestParam("ObjectID")String objectID_str){
        final long objectID = Long.parseLong(objectID_str);

        final List<Comment> comments = commentService.retrieveCommentsByObjectID(objectID);
        for (Comment comment : comments) {
            System.out.println(comment);
        }
        return comments;
    }
}
