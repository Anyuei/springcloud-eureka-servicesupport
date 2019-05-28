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
    private User user;//评论对应用户
    private Long objectID;//评论对应被评论对象ID
    private String stars="5";//评价星级
    private String content;//评价内容
    private String imagesPath="/";//评论图片路径
    private Long likes=0L;//支持数
    private Long opposition=0L;//反对数
    private Long realNameSupport=0L;//实名支持数
    private Long realNameOpposition=0L;//实名反对数
    private String state="1";//评论状态
    private Date commentTime;//评论时间
    private Boolean realnameState;//实名状态
    private Integer commonOperateType;//当前用户对此条评论普通操作类型
    private Integer realnameOperateType;//当前用户对此条评论实名操作类型
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
                ", likes=" + likes +
                ", opposition=" + opposition +
                ", realNameSupport=" + realNameSupport +
                ", realNameOpposition=" + realNameOpposition +
                ", state='" + state + '\'' +
                ", commentTime=" + commentTime +
                ", realnameState=" + realnameState +
                ", commonOperateType=" + commonOperateType +
                ", realnameOperateType=" + realnameOperateType +
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getOpposition() {
        return opposition;
    }

    public void setOpposition(Long opposition) {
        this.opposition = opposition;
    }

    public Long getRealNameSupport() {
        return realNameSupport;
    }

    public void setRealNameSupport(Long realNameSupport) {
        this.realNameSupport = realNameSupport;
    }

    public Long getRealNameOpposition() {
        return realNameOpposition;
    }

    public void setRealNameOpposition(Long realNameOpposition) {
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

    public Integer getCommonOperateType() {
        return commonOperateType;
    }

    public void setCommonOperateType(Integer commonOperateType) {
        this.commonOperateType = commonOperateType;
    }

    public Integer getRealnameOperateType() {
        return realnameOperateType;
    }

    public void setRealnameOperateType(Integer realnameOperateType) {
        this.realnameOperateType = realnameOperateType;
    }

    public Boolean getRealnameState() {
        return realnameState;
    }

    public void setRealnameState(Boolean realnameState) {
        this.realnameState = realnameState;
    }
}
