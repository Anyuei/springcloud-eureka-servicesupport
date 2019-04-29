package com.yun.controller;

import com.yun.EurekaServiceApplication;
import com.yun.entity.Comment;
import com.yun.entity.User;
import com.yun.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    /**
     * 根据对象ID 查询对象所有的评论
     * @param objectID_str
     * @return
     */
    @RequestMapping("/getCommentsByObjectID")
    public @ResponseBody List<Comment> getCommentsByObjectID(@RequestParam("ObjectID")String objectID_str){
        final long objectID = Long.parseLong(objectID_str);
        final List<Comment> comments = commentService.retrieveCommentsByObjectID(objectID);
        return comments;
    }

    /**
     * 用户发表评论
     * @param file
     * @param comment
     * @return
     */
    @RequestMapping(value = "/postComment",method = RequestMethod.POST,produces="text/jason;charset=UTF-8")
    public @ResponseBody String postComment(
            HttpSession session,
            @RequestParam("image") MultipartFile image,
            Comment comment){
        long imageSize = image.getSize();
        String originalFilename = image.getOriginalFilename();
        User user = (User)session.getAttribute("currentUserInfo");
        File path= null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            //路径获取失败
            System.out.println("路径获取失败");
            e.printStackTrace();
        }
        if(!path.exists()){
            path=new File("");
        }
        File upload=new File(EurekaServiceApplication.uploadPath);
        if(!upload.exists()){
            upload.mkdirs();
        }
        System.out.println(upload.getAbsolutePath());
        String image_uuid = UUID.randomUUID().toString();

        //在开发测试模式时，得到地址为：{项目根目录}/target/static/images/upload/
        //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
        comment.setImagesPath(EurekaServiceApplication.uploadPath+"image_uuid");
        comment.setUserID(user.getUserID());
        comment.setCommentTime(new Date());
        System.out.println(comment);
        if (commentService.insertComment(comment)==0){
            return "评论失败";
        }else{
            return "评论成功";
        }

    }
}
