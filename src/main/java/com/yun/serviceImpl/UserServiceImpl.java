package com.yun.serviceImpl;

import com.yun.dao.CategoryDao;
import com.yun.dao.UserDao;
import com.yun.entity.Category;
import com.yun.entity.CommentCountByCategory;
import com.yun.entity.User;
import com.yun.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private CategoryDao categoryDao;
    @Override
    public User insertUser(User user) {
        userDao.insertUser(user);
        return userDao.retrieveUserByNickname(user.getUserNickname());
    }

    @Override
    public void deleteUserByID(Integer id) {

    }

    @Override
    public void updateUserByID(User user) {
        userDao.updateUserByID(user);
    }

    @Override
    public User retrieveUserByID(Integer id) {
        return userDao.retrieveUserByID(id);
    }

    @Override
    public User retrieveUserByName(String name) {
        return userDao.retrieveUserByName(name);
    }
    @Override
    public List<User> retrieveUsersByName(String name) {
        return userDao.retrieveUsersByName(name);
    }

    @Override
    public User retrieveUserByEmail(String email) {
        return userDao.retrieveUserByEmail(email);
    }

    @Override
    public User retrieveUserByNickname(String nickname) {
        return userDao.retrieveUserByNickname(nickname);
    }
    @Override
    public List<User> retrieveUsersByNickname(String nickname) {
        return userDao.retrieveUsersByNickname(nickname);
    }

    @Override
    public List<User> retrieveUsersByXP(Integer startIndex, Integer count) {
        return userDao.retrieveUsersByXP(startIndex,count);
    }
    @Override
    public List<CommentCountByCategory> retrieveCommentCountByUserID(Integer userID){
        return categoryDao.retrieveCommentCountByUserID(userID);
    }
}
