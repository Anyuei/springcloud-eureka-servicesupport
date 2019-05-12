package com.yun.restController;

import com.yun.entity.Category;
import com.yun.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: CategoryController
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/3/25 16:35
 */
@Controller
@RequestMapping("/categoryRestful")
public class RestfulCategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 添加类目
     * @param category
     * @return
     */
    @RequestMapping("/add")
    public String addCategory(Category category){
        categoryService.insertCategory(category);
        return "ok";
    }

    /**
     * 删除类目
     * @param categoryID
     * @return
     */
    @RequestMapping("/delete")
    public String deleteCategory(Long categoryID){
        categoryService.deleteCategoryByID(categoryID);
        return "ok";
    }

    /**
     * 查找子类目
     * @param supCategoryID
     * @return
     */
    @RequestMapping("/getSubCategories")
    public @ResponseBody List<Category> getCategories(@RequestParam("supCategoryID")Long supCategoryID){
        List<Category> categoriesList =  categoryService.retrieveCategoriesBySupID(supCategoryID);
        if (categoriesList==null||categoriesList.size()==0){
            return null;
        }
        return categoriesList;
    }
}
