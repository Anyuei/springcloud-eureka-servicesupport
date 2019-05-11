package com.yun.entity;

/**
 * @version : V1.0
 * @ClassName: RequestBody
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/5/11 16:17
 */
public class RequestBody {
    private String operationType;
    private String commentID;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "operationType='" + operationType + '\'' +
                ", commentID='" + commentID + '\'' +
                '}';
    }
}
