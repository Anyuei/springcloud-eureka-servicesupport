package com.yun.dao;

import com.yun.entity.User;
import java.util.List;
public interface UserDao {
    /**
     * 添加新用户
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 根据ID删除用户
     * @param id
     */
    void deleteUserByID(Integer id);

    /**
     * 根据ID修改用户
     * @param id
     */
    void updateUserByID(Integer id);

    /**
     * 根据ID查用户
     * @param id
     * @return
     */
    User retrieveUserByID(Integer id);

    /**
     * 用户名精确查
     * @param name
     * @return
     */
    User retrieveUserByName(String name);

    /**
     * 用户名模糊查
     * @param name
     * @return
     */
    List<User> retrieveUsersByName(String name);

    /**
     * 昵称精确查
     * @param nickname
     * @return
     */
    User retrieveUserByNickname(String nickname);

    /**
     * 昵称模糊查
     * @param nickname
     * @return
     */
    List<User> retrieveUsersByNickname(String nickname);
}
