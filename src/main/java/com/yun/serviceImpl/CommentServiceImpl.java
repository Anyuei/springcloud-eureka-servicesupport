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
    public List<Comment> retrieveCommentsByObjectID(Long objectID) {
        //查询某对象的所有评论
        List<Comment> comments = commentDao.retrieveCommentsByObjectID(objectID);
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
    public CommentOperateLog operateComment(Integer userID ,Long commentID ,String operateType){
        Boolean isInitCommentOperate= false;
        //根据用户ID和评论ID 搜索对应用户对对应评论的操作
        CommentOperateLog commentOperateLog = commentOperateLogDao.retrieveCommentOperateByUserIDAndCommentID(commentID,userID);
        if (commentOperateLog==null){//如果用户之前没有操作过此评论，初始化评论操作记录
            Comment comment = commentDao.retrieveCommentByID(commentID);
            commentOperateLog = new CommentOperateLog(
                    null,new Date(),
                    0,
                    0,
                    userID,
                    commentID,
                    comment.getObjectID()
            );
            isInitCommentOperate=true;
        }
        Integer commonOperateType = commentOperateLog.getCommonOperateType();
        Integer realnameOperateType = commentOperateLog.getRealnameOperateType();

        Integer like_changeNum=0;
        Integer opposition_changeNum=0;
        Integer realNameSupport_changeNum=0;
        Integer realNameOpposition_changeNum=0;

        switch (operateType) {
            case "likes":
                if (commonOperateType==1){//取消喜欢
                    commonOperateType=0;
                    like_changeNum=-1;
                }else{//确认喜欢
                    if (commonOperateType==-1){
                        opposition_changeNum=-1;//对应评论反对数减一
                    }
                    commonOperateType=1;
                    like_changeNum=1;
                    if (realnameOperateType==-1){//喜欢时，把实名反对去掉（如果实名反对被激活）
                        realnameOperateType=0;
                        realNameOpposition_changeNum=-1;//对应评论实名反对数减一
                    }

                }
                break;
            case "opposition":
                if (commonOperateType==-1){//取消反对
                    commonOperateType=0;
                    opposition_changeNum=-1;//反对数减一
                }else{//确认反对
                    if (commonOperateType==1){//反对时，把喜欢去掉（如果喜欢被激活）
                        like_changeNum=-1;//喜欢数减一
                    }
                    commonOperateType=-1;
                    opposition_changeNum=1;//反对数加一
                    if (realnameOperateType==1){//反对时，把实名支持去掉（如果实名支持被激活）
                        realnameOperateType=0;
                        realNameSupport_changeNum=-1;//对应实名支持数减一
                    }
                }
                break;
            case "realNameSupport":
                if (realnameOperateType==1){//取消实名支持
                    realnameOperateType=0;
                    realNameSupport_changeNum=-1;//实名支持数减一
                }else{//确认支持
                    if (realnameOperateType==-1){//实名支持时，把实名反对去掉(如果实名反对被激活)
                        realNameOpposition_changeNum=-1;//实名反对数减一
                    }
                    realnameOperateType=1;
                    realNameSupport_changeNum=1;//实名支持数加一
                    if (commonOperateType==-1){//实名支持时，把反对去掉（如果反对被激活）
                        commonOperateType=0;
                        opposition_changeNum=-1;//反对数减一
                    }
                }
                break;
            case "realNameOpposition":
                if (realnameOperateType==-1){//取消实名反对
                    realnameOperateType=0;
                    realNameOpposition_changeNum=-1;//实名反对减一
                }else{//确认实名反对
                    if (realnameOperateType==1){//实名反对时，把实名支持去掉（如果实名支持被激活）
                        realNameSupport_changeNum=-1;
                    }
                    realnameOperateType=-1;
                    realNameOpposition_changeNum=1;//实名反对加一
                    if (commonOperateType==1){//实名反对时，把普通喜欢去掉（如果普通喜欢被激活）
                        commonOperateType=0;
                        like_changeNum=-1;
                    }
                }
                break;
        }
        //更新当前评论的信息 例如喜欢数 反对数 实名...
        operateNumOfComment(
                commentID,
                like_changeNum,
                opposition_changeNum,
                realNameSupport_changeNum,
                realNameOpposition_changeNum
        );

        //更新当前用户 对评论的操作的记录
        commentOperateLog.setCommonOperateType(commonOperateType);//更新普通评论操作
        commentOperateLog.setRealnameOperateType(realnameOperateType);//更新实名评论操作
        commentOperateLog.setOperateTime(new Date());//更新操作时间
        Integer state=0;//0 操作失败 其他数字 影响数据库记录的条数
        if (isInitCommentOperate){
            state = commentOperateLogDao.insertCommentOperate(commentOperateLog);
        }else{
            state = commentOperateLogDao.updateCommentOperate(commentOperateLog);
        }
        if (state!=null&&state==1){
            return commentOperateLog;
        }else{
            return null;
        }
    }

    /**
     * 更新某评论的喜欢数
     * @param commentID
     * @param num
     * @return
     */
    @Override
    public Integer operateLikesNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long likes = comment.getLikes();
        likes+=num;
        comment.setLikes(likes);
        return commentDao.updateCommentOperateNumByID(comment);
    }
    /**
     * 更新某评论的反对数
     * @param commentID
     * @param num
     * @return
     */
    @Override
    public Integer operateOppositionsNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long oppositions = comment.getOpposition();
        oppositions+=num;
        comment.setOpposition(oppositions);
        return commentDao.updateCommentOperateNumByID(comment);
    }
    /**
     * 更新某评论的实名支持数
     * @param commentID
     * @param num
     * @return
     */
    @Override
    public Integer operateRealNameSupportsNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long supports = comment.getRealNameSupport();
        supports+=num;
        comment.setRealNameSupport(supports);
        return commentDao.updateCommentOperateNumByID(comment);
    }
    /**
     * 更新某评论的实名反对数
     * @param commentID
     * @param num
     * @return
     */
    @Override
    public Integer operateRealNameOppositionsNumOfComment(Long commentID,Integer num) {
        Comment comment = commentDao.retrieveCommentByID(commentID);
        Long oppositions = comment.getRealNameOpposition();
        oppositions+=num;
        comment.setRealNameOpposition(oppositions);
        return commentDao.updateCommentOperateNumByID(comment);
    }

    /**
     * 更改评论个状态数数量
     * @param commentID
     * @param like_changeNum
     * @param opposition_changeNum
     * @param realNameSupport_changeNum
     * @param realNameOpposition_changeNum
     * @return
     */
    @Override
    public Integer operateNumOfComment(Long commentID,
                                       Integer like_changeNum,
                                       Integer opposition_changeNum,
                                       Integer realNameSupport_changeNum,
                                       Integer realNameOpposition_changeNum) {
        //查询对应评论的消息
        Comment comment = commentDao.retrieveCommentByID(commentID);
        //获取评论原状态数
        Long likes = comment.getLikes();
        Long oppositions = comment.getOpposition();
        Long realNameSupport = comment.getRealNameSupport();
        Long realNameOpposition = comment.getRealNameOpposition();
        //操作各状态数
        likes=likes+like_changeNum;
        oppositions=oppositions+opposition_changeNum;
        realNameSupport=realNameSupport+realNameSupport_changeNum;
        realNameOpposition=realNameOpposition+realNameOpposition_changeNum;
        //更新状态数
        comment.setLikes(likes);
        comment.setOpposition(oppositions);
        comment.setRealNameSupport(realNameSupport);
        comment.setRealNameOpposition(realNameOpposition);
        System.out.println(comment);
        return commentDao.updateCommentOperateNumByID(comment);
    }
}
