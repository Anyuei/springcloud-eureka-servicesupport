package com.yun.service;


import com.yun.entity.Comment;
import com.yun.entity.CommentOperateLog;

import java.util.List;

public interface CommentService {
    /**
     * 添加评论表
     * @param comment
     * @return
     */
    Integer insertComment(Comment comment);

    /**
     * 根据评论ID删除评论
     * @param id
     */
    void deleteCommentByID(Long id);

    /**
     * 根据评论ID修改评论
     * @param id
     */
    void updateCommentByID(Long id);

    /**
     * 根据评论ID 查询评论
     * @param id
     * @return
     */
    Comment retrieveCommentByID(Long id);
    /**
     * 根据用户ID 查询评论 并根据字段 降序排序 获取index行往后的count条数据
     * @param userID
     * @return
     */
    List<Comment> retrieveCommentsByUserID(
            Integer userID,
            String key,
            Integer index,
            Integer count,
            String descOrAsc);
    /**
     * 根据用户ID 查询评论
     * @param userID
     * @return
     */
    List<Comment> retrieveCommentsByUserID(Integer userID);

    /**
     * 根据被评论对象ID 查询评论 当前有登录用户时，应当根据用户显示出 用户对他操作过的评论 做出不同的显示，比如点赞 反对的意见
     * @param objectID
     * @return
     */
    List<Comment> retrieveCommentsByObjectID(Long objectID,Integer userID);

    /**
     * 当无登录用户时 直接返回
     * @param objectID
     * @return
     */
    List<Comment> retrieveCommentsByObjectID(Long objectID);

    /**
     * 根据状态查询
     * @param state 评论状态 （0=审核中，1=通过，2=封禁，3=删除）
     * @return
     */
    List<Comment> retrieveCommentsByState(Integer state);

    /**
     * 根据commentID(评论ID),userID(用户ID)查询某用户对他的操作
     * @param userID
     * @param commentID
     * @return
     */
    CommentOperateLog retrieveCommentOperateLogByUserIDAndCommentID(Integer userID , Long commentID);


    /**
     * 根据用户ID 评论ID 操作类型 操作评论
     * @param userID
     * @param commentID
     * @param operateType
     * @return
     */
    CommentOperateLog operateComment(Integer userID, Long commentID , String operateType);

    /**
     * 操作评论喜欢数
     * @param commentID
     * @param num
     * @return
     */
    Integer operateLikesNumOfComment(Long commentID,Integer num);

    /**
     * 操作评论反对数
     * @param commentID
     * @param num
     * @return
     */
    Integer operateOppositionsNumOfComment(Long commentID,Integer num);

    /**
     * 操作评论实名支持数
     * @param commentID
     * @param num
     * @return
     */
    Integer operateRealNameSupportsNumOfComment(Long commentID,Integer num);

    /**
     * 操作评论实名反对数
     * @param commentID
     * @param num
     * @return
     */
    Integer operateRealNameOppositionsNumOfComment(Long commentID,Integer num);
    /**
     * 更改评论个状态数数量
     * @param commentID
     * @param like_changeNum
     * @param opposition_changeNum
     * @param realNameSupport_changeNum
     * @param realNameOpposition_changeNum
     * @return
     */
    Integer operateNumOfComment(Long commentID,
                                Integer like_changeNum,
                                Integer opposition_changeNum,
                                Integer realNameSupport_changeNum,
                                Integer realNameOpposition_changeNum);
}
