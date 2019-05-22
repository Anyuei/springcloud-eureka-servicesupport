package com.yun.serviceImpl;

import com.yun.dao.CommentDao;
import com.yun.dao.CommentOperateLogDao;
import com.yun.entity.Comment;
import com.yun.entity.CommentOperateLog;
import com.yun.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
        return commentDao.retrieveCommentByID(id);
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

    @Override
    public CommentOperateLog retrieveCommentOperateLogByUserIDAndCommentID(Integer userID ,Long commentID) {
        return commentOperateLogDao.retrieveCommentOperateByUserIDAndCommentID(commentID,userID);
    }
    @Override
    public CommentOperateLog operateComment(CommentOperateLog commentOperateLog ,String operateType){
        Integer commonOperateType = commentOperateLog.getCommonOperateType();
        Integer realnameOperateType = commentOperateLog.getRealnameOperateType();

        switch (operateType) {
            case "likes":
                if (commonOperateType==1){//取消喜欢
                    commonOperateType=0;
                }else{//确认喜欢
                    commonOperateType=1;
                    if (realnameOperateType==-1){//喜欢时，把实名反对去掉（如果实名反对被激活）
                        realnameOperateType=0;
                    }
                }
                break;
            case "opposition":
                if (commonOperateType==-1){//取消反对
                    commonOperateType=0;
                }else{//确认反对
                    commonOperateType=-1;
                    if (realnameOperateType==1){//反对时，把实名支持去掉（如果实名支持被激活）
                        realnameOperateType=0;
                    }
                }
                break;
            case "realNameSupport":
                if (realnameOperateType==1){//取消实名支持
                    realnameOperateType=0;
                }else{//确认支持
                    realnameOperateType=1;
                    if (commonOperateType==-1){//实名支持时，把反对去掉（如果实名支持被激活）
                        commonOperateType=0;
                    }
                }
                break;
            case "realNameOpposition":
                if (realnameOperateType==-1){//取消反对
                    realnameOperateType=0;
                }else{//确认反对
                    realnameOperateType=-1;
                    if (commonOperateType==1){//实名反对时，把普通喜欢去掉（如果普通喜欢被激活）
                        commonOperateType=0;
                    }
                }
                break;
        }
        commentOperateLog.setCommonOperateType(commonOperateType);//更新普通评论操作
        commentOperateLog.setRealnameOperateType(realnameOperateType);//更新实名评论操作
        commentOperateLog.setOperateTime(new Date());//更新操作时间
        Integer state = commentOperateLogDao.insertCommentOperate(commentOperateLog);
        if (state!=null&&state==1){
            return commentOperateLog;
        }else{
            return null;
        }
    }

    @Override
    public Integer operateLikesNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long likes = comment.getLikes();
        likes+=num;
        comment.setLikes(likes);
        return commentDao.updateCommentOperateNumByID(comment);
    }

    @Override
    public Integer operateOppositionsNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long oppositions = comment.getOpposition();
        oppositions+=num;
        comment.setOpposition(oppositions);
        return commentDao.updateCommentOperateNumByID(comment);
    }

    @Override
    public Integer operateRealNameSupportsNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long supports = comment.getRealNameSupport();
        supports+=num;
        comment.setRealNameSupport(supports);
        return commentDao.updateCommentOperateNumByID(comment);
    }

    @Override
    public Integer operateRealNameOppositionsNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long oppositions = comment.getRealNameOpposition();
        oppositions+=num;
        comment.setRealNameOpposition(oppositions);
        return commentDao.updateCommentOperateNumByID(comment);
    }

}
