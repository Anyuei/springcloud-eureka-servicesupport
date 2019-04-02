package com.yun.dao;

import com.yun.entity.Comment;
import java.util.List;

/**
 * 操作comment表Dao
 */
public interface CommentDao {
    Integer insertComment(Comment category);
    void deleteCommentByID(Long id);
    void updateCommentByID(Long id);
    Comment retrieveCommentByID(Long id);

    /**
     * 根据状态查询
     * @param state (0-审核 1-通过 2-驳回 3-封禁)
     * @return
     */
    List<Comment> retrieveCommentsByState(Integer state);

    /**
     * 根据类目名查询
     * @param categoryName
     * @return
     */
    Comment retrieveCommentByName(String categoryName);

    /**
     * 根据最受欢迎的n个类目查询
     * @param limit 最受欢迎前n个类目
     * @return
     */
    List<Comment> retrieveNCommentsByMostLikes(Integer limit);

    /**
     * 根据类目名模糊查
     * @param categoryName
     * @return
     */
    List<Comment> retrieveCommentsByName(String categoryName);


    List<Comment> retrieveCommentsBySupID(Long supCategoryID);

}
