package com.yun.controller;

import com.yun.EurekaServiceApplication;
import com.yun.config.ConstantConfig;
import com.yun.entity.CommentCountByCategory;
import com.yun.entity.User;
import com.yun.service.EmailService;
import com.yun.service.UserService;
import com.yun.utils.FileUtils;
import com.yun.utils.MD5Util;
import com.yun.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
class UserController {

    @Autowired
    private ConstantConfig constantConfig;
    @Autowired
    private UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private EmailService emailService;
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
     * @param session
     * @return
     */
    @RequestMapping("/validationLogin")
    @ResponseBody//禁用把返回值解析为路径，而直接把返回结果加到HTTP响应体中
    public String validationLogin(User user,HttpSession session) {
        final String userPassword = user.getUserPassword();
        if (user.getUserNickname() != null && userPassword != null) {
            System.out.println("用户名->"+user.getUserNickname()+"希望登录");
            User reallyUser = userService.retrieveUserByNickname(user.getUserNickname());

            final String passwordMD5 = MD5Util.getStringMD5(userPassword);//MD5加密
            if (reallyUser != null && reallyUser.getUserPassword().equals(passwordMD5)) {
                String userNickname = reallyUser.getUserNickname();
                System.out.println("USER:" + userNickname + " login success！["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) +"]");
                session.setAttribute(userNickname, userNickname);
                //把用户信息存入会话
                session.setAttribute("currentUserInfo",reallyUser);
                return "success";
            }
        }
        return "fail";
    }

    /**
     * 用户主界面
     * @param userNickname
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/index/{userNickname}")
    public String getMainPath(@PathVariable String userNickname,Model model,HttpSession session) {
        //当会话还有用户存有，可免密再次登录
        if (session.getAttribute(userNickname)!=null&&!session.isNew()){
            User reallyUser = userService.retrieveUserByNickname(userNickname);
            if (reallyUser!=null){
                model.addAttribute("user", reallyUser);
                reallyUser.setUserPassword(null);

                String avatarPath = reallyUser.getAvatarPath();
                if (avatarPath==null||avatarPath.equals("")){
                    reallyUser.setAvatarPath("/img/defaultImages/defaultAvatar♂.jpg");
                }
                //把用户信息存入会话
                session.setAttribute("currentUserInfo",reallyUser);
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
    public String validationRegister(User user) {
        String newUserNickname = user.getUserNickname();
        String newUserPassword =  user.getUserPassword();
        String newUserEmail = user.getEmail();
        if (userService.retrieveUserByNickname(newUserNickname) == null) {
            User newUser = new User();
            newUser.setUserNickname(newUserNickname);
            String passwordMD5 = MD5Util.getStringMD5(newUserPassword);
            newUser.setUserPassword(passwordMD5);
            newUser.setEmail(newUserEmail);
            if (userService.insertUser(newUser)!=null){
                System.out.println("USER:" + newUserNickname + " register success！["+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) +"]");
                return "success";
            }
        }else {
            return "nickname_exists";
        }
        return "fail";
        //识别非法用户名（此处需求还需要完成）
    }
    /**
     * 检测会话情况
     * @return
     */
    @RequestMapping("/state")
    public @ResponseBody String checkSession(HttpSession session) {
        if (session.getAttribute("currentUserInfo")==null){
            return "false";
        }
        return "true";
    }

    /**
     * 上传用户头像
     * @return
     */
    @RequestMapping("/uploadAvatar")
    public @ResponseBody String uploadAvatar(@RequestParam("file") MultipartFile file,HttpSession session){

        String avatarPath_absolute =EurekaServiceApplication.staticPath+"img/Avatar/";
        /*获取文件后缀*/
        String originalFilename = file.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        /*得到保存文件名*/
        String image_uuid = UUID.randomUUID().toString();
        String imageSaveName= image_uuid+"."+fileSuffix;

        String resultString = FileUtils.uploadFile(file, avatarPath_absolute,constantConfig.getLimit_Avatar_Size(), imageSaveName);
        if (!resultString.equals("上传成功")){
            return resultString;
        }

        User user = (User)session.getAttribute("currentUserInfo");
        user.setAvatarPath("/img/Avatar/"+imageSaveName);
        userService.updateUserByID(user);
        return "上传成功";
    }

    /**
     * 查询用户排名
     * @param startIndex
     * @param count
     * @return
     */
    @RequestMapping("/XP_Ranking")
    public @ResponseBody List<User> getXPRankingOfUsers(
            @RequestParam("currentPageNum") Integer startIndex,
            @RequestParam("count")Integer count){
        List<User> users = userService.retrieveUsersByXP(startIndex, count);
        for (User user : users) {
            user.setUserPassword("");
        }
        return users;
    }

    /**
     * 用户的个人主页
     * @param userNickname 用于查询用户的昵称
     * @param model
     * @return
     */
    @RequestMapping("/mainPage/{userNickname}")
    public String getXPRankingOfUsers(
            @PathVariable String userNickname,
            Model model){
            User user = userService.retrieveUserByNickname(userNickname);
            if (user!=null){
                user.setUserPassword(null);
                model.addAttribute("user", user);
            }
            return "personalMainPage";
    }

    /**
     * 获取当前用户 在各邻域（分类下）评论的数量
     * @return
     */
    @RequestMapping("/commentRatio")
    public @ResponseBody List<CommentCountByCategory> getCommentRatio(
            @RequestParam("userID") Integer userID){
        List<CommentCountByCategory> commentCountByCategories
                =userService.retrieveCommentCountByUserID(userID);
        return commentCountByCategories;
    }
    /**
     * 用户密码找回
     * @return
     */
    @RequestMapping("/forgotPassword")
    public @ResponseBody String forgotPassword(
            @RequestParam("email") String email,HttpServletRequest request){
        final User user = userService.retrieveUserByEmail(email);
        if (user==null){
            return "fail";
        }else{
            final UUID uuid = UUID.randomUUID();
            String uuid_str = uuid.toString();
            redisUtil.setKV(uuid_str,email,600);//10分钟有效
            request.getRequestURI();
            emailService.sendSimpleMail(
                    email,
                    "评价网用户名密码找回",
                    "用户名："+user.getUserNickname()+"请访问此链接修改密码："+request.getRequestURL()+"/"+uuid_str);
            return "success";
        }
    }
    /**
     * 跳转密码重置页面
     * @return
     */
    @RequestMapping("/forgotPassword/{uuid}")
    public String forgotPasswordPage(
            @PathVariable String uuid,
            Model model){
        if (uuid==null){
            return "error";
        }
        String email = redisUtil.getValueByKey(uuid);
        if (email==null){
            return "error";
        }
        User user = userService.retrieveUserByEmail(email);
        if (user==null){
            return "error";
        }else{
            user.setUserPassword(null);
            redisUtil.deleteKey(uuid);
            model.addAttribute("user", user);
            return "resetPassword";
        }

    }
    /**
     * 用户密码重置
     * @return
     */
    @RequestMapping("/resetPassword")
    public @ResponseBody String resetPassword(User user){
        String userNickname = user.getUserNickname();
        String userPassword = user.getUserPassword();
        if (user==null||user.getUserNickname().equals("")||user.getUserPassword().equals("")){
            return "fail";
        }else{
            User realUser = userService.retrieveUserByNickname(userNickname);
            if (realUser!=null){
                realUser.setUserPassword(MD5Util.getStringMD5(userPassword));
                userService.updateUserByID(realUser);
                return "success";
            }else{
                return "fail";
            }
        }
    }
}

