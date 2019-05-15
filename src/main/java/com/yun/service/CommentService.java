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
     * 根据用户ID 查询评论 并根据字段 降序排序 获取index行往后的count条数据
     * @param userID
     * @return
     */
    List<Comment> retrieveCommentsByUserID(
            Integer userID,
            String key,
            Integer index,
            Integer count,
            String descOrAsc);
    /**
     * 根据用户ID 查询评论
     * @param userID
     * @return
     */
    List<Comment> retrieveCommentsByUserID(Integer userID);

    /**
     * 根据被评论对象ID 查询评论 当前有登录用户时，应当根据用户显示出 用户对他操作过的评论 做出不同的显示，比如点赞 反对的意见
     * @param objectID
     * @return
     */
    List<Comment> retrieveCommentsByObjectID(Long objectID,Integer userID);

    /**
     * 根据状态查询
     * @param state 评论状态 （0=审核中，1=通过，2=封禁，3=删除）
     * @return
     */
    List<Comment> retrieveCommentsByState(Integer state);
}
