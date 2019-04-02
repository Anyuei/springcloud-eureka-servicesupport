package com.yun.serviceImpl;

import com.yun.dao.ItemDao;
import com.yun.entity.Comment;
import com.yun.entity.Item;
import com.yun.service.ItemService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: ItemServiceImpl
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/2 20:42
 */
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemDao itemDao;

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
}
