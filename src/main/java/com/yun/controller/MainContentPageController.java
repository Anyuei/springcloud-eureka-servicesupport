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

    /**
     * 初始化对象 主页面
     * @param itemName
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/{itemName}")
    public String initPage(@PathVariable String itemName, HttpSession session, Model model){
        User user = (User)session.getAttribute("currentUserInfo");
        Item item = null;
        /*允许未登录访问*/
        if (user!=null){
            item = itemService.searchItemByName(itemName,user.getUserID());
        }else{
            item = itemService.searchItemByName(itemName);
        }
        /*增加浏览次数*/
        String viewTimes = item.getViewTimes();
        item.setViewTimes(Integer.parseInt(viewTimes)+1+"");
        itemService.updateItemViewTimesByID(item);
        /*从图片路径分割出封面路径*/
        String[] img_paths = item.getObjectPicturePath().split(",");
        item.setObjectPicturePath(img_paths[0]);
        /*数据交给freemarker渲染*/
        model.addAttribute("user", user);
        model.addAttribute("item",item);//对象信息
        model.addAttribute("img_paths",img_paths);//轮播图
        model.addAttribute("img_paths_length",img_paths.length);//轮播图数量
        return "objectMainContentPage";
    }
}
