package com.yun.service;


import com.yun.entity.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 添加评论表
     * @param comment
     * @return
     */
    Integer insertComment(Comment comment);

    /**
     * 根据评论ID删除评论
     * @param id
     */
    void deleteCommentByID(Long id);

    /**
     * 根据评论ID修改评论
     * @param id
     */
    void updateCommentByID(Long id);

    /**
     * 根据评论ID 查询评论
     * @param id
     * @return
     */
    Comment retrieveCommentByID(Long id);

    /**
     * 根据用户ID 查询评论
     * @param userID
     * @return
     */
    List<Comment> retrieveCommentsByUserID(Long userID);

    /**
     * 根据被评论对象ID 查询评论
     * @param objectID
     * @return
     */
    List<Comment> retrieveCommentsByObjectID(Long objectID);

    /**
     * 根据状态查询
     * @param state 评论状态 （0=审核中，1=通过，2=封禁，3=删除）
     * @return
     */
    List<Comment> retrieveCommentsByState(Integer state);
}
