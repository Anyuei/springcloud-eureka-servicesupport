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
    @RequestMapping("/searchByName/{itemName}")
    public @ResponseBody List<Item> searchItems(@PathVariable String itemName, HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        List<Item> items = itemService.searchByName(itemName,user.getUserID());
        if (!items.isEmpty()){
            return items;
        }else {
            return null;
        }
    }

    /**
     * 处理点赞请求
     * @param objectID
     * @param session
     * @return
     */
    @RequestMapping("/like")
    public @ResponseBody String likeItem(@RequestParam("objectID")String objectID,
                                         @RequestParam("likeState")String likeState,
                                         HttpSession session){
        User user = (User)session.getAttribute("currentUserInfo");
        System.out.println("55---"+user);
        System.out.println("56---"+objectID);
        return itemService.likeItem(objectID,user.getUserID(),likeState);
    }
}
