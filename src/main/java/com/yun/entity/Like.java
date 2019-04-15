package com.yun.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @version : V1.0
 * @ClassName: Like
 * @Description: 点赞信息实体类
 * @Auther: Anakki
 * @Date: 2019/3/22 23:59
 */
public class Like implements Serializable {
    private Long likesID;
    private Long userID;//点赞用户ID
    private Long objectID;//点赞用户ID
    private Date likeTime;//点赞时间
    public Like() {
    }
    @Override
    public String toString() {
        return "Like{" +
                "likesID=" + likesID +
                ", userID=" + userID +
                ", objectID=" + objectID +
                ", likeTime=" + likeTime +
                '}';
    }

    public Like(Long likesID, Long userID, Long objectID, Date likeTime) {
        this.likesID = likesID;
        this.userID = userID;
        this.objectID = objectID;
        this.likeTime = likeTime;
    }

    public Long getLikesID() {
        return likesID;
    }

    public void setLikesID(Long likesID) {
        this.likesID = likesID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getObjectID() {
        return objectID;
    }

    public void setObjectID(Long objectID) {
        this.objectID = objectID;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}