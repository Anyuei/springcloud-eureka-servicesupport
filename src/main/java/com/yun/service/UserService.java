package com.yun.service;

import com.yun.entity.CommentCountByCategory;
import com.yun.entity.User;

import java.util.List;

public interface UserService {
    User insertUser(User user);
    void deleteUserByID(Integer id);
    void updateUserByID(User user);
    User retrieveUserByID(Integer id);
    User retrieveUserByName(String name);
    List<User> retrieveUsersByName(String name);
    User retrieveUserByNickname(String nickname);
    List<User> retrieveUsersByNickname(String nickname);
    /**
     * 根据用户经验值排序,并取对应分页的数据。
     * @param startIndex 查询结果取数据位置
     * @param count 查询数量
     * @return
     */
    List<User> retrieveUsersByXP(Integer startIndex,Integer count);

    List<CommentCountByCategory> retrieveCommentCountByUserID(Integer userID);
}
