package com.yun.serviceImpl;

import com.yun.dao.CategoryDao;
import com.yun.dao.ItemDao;
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

    @Resource
    private ItemDao itemDao;
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
            List<Long> subCategoriesID = categoryDao.retrieveCategoriesIDBySupID(categoryID);//得到所有子类ID
            if (subCategoriesID==null||subCategoriesID.isEmpty()){//当分类为叶子分类时，可以直接删除。
                //删除类目为categoryID 下所有对象
                itemDao.deleteItemsByCategoryID(categoryID);
            }else{//当不为叶子分类时，递归到子分类
                for (Long subCategoryID : subCategoriesID) {
                    deleteCategoryByID(subCategoryID);//递归删除
                }
            }
            //删除类目
            categoryDao.deleteCategoryByID(categoryID);
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
