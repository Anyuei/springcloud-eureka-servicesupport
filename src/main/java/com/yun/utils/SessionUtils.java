package com.yun.utils;

import com.yun.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @version : V1.0
 * @ClassName: SessionUtils
 * @Description: 会话相关操作
 * @Auther: Anakki
 * @Date: 2019/4/15 18:09
 */
public class SessionUtils {
    public static User getCurrentUserInfo(HttpSession session){
        return (User)session.getAttribute("currentUserInfo");
    }
}
