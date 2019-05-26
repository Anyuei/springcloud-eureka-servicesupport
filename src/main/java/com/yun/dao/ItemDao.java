package com.yun.dao;

import com.yun.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDao {
    void insertItem(Item item);
    void deleteItemByID(Long objectID);
    void deleteItemsByCategoryID(Long categoryID);
    void updateItemByID(Item item);
    Item retrieveItemByID(Long objectID);

    /**
     * 根据对象名模糊查对象
     * @param objectName
     * @return
     */
    Item retrieveItemByName(String objectName);
    /**
     * 根据对象名模糊查对象
     * @param objectName
     * @return
     */
    List<Item> retrieveItemsByName(String objectName);
    /**
     * 根据对象ID模糊查对象
     * @param objectID
     * @return
     */
    List<Item> retrieveItemsByID(Long objectID);
    /**
     * 根据对象的所属类目查询对象信息
     * @param categoryID 类目ID
     * @return 所有对象
     */
    List<Item> retrieveItemsByCategoryID(Long categoryID);
    /**
     * 根据对象的所属类目查询对象信息
     * @param categoryName 类目名
     * @return 所有对象
     */
    List<Item> retrieveItemsByCategoryName(@Param("categoryName") String categoryName, @Param("state") Integer state);
    /**
     * 根据对象的所属类目，查询某状态对象。
     * @param state 对象状态(0-正常 1-审核 2-封禁)
     * @param categoryID 类目ID
     * @return 所有对象
     */
    List<Item> retrieveItemsInStateByCategoryID(@Param("categoryID") Long categoryID, @Param("state") Integer state);



}
