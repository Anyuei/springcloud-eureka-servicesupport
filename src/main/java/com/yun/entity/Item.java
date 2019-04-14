package com.yun.entity;

import java.util.Date;

/**
 * @version : V1.0
 * @ClassName: Item
 * @Description: 被评价对象实体类 对应库表Object
 * @Auther: Anakki
 * @Date: 2019/3/22 23:59
 */
public class Item {
    private Long objectID;
    private String objectName;
    private String details;
    private String dislikes;
    private String likes;
    private String viewTimes;
    private String commentsTimes;
    private Long categoryID;
    private String createUser;
    private Date createTime;
    private Integer state;
    private String objectPicturePath;
    private String brief_introduction;
    public Item() {
    }

    public Item(Long objectID, String objectName, String details, String dislikes, String likes, String viewTimes, String commentsTimes, Long categoryID, String createUser, Date createTime, Integer state, String objectPicturePath, String brief_introduction) {
        this.objectID = objectID;
        this.objectName = objectName;
        this.details = details;
        this.dislikes = dislikes;
        this.likes = likes;
        this.viewTimes = viewTimes;
        this.commentsTimes = commentsTimes;
        this.categoryID = categoryID;
        this.createUser = createUser;
        this.createTime = createTime;
        this.state = state;
        this.objectPicturePath = objectPicturePath;
        this.brief_introduction = brief_introduction;
    }

    @Override
    public String toString() {
        return "Item{" +
                "objectID=" + objectID +
                ", objectName='" + objectName + '\'' +
                ", details='" + details + '\'' +
                ", dislikes='" + dislikes + '\'' +
                ", likes='" + likes + '\'' +
                ", viewTimes='" + viewTimes + '\'' +
                ", commentsTimes='" + commentsTimes + '\'' +
                ", categoryID=" + categoryID +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                ", objectPicturePath='" + objectPicturePath + '\'' +
                ", brief_introduction='" + brief_introduction + '\'' +
                '}';
    }

    public Long getObjectID() {
        return objectID;
    }

    public void setObjectID(Long objectID) {
        this.objectID = objectID;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(String viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String getCommentsTimes() {
        return commentsTimes;
    }

    public void setCommentsTimes(String commentsTimes) {
        this.commentsTimes = commentsTimes;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getObjectPicturePath() {
        return objectPicturePath;
    }

    public void setObjectPicturePath(String objectPicturePath) {
        this.objectPicturePath = objectPicturePath;
    }

    public String getBrief_introduction() {
        return brief_introduction;
    }

    public void setBrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction;
    }
}
