package com.yun.serviceImpl;

import com.yun.dao.CommentDao;
import com.yun.entity.Comment;
import com.yun.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: CommentServiceImpl
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/24 20:37
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public Integer insertComment(Comment comment) {

        return commentDao.insertComment(comment);
    }

    @Override
    public void deleteCommentByID(Long id) {

    }

    @Override
    public void updateCommentByID(Long id) {

    }

    @Override
    public Comment retrieveCommentByID(Long id) {
        return null;
    }

    @Override
    public List<Comment> retrieveCommentsByUserID(Long userID) {
        List<Comment> comments = commentDao.retrieveCommentsByUserID(userID);
        return comments;
    }

    @Override
    public List<Comment> retrieveCommentsByObjectID(Long objectID) {
        List<Comment> comments = commentDao.retrieveCommentsByObjectID(objectID);
        return comments;
    }

    @Override
    public List<Comment> retrieveCommentsByState(Integer state) {
        return null;
    }
}
