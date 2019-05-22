package com.yun.controller;

import com.yun.EurekaServiceApplication;
import com.yun.config.ConstantConfig;
import com.yun.entity.Comment;
import com.yun.entity.CommentOperateLog;
import com.yun.entity.User;
import com.yun.service.CommentService;
import com.yun.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

    @Autowired
    private ConstantConfig constantConfig;
    /**
     * 根据对象ID 查询对象所有的评论
     * @param objectID_str
     * @return
     */
    @RequestMapping("/getCommentsByObjectID")
    public @ResponseBody List<Comment> getCommentsByObjectID(@RequestParam("ObjectID")String objectID_str,HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        Integer userID = user.getUserID();
        final long objectID = Long.parseLong(objectID_str);
        final List<Comment> comments = commentService.retrieveCommentsByObjectID(objectID,userID);
        return comments;
    }

    /**
     * 用户发表评论
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/postComment",method = RequestMethod.POST,produces="text/jason;charset=UTF-8")
    public @ResponseBody String postComment(
            HttpSession session,
            @RequestParam("image") MultipartFile image,
            Comment comment){
        if(!image.isEmpty()){
            String avatarPath_absolute =EurekaServiceApplication.staticPath+"img/Comment/";
            /*获取文件后缀*/
            String originalFilename = image.getOriginalFilename();
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            /*得到保存文件名*/
            String image_uuid = UUID.randomUUID().toString();
            String imageSaveName= image_uuid+"."+fileSuffix;

            String resultString = FileUtils.uploadFile(image, avatarPath_absolute, constantConfig.getLimit_Img_Size(), imageSaveName);
            //在开发测试模式时，得到地址为：{项目根目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
            comment.setImagesPath("/img/Comment/"+imageSaveName);//路径存储为相对路径
            if (!resultString.equals("上传成功")){
                return "评论失败";
            }
        }

        //记录上传者
        User user = (User)session.getAttribute("currentUserInfo");
        comment.setUserID(user.getUserID());
        comment.setCommentTime(new Date());
        if (commentService.insertComment(comment)==0){
            return "评论失败";
        }else{
            return "评论成功";
        }

    }

    /**
     * 对评论的操作
     * @return
     */
    @RequestMapping("/operate")
    public @ResponseBody CommentOperateLog operateComment(@RequestParam("operationType") String operationType,/*操作类型*/
                                               @RequestParam("commentID") String commentID_str,/*被操作的评论的ID*/
                                               HttpSession session){
        //获取当前用户信息
        User user = (User)session.getAttribute("currentUserInfo");
        Integer userID = user.getUserID();
        //获取被操作评论的ID
        Long commentID = Long.parseLong(commentID_str);
        //根据用户ID和评论ID 搜索对应用户对对应评论的操作
        CommentOperateLog commentOperateLog = commentService.retrieveCommentOperateLogByUserIDAndCommentID(userID, commentID);
        if (commentOperateLog==null){//如果用户之前没有操作过此评论，初始化评论操作记录
            Comment comment = commentService.retrieveCommentByID(commentID);
            commentOperateLog = new CommentOperateLog(
                    null,new Date(),
                    0,
                    0,
                    userID,
                    commentID,
                    comment.getObjectID()
            );
        }
        //更新对评论的操作
        commentOperateLog = commentService.operateComment(commentOperateLog, operationType);
        if (commentOperateLog!=null){
            return commentOperateLog;//返回操作日志
        }
        return null;
    }

    /**
     * 查询当前用户的评论 根据 orderBy排序
     * @param orderBy
     * @param session
     * @return
     */
    @RequestMapping("/currentUserComments")
    public @ResponseBody List<Comment> getCurrentUserCommentsBy(@RequestParam("orderBy") String orderBy,/*排序字段*/
                                                                @RequestParam("currentPage") Integer currentPage,/*当前分页*/
                                                                @RequestParam("descOrAsc") String descOrAsc,/*排序规则*/
                                                                HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        if (user!=null&&user.getUserID()!=null){

            Integer userID = user.getUserID();

            List<Comment> comments = commentService.retrieveCommentsByUserID(
                    userID,
                    orderBy,
                    currentPage,
                    constantConfig.getMAX_COMMENTS_PER_PAGE(),
                    descOrAsc);

            return comments;
        }
        return null;
    }

    /**
     * 查询用户的评论 根据 orderBy排序
     * @param orderBy
     * @param currentPage
     * @param userID
     * @return
     */
    @RequestMapping("/userComments")
    public @ResponseBody List<Comment> getCurrentUserComments(@RequestParam("orderBy") String orderBy,/*排序字段*/
                                                              @RequestParam("currentPage") Integer currentPage,/*当前分页*/
                                                              @RequestParam("userID") Integer userID,
                                                              @RequestParam("descOrAsc") String descOrAsc/*排序规则*/
                                                              ){
            List<Comment> comments = commentService.retrieveCommentsByUserID(
                    userID,
                    orderBy,
                    currentPage,
                    constantConfig.getMAX_COMMENTS_PER_PAGE(),
                    descOrAsc);
            return comments;
    }
}
