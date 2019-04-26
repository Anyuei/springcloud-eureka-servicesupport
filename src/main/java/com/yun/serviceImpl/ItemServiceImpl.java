package com.yun.serviceImpl;

import com.sun.imageio.plugins.common.I18N;
import com.yun.dao.ItemDao;
import com.yun.dao.LikesDao;
import com.yun.entity.Item;
import com.yun.entity.Like;
import com.yun.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: ItemServiceImpl
 * @Description: 对被评论对象的相关操作
 * @Auther: Anakki
 * @Date: 2019/4/2 20:42
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemDao itemDao;
    @Resource
    private LikesDao likesDao;

    public void insertItem(Item item){

    }
    public void deleteItemByID(Long objectID){

    }
    public void deleteItemsByCategoryID(Long categoryID){

    }
    public void updateItemByID(Item item){

    }
    public Item retrieveItemByID(Long objectID){
        return itemDao.retrieveItemByID(objectID);
    }
    /**
     * 根据对象名精确查对象
     * @param objectName
     * @param userID
     * @return
     */
    public Item searchItemByName(String objectName,Long userID){
        Item item = itemDao.retrieveItemByName(objectName);
        //查询当前用户对此对象ID发表的态度状态
        Like like = likesDao.retrieveLikeByID(userID,item.getObjectID());
        //把用户的态度状态赋值给所查询的对象，前台展示需要知道被搜索对象的当前用户的态度状态
        if (like==null||like.getStateOfMind()==null){
            item.setIslike(0);
        }else{
            item.setIslike(like.getStateOfMind());
        }
        return item;
    }
    /**
     * 根据对象名模糊查对象
     * @param objectName
     * @return
     */
    public List<Item> searchItemsByName(String objectName,Long userID){
        List<Item> items = itemDao.retrieveItemsByName(objectName);
        //查询当前用户对所有类似此对象name发表的态度状态
        HashMap<Long,Integer> map = getObjectIDAndlikestateMapOfUserLikesByUserID(userID);
        //把用户的态度状态分别赋值给所查询的对象，前台展示需要知道每个对象的当前用户的态度状态
        for (Item item : items) {
            item.setIslike(map.get(item.getObjectID()));
        }
        return items;
    }
    /**
     * 根据对象的所属类目查询对象信息
     * @param categoryID 类目ID
     * @return 所有对象
     */
    public List<Item> retrieveItemsByCategoryID(Long categoryID){
        return itemDao.retrieveItemsByCategoryID(categoryID);
    }
    /**
     * 根据对象的所属类目，查询某状态对象。
     * @param state 对象状态(0-正常 1-审核 2-封禁)
     * @param categoryID 类目ID
     * @return 所有对象
     */
    public List<Item> retrieveItemsInStateByCategoryID(Long categoryID,Integer state){
        return itemDao.retrieveItemsInStateByCategoryID(categoryID,state);
    }

    /**
     * 根据用户ID获得用户所有的点赞过的对象ID集合
     * @param userID
     * @return
     */
    public HashMap<Long,Integer> getObjectIDAndlikestateMapOfUserLikesByUserID(Long userID){
        List<Like> likes = likesDao.retrieveLikesByID(userID);
        HashMap<Long,Integer> map = new HashMap<>();
        for (Like like : likes) {
            map.put(like.getObjectID(),like.getStateOfMind());
        }
        return map;
    }

    /**
     * 用户：userID对事物：objectID_str表达态度操作，likeState_str(-1:不喜欢; 0:无感; 1：喜欢;)
     * @param objectID_str
     * @param userID
     * @param likeState_str
     * @return
     */
    public String likeItem(String objectID_str, Long userID, String likeState_str){
        Long objectID = Long.parseLong(objectID_str);
        Integer likeState = Integer.parseInt(likeState_str);
        //先查询此用户对此对象的态度
        Like like = likesDao.retrieveLikeByID(userID,objectID);
        //如果用户之前未发表过态度，初始化态度
        if (like==null){
            like=new Like(null,userID,objectID,new Date(),0);
            likesDao.insertLike(like);
        }
        //如果当前与之前态度一致，直接返回
        if (like.getStateOfMind()==likeState){
            return likeState_str;
        }
        Item item =itemDao.retrieveItemByID(objectID);
        //likeState(-1:不喜欢; 0:无感; 1：喜欢;)
        if (like.getStateOfMind()==0 && likeState==1){
            item.setLikes(item.getLikes()+likeState);
        }else if (like.getStateOfMind()==0&&likeState==-1){
            item.setDislikes(item.getDislikes()-likeState);
        }else if (like.getStateOfMind()==1&&likeState==0){
            item.setLikes(item.getLikes()-1);
        }else if (like.getStateOfMind()==-1&&likeState==0){
            item.setDislikes(item.getDislikes()-1);
        }else{
            item.setLikes(item.getLikes()+likeState);
            item.setDislikes(item.getDislikes()-likeState);
        }
        //获取对象的喜欢数与不喜欢数并更新
        like.setStateTime(new Date());
        like.setStateOfMind(likeState);

        likesDao.updateLikeByID(like);
        itemDao.updateItemByID(item);

        return likeState_str;
    }
}
