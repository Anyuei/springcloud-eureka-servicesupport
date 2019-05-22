package com.yun.dao;

import com.yun.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作comment表Dao
 */
public interface CommentDao {
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
     * @param comment
     */
    Integer updateCommentOperateNumByID(Comment comment);

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
    List<Comment> retrieveCommentsByUserID(Integer userID);
    /**
     * 根据用户ID 查询评论 并根据字段 降序排序 获取index行往后的count条数据
     * @param userID
     * @return
     */
    List<Comment> retrieveCommentsByUserID_OrderByKey_StartIndex_HaveCount(
            @Param("userID")Integer userID,
            @Param("key") String key,
            @Param("index")Integer index,
            @Param("count")Integer count,
            @Param("descOrAsc")String descOrAsc);
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
