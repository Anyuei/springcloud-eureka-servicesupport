package com.yun.dao;

import com.yun.entity.Category;

import java.util.List;

/**
 * 操作category表（已完成）
 */
public interface CategoryDao {
    Integer insertCategory(Category category);
    Integer deleteCategoryByID(Long id);
    Integer updateCategoryByID(Category category);
    Category retrieveCategoryByID(Long id);

    /**
     * 根据状态查询
     * @param state (0-审核 1-通过 2-驳回 3-封禁)
     * @return
     */
    List<Category> retrieveCategoriesByState(Integer state);

    /**
     * 根据类目名查询
     * @param categoryName
     * @return
     */
    Category retrieveCategoryByName(String categoryName);

    /**
     * 根据最受欢迎的n个类目查询
     * @param limit 最受欢迎前n个类目
     * @return
     */
    List<Category> retrieveNCategoriesByMostLikes(Integer limit);

    /**
     * 根据类目名模糊查
     * @param categoryName
     * @return
     */
    List<Category> retrieveCategoriesByName(String categoryName);

    /**
     * 根据父类ID查询所有子类信息
     * @param supCategoryID
     * @return
     */
    List<Category> retrieveCategoriesBySupID(Long supCategoryID);
    /**
     * 根据父类ID查询所有子类ID
     * @param supCategoryID
     * @return
     */
    List<Long> retrieveCategoriesIDBySupID(Long supCategoryID);
}
