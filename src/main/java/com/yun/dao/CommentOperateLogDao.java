package com.yun.dao;

import com.yun.entity.Category;
import com.yun.entity.CommentOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作CommentOperateLog表（已完成）
 */
public interface CommentOperateLogDao {
    Integer insertCommentOperate(CommentOperateLog commentOperate);
    Integer deleteCommentOperateByID(Long operateID);
    Integer updateCommentOperate(CommentOperateLog commentOperate);
    Category retrieveCommentOperateByID(Long operateID);

    /**
     * 查询某用户对某评论的操作信息。
     * @param commentID
     * @param userID
     * @return
     */
    CommentOperateLog retrieveCommentOperateByUserIDAndCommentID(@Param("commentID")Long commentID,@Param("userID")Integer userID);
    /**
     * 查询某对象下，某用户对所有操作过的评论的操作信息。
     * @param objectID
     * @param userID
     * @return
     */
    List<CommentOperateLog> retrieveCommentOperatesByUserIDAndObjectID(@Param("objectID")Long objectID, @Param("userID")Integer userID);

    /**
     * 查询某用户对所有评论的操作信息。
     * @param userID
     * @return
     */
    List<CommentOperateLog> retrieveCommentOperatesByUserID(Integer userID);
    /**
     * 查询某评论的所有操作信息。
     * @param commentID
     * @return
     */
    List<CommentOperateLog> retrieveCommentOperatesByCommentID(Long commentID);
}
