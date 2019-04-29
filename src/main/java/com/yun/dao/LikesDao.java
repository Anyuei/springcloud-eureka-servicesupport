package com.yun.dao;

import com.yun.entity.Category;
import com.yun.entity.Like;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 操作likes表（已完成）
 */
public interface LikesDao {
    Integer insertLike(Like like);
    Integer deleteLikeByID(@Param("userID") Long userID,@Param("objectID")Long objectID);
    List<Like> retrieveLikesByID(Integer userID);
    Integer updateLikeByID(Like like);

    /**
     * 查询某用户某点赞记录
     * @param userID
     * @param objectID
     * @return
     */
    Like retrieveLikeByID(@Param("userID") Integer userID,@Param("objectID")Long objectID);
}
