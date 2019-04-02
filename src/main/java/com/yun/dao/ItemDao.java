package com.yun.dao;

import com.yun.entity.Item;

import java.util.List;

public interface ItemDao {
    void insertItem(Item category);
    void deleteItemByID(Long id);
    void updateItemByID(Item category);
    Item retrieveItemByID(Long id);
    /**
     * 根据对象的所属类目查询对象信息
     * @param categoryID 类目ID
     * @return 所有对象
     */
    List<Item> retrieveItemsByCategoryID(Long categoryID);
    /**
     * 根据对象的所属类目，查询某状态对象。
     * @param state 对象状态(0-正常 1-审核 2-封禁)
     * @param categoryID 类目ID
     * @return 所有对象
     */
    List<Item> retrieveItemsInStateByCategoryID(Long categoryID,Integer state);

    /**
     * 根据类目名查询
     * @param itemName
     * @return
     */
    Item retrieveItemByName(String itemName);
}
