package com.yun.restController;

import com.yun.EurekaServiceApplication;
import com.yun.entity.User;
import com.yun.service.UserService;
import com.yun.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/userRestful")
class RestfulUserController {

    private Integer limitAvatarSizeMB=2;//单位MB
    private Long limitAvatarSize=1024L*1024*limitAvatarSizeMB;//2MB

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
     * 获取用户信息
     * @param userNickname
     * @return
     */
    @RequestMapping("/getUserByNickname")
    public User validationLogin(@RequestParam("userNickname")String userNickname) {
        User reallyUser=null;
        if (userNickname != null) {
            reallyUser = userService.retrieveUserByNickname(userNickname);
            reallyUser.setUserPassword("");
        }
        return reallyUser;
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

        String resultString = FileUtils.uploadFile(file, avatarPath_absolute, limitAvatarSize, imageSaveName);
        if (!resultString.equals("上传成功")){
            return resultString;
        }

        User user = (User)session.getAttribute("currentUserInfo");
        user.setAvatarPath("/img/Avatar/"+imageSaveName);
        userService.updateUserByID(user);
        return "上传成功";
    }
}

