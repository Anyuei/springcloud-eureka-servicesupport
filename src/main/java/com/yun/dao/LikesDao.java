package com.yun.dao;

import com.yun.entity.Category;
import com.yun.entity.Like;

import java.util.List;

/**
 * 操作likes表（已完成）
 */
public interface LikesDao {
    Integer insertLikes(Long userID,String likesObjectIDs);
    Integer deleteLikesByID(Long id);
    List<Like> retrieveLikesByID(Long userID);
}
