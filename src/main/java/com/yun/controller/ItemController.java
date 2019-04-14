package com.yun.controller;
import com.yun.entity.Item;
import com.yun.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    @RequestMapping("/searchByName/{itemName}")
    public @ResponseBody List<Item> searchItems(@PathVariable String itemName){
        List<Item> items = itemService.searchByName(itemName);
        if (!items.isEmpty()){
            return items;
        }else {
            return null;
        }
    }
}
