package com.yun.controller;

import com.yun.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @version : V1.0
 * @ClassName: RedisController
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/5/12 2:09
 */
@Controller
@RequestMapping(value="/redis")
public class RedisController{
    @Autowired
    RedisUtils redisUtil;

    /**
     * @auther: zhangyingqi
     * @date: 16:23 2018/8/29
     * @param: []
     * @return: org.springframework.ui.ModelMap
     * @Description: 执行redis写/读/生命周期
     */
    @RequestMapping(value = "getRedis",method = RequestMethod.POST)
    @ResponseBody
    public String getRedis(){
        redisUtil.set("20182018","这是一条测试数据", 0);
        Long resExpire = redisUtil.expire("20182018", 60, 0);//设置key过期时间
        String res = redisUtil.get("20182018", 0);
        return res;
    }

}
