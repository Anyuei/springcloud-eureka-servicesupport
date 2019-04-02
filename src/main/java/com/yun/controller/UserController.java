package com.yun.controller;

import com.yun.entity.User;
import com.yun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注销并重定向登录页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        System.out.println("用户已经退出");
        request.getSession().invalidate();
        return "redirect:/user/login.html";
    }

    /**
     * 登录异步验证
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/validationLogin")
    @ResponseBody//禁用把返回值解析为路径，而直接把返回结果加到HTTP响应体中
    public String validationLogin(User user, HttpServletRequest request) {
        if (user.getUserNickname() != null && user.getUserPassword() != null) {
            User reallyUser = userService.retrieveUserByNickname(user.getUserNickname());
            if (reallyUser != null && reallyUser.getUserPassword().equals(user.getUserPassword())) {
                String userNickname = reallyUser.getUserNickname();
                System.out.println("USER:" + userNickname + " login success！["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) +"]");
                request.getSession().setAttribute(userNickname, userNickname);
                return "success";
            }
        }
        return "fail";
    }

    /**
     * 用户主界面
     * @param userNickname
     * @return
     */
    @RequestMapping("/index/{userNickname}")
    public String getMainPath(@PathVariable String userNickname,Model model,HttpServletRequest request) {
        //当会话还有用户存有，可免密再次登录
        if (request.getSession().getAttribute(userNickname)!=null&&!request.getSession().isNew()){
            User reallyUser = userService.retrieveUserByNickname(userNickname);
            if (reallyUser!=null){
                model.addAttribute("user", reallyUser);
                return "index";
            }
        }
        System.out.println("重定向登录");
        return "redirect:/user/login";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/register")
    public String registerPage() {
        return "register";
    }
    /**
     * 注册失败重定向注册页面，成功则跳转user/index
     * @return
     */
    @RequestMapping("/validationRegister")
    @ResponseBody
    public String validationRegister(User user, HttpServletRequest request) {
        String newUserNickname = user.getUserNickname();
        String newUserPassword =  user.getUserPassword();
        String newUserEmail = user.getEmail();
        if (userService.retrieveUserByNickname(newUserNickname) == null) {
            User newUser = new User();
            newUser.setUserNickname(newUserNickname);
            newUser.setUserPassword(newUserPassword);
            newUser.setEmail(newUserEmail);
            if (userService.insertUser(newUser)!=null){
                System.out.println("USER:" + newUserNickname + " register success！["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) +"]");
                return "success";
            }
        }else {
            return "nickname_exists";
        }
        return "fail";
        //识别非法用户名（此处需求）
    }
}

