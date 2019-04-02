package com.yun.entity;

import java.io.Serializable;

/**
 * @version : V1.0
 * @ClassName: ManagerDao
 * @Description: 管理员实体类
 * @Auther: Anakki
 * @Date: 2019/3/24 0:24
 */
public class Manager implements Serializable {
    private Integer managerID;
    private String managerName;
    private String managerPassword;
    private String managerNickname;
    private Integer userID;
    private Integer level;
    private Boolean gender;
    private Integer state;
    private Integer xp;
    private String signature;
    public Manager() {
    }
    public Manager(Integer managerID, String managerName, String managerPassword, String managerNickname, Integer userID, Integer level, Boolean gender, Integer state, Integer xp, String signature) {
        this.managerID = managerID;
        this.managerName = managerName;
        this.managerPassword = managerPassword;
        this.managerNickname = managerNickname;
        this.userID = userID;
        this.level = level;
        this.gender = gender;
        this.state = state;
        this.xp = xp;
        this.signature = signature;
    }
    public Integer getManagerID() {
        return managerID;
    }

    public void setManagerID(Integer managerID) {
        this.managerID = managerID;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerNickname() {
        return managerNickname;
    }

    public void setManagerNickname(String managerNickname) {
        this.managerNickname = managerNickname;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
