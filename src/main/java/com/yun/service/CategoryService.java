package com.yun.service;


import com.yun.entity.Category;


import java.util.List;

/**
 * @version : V1.0
 * @ClassName: CategoryService
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/3/25 16:36
 */
public interface CategoryService {
    Integer insertCategory(Category category);
    void deleteCategoryByID(Long id);
    void updateCategoryByID(Category category);
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
}
