package com.yun.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @version : V1.0
 * @ClassName: Like
 * @Description: 态度 实体类 喜欢状态 0-无感 1-喜欢 2-不看好
 * @Auther: Anakki
 * @Date: 2019/3/22 23:59
 */
public class Like implements Serializable {
    private Long likesID;
    private Integer userID;//用户ID
    private Long objectID;//对象ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date stateTime;//发表状态时间
    private Integer stateOfMind;//心理状态
    public Like() {
    }

    @Override
    public String toString() {
        return "Like{" +
                "likesID=" + likesID +
                ", userID=" + userID +
                ", objectID=" + objectID +
                ", stateTime=" + stateTime +
                ", stateOfMind=" + stateOfMind +
                '}';
    }

    public Like(Long likesID, Integer userID, Long objectID, Date stateTime, Integer stateOfMind) {
        this.likesID = likesID;
        this.userID = userID;
        this.objectID = objectID;
        this.stateTime = stateTime;
        this.stateOfMind = stateOfMind;
    }

    public Long getLikesID() {
        return likesID;
    }

    public void setLikesID(Long likesID) {
        this.likesID = likesID;
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

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public Integer getStateOfMind() {
        return stateOfMind;
    }

    public void setStateOfMind(Integer stateOfMind) {
        this.stateOfMind = stateOfMind;
    }
}