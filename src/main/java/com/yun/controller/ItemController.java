package com.yun.controller;
import com.yun.entity.Item;
import com.yun.entity.User;
import com.yun.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: ItemController
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/14 23:23
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    @Resource
    private ItemService itemService;

    /**
     * 处理搜索被评论对象请求（模糊查询）
     * @param itemName
     * @param session
     * @return
     */
    @RequestMapping(value = "/searchByName")
    public @ResponseBody List<Item> searchItems(@RequestParam("itemKey") String itemName, HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        System.out.println("搜索对象->"+itemName+"（关键词）");
        List<Item> items=null;
        if (user!=null){
            items = itemService.searchItemsByName(itemName,user.getUserID());
        }else{
            items = itemService.searchItemsByName(itemName,null);
        }
        if (!items.isEmpty()){
            return items;
        }else {
            return null;
        }
    }

    /**
     * 处理用户对 对象的 态度状态 请求
     * @param objectID
     * @param session
     * @return
     */
    @RequestMapping("/like")
    public @ResponseBody String likeItem(@RequestParam("objectID")String objectID,
                                         @RequestParam("likeState")String likeState,
                                         HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        return itemService.likeItem(objectID,user.getUserID(),likeState);
    }

    /**
     *
     * @param categoryID
     * @param session
     * @return
     */
    @RequestMapping("/searchByCategoryID")
    public @ResponseBody List<Item> searchItemsByCategoryID(@RequestParam("category")Long categoryID,
                                         HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        List<Item> items=null;
        if (user!=null){
            items = itemService.retrieveItemsInStateByCategoryID(categoryID,user.getUserID(),0);
        }else{
            items = itemService.retrieveItemsInStateByCategoryID(categoryID,null,0);
        }
        for (Item item : items) {
            System.out.println("=="+item.getObjectName());
        }
        return items;
    }

    /**
     *
     * @param categoryName
     * @param session
     * @return
     */
    @RequestMapping("/searchByCategoryName")
    public @ResponseBody List<Item> searchItemsByCategoryName(@RequestParam("category")String categoryName,
                                                            HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        List<Item> items=null;
        if (user!=null){
            items = itemService.searchItemsInStateByCategoryName(categoryName,user.getUserID(),0);
        }else{
            items = itemService.searchItemsInStateByCategoryName(categoryName,null,0);
        }
        for (Item item : items) {
            System.out.println("=="+item.getObjectName());
        }
        return items;
    }
}
