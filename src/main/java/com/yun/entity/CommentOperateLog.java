package com.yun.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @version : V1.0
 * @ClassName: CommentOperate 评论操作记录实体类 对应库表 commentOperate
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/5/11 18:37
 */
public class CommentOperateLog implements Serializable {
    private Long operateID;
    private Date operateTime;
    private Integer commonOperateType;
    private Integer realnameOperateType;
    private Integer operateUserID;
    private Long commentID;
    private Long objectID;
    public CommentOperateLog() {
    }

    public CommentOperateLog(Long operateID, Date operateTime, Integer commonOperateType, Integer realnameOperateType, Integer operateUserID, Long commentID, Long objectID) {
        this.operateID = operateID;
        this.operateTime = operateTime;
        this.commonOperateType = commonOperateType;
        this.realnameOperateType = realnameOperateType;
        this.operateUserID = operateUserID;
        this.commentID = commentID;
        this.objectID = objectID;
    }

    @Override
    public String toString() {
        return "CommentOperateLog{" +
                "operateID=" + operateID +
                ", operateTime=" + operateTime +
                ", commonOperateType=" + commonOperateType +
                ", realnameOperateType=" + realnameOperateType +
                ", operateUserID=" + operateUserID +
                ", commentID=" + commentID +
                ", objectID=" + objectID +
                '}';
    }

    public Long getOperateID() {
        return operateID;
    }

    public void setOperateID(Long operateID) {
        this.operateID = operateID;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
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

    public Integer getOperateUserID() {
        return operateUserID;
    }

    public void setOperateUserID(Integer operateUserID) {
        this.operateUserID = operateUserID;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public Long getObjectID() {
        return objectID;
    }

    public void setObjectID(Long objectID) {
        this.objectID = objectID;
    }
}
