package com.yun.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @version : V1.0
 * @ClassName: Comment 评论实体类 对应库表 comments
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/2 15:21
 */
public class Comment implements Serializable {
    private Long commentID;//评论ID
    private Integer userID;//评论对应用户ID
    private User user;//评论对应用户ID
    private Long objectID;//评论对应被评论对象ID
    private String stars;//评价星级
    private String content;//评价内容
    private String imagesPath;//评论图片路径
    private String likes;//支持数
    private String opposition;//反对数
    private String realNameSupport;//实名支持数
    private String realNameOpposition;//实名反对数
    private String state;//评论状态
    private Date commentTime;//评论时间
    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", userID=" + userID +
                ", user=" + user +
                ", objectID=" + objectID +
                ", stars='" + stars + '\'' +
                ", content='" + content + '\'' +
                ", imagesPath='" + imagesPath + '\'' +
                ", likes='" + likes + '\'' +
                ", opposition='" + opposition + '\'' +
                ", realNameSupport='" + realNameSupport + '\'' +
                ", realNameOpposition='" + realNameOpposition + '\'' +
                ", state='" + state + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Long getObjectID() {
        return objectID;
    }

    public void setObjectID(Long objectID) {
        this.objectID = objectID;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getOpposition() {
        return opposition;
    }

    public void setOpposition(String opposition) {
        this.opposition = opposition;
    }

    public String getRealNameSupport() {
        return realNameSupport;
    }

    public void setRealNameSupport(String realNameSupport) {
        this.realNameSupport = realNameSupport;
    }

    public String getRealNameOpposition() {
        return realNameOpposition;
    }

    public void setRealNameOpposition(String realNameOpposition) {
        this.realNameOpposition = realNameOpposition;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
