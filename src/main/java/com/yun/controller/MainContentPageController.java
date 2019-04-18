package com.yun.controller;

import com.yun.entity.Item;
import com.yun.entity.User;
import com.yun.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
/**
 * @version : V1.0
 * @ClassName: MainContentPageController
 * @Description: 被评论对象的 主要展示页面 相关请求控制器。访问路径：域名/world/对象名
 * @Auther: Anakki
 * @Date: 2019/4/18 15:11
 */
@Controller
@RequestMapping("/world")
public class MainContentPageController {

    @Resource
    private ItemService itemService;

    @RequestMapping("/{itemName}")
    public Item initPage(@PathVariable String itemName, HttpSession session, Model model){
        User user = (User)session.getAttribute("currentUserInfo");
        Item item = itemService.searchItemByName(itemName,user.getUserID());
        return item;
    }
}
