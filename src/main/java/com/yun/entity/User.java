package com.yun.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    Integer userID;
    String userName;
    String userNickname;
    String userPassword;
    String avatarPath="/img/defaultImages/defaultAvatar♂.jpg";//头像路径

    boolean gender;
    Integer xp;
    String telephone;
    String email;
    Integer state;//用户状态(0-正常 1-禁言 2-封号 )
    String signature;//个人说明
    Integer realnameCommentNum;//实名评论数
    public User() {
    }
    public User(Integer userID,
                String userName,
                String userNickname,
                String userPassword,
                String avatarPath,
                boolean gender,
                Integer xp,
                String telephone,
                String email,
                Integer state,
                String signature,
                Integer realnameCommentNum
    ) {
        this.userID = userID;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.avatarPath = avatarPath;
        this.gender = gender;
        this.xp = xp;
        this.telephone = telephone;
        this.email = email;
        this.state = state;
        this.signature = signature;
        this.realnameCommentNum=realnameCommentNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, userNickname, userPassword, avatarPath, gender, xp, telephone, email, state, signature,realnameCommentNum);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", gender=" + gender +
                ", xp=" + xp +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                ", signature='" + signature + '\'' +
                ", realnameCommentNum=" + realnameCommentNum +
                '}';
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer addXP(Integer number) {
        this.xp += number;
        System.out.println(this.xp);
        return xp;
    }

    public Integer getRealnameCommentNum() {
        return realnameCommentNum;
    }

    public void setRealnameCommentNum(Integer realnameCommentNum) {
        this.realnameCommentNum = realnameCommentNum;
    }
}
