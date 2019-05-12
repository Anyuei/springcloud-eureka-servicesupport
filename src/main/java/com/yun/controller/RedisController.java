package com.yun.controller;

import com.yun.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    RedisUtil redisUtil;

    @RequestMapping(value = "setKV",method = RequestMethod.POST)
    @ResponseBody
    public String setRedisKV(String key,String value){
        final String state = redisUtil.setKV(key, value);
        return state;
    }

    @RequestMapping(value = "getValueByKey",method = RequestMethod.GET)
    @ResponseBody
    public String getRedisValueByKey(@RequestParam("key") String key){
        String res = redisUtil.getValueByKey(key);
        return res;
    }
}
