package com.yun.serviceImpl;

import com.yun.dao.ItemDao;
import com.yun.dao.LikesDao;
import com.yun.entity.Item;
import com.yun.entity.Like;
import com.yun.service.ItemService;
import com.yun.utils.SessionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: ItemServiceImpl
 * @Description:
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
     * 根据对象名模糊查对象
     * @param objectName
     * @return
     */
    public List<Item> searchByName(String objectName,Long userID){
        List<Item> items = itemDao.retrieveItemsByName(objectName);
        //查询当前用户点赞对象ID集合
        List<Long> objectIDs = getObjectIDsOfUserLikesByUserID(userID);
        for (Item item : items) {
            //如果用户点赞对象ID集合包含当前对象ID，则把当前对象被点赞状态设为true，该属性将会被前端用来设置前端对象的点赞状态
            if (objectIDs.contains(item.getObjectID())){
                item.setIslike(true);
            }
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
    public List<Long> getObjectIDsOfUserLikesByUserID(Long userID){
        List<Like> likes = likesDao.retrieveLikesByID(userID);
        List<Long> objectIDs=new ArrayList<>();
        for (Like like : likes) {
            objectIDs.add(like.getObjectID());
        }
        return objectIDs;
    }
}
