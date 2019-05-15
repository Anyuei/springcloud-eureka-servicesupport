package com.yun.serviceImpl;

import com.yun.dao.CommentDao;
import com.yun.dao.CommentOperateLogDao;
import com.yun.entity.Comment;
import com.yun.entity.CommentOperateLog;
import com.yun.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
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
    @Resource
    private CommentOperateLogDao commentOperateLogDao;
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
    /**
     * 根据用户ID 查询评论 并根据字段 降序排序 获取index行往后的count条数据
     * @param userID
     * @return
     */
    @Override
    public List<Comment> retrieveCommentsByUserID(Integer userID, String key, Integer index, Integer count,String descOrAsc) {
        return commentDao.retrieveCommentsByUserID_OrderByKey_StartIndex_HaveCount(userID,key,index,count,descOrAsc);
    }

    @Override
    public List<Comment> retrieveCommentsByUserID(Integer userID) {
        List<Comment> comments = commentDao.retrieveCommentsByUserID(userID);
        return comments;
    }

    @Override
    public List<Comment> retrieveCommentsByObjectID(Long objectID,Integer userID) {
        //查询出某对象下的所有评论，某用户的对这些评论的操作信息
        List<CommentOperateLog> commentOperateLogs = commentOperateLogDao.retrieveCommentOperatesByUserIDAndObjectID(objectID, userID);
        HashMap<Long, CommentOperateLog> objectObjectHashMap = new HashMap<>();
        for (CommentOperateLog commentOperateLog : commentOperateLogs) {
            objectObjectHashMap.put(commentOperateLog.getCommentID(),commentOperateLog);
        }

        //查询某对象的所有评论
        List<Comment> comments = commentDao.retrieveCommentsByObjectID(objectID);
        for (Comment comment : comments) {
            Long commentID = comment.getCommentID();
            if (objectObjectHashMap.containsKey(commentID)){//把当前用户对当前对象的评论的操作写入
                CommentOperateLog commentOperateLog = objectObjectHashMap.get(commentID);
                comment.setCommonOperateType(commentOperateLog.getCommonOperateType());
                comment.setRealnameOperateType(commentOperateLog.getRealnameOperateType());
            }
        }
        return comments;
    }

    @Override
    public List<Comment> retrieveCommentsByState(Integer state) {
        return null;
    }
}
