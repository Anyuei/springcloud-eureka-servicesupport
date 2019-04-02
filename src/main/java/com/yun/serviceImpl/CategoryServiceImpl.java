package com.yun.serviceImpl;

import com.yun.dao.CategoryDao;
import com.yun.entity.Category;
import com.yun.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: CategoryServiceImpl
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/3/25 16:39
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    /**
     * 添加新类
     * @param category
     * @return
     */
    @Override
    public Integer insertCategory(Category category) {
        Integer integer = categoryDao.insertCategory(category);
        return integer;
    }
    /**
     * 级联删除类目，删除类目会删除旗下所有子类目，以及删除子类目下所有被评论对象，此操作请谨慎执行
     * @param categoryID
     */
    @Override
    public void deleteCategoryByID(Long categoryID) {
        if (categoryDao.retrieveCategoryByID(categoryID)!=null){
            List<Long> subCategoriesID = categoryDao.retrieveCategoriesIDBySupID(categoryID);
            if (subCategoriesID==null||subCategoriesID.isEmpty()){//当分类为叶子分类时，可以直接删除。
                categoryDao.deleteCategoryByID(categoryID);
                //删除类目下所有对象

            }
            for (Long subCategoryID : subCategoriesID) {
                deleteCategoryByID(subCategoryID);
            }
        }

    }

    @Override
    public void updateCategoryByID(Category category) {

    }

    @Override
    public Category retrieveCategoryByID(Long id) {
        return null;
    }

    @Override
    public List<Category> retrieveCategoriesByState(Integer state) {
        return null;
    }

    @Override
    public Category retrieveCategoryByName(String categoryName) {
        return null;
    }

    @Override
    public List<Category> retrieveNCategoriesByMostLikes(Integer limit) {
        return null;
    }

    @Override
    public List<Category> retrieveCategoriesByName(String categoryName) {
        return null;
    }

    /**
     * 根据父类ID查询所有子类信息
     * @param supCategoryID
     * @return
     */
    public List<Category> retrieveCategoriesBySupID(Long supCategoryID){
        return categoryDao.retrieveCategoriesBySupID(supCategoryID);
    }
}
