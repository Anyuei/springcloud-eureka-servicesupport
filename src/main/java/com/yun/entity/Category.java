package com.yun.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @version : V1.0
 * @ClassName: Category
 * @Description: 评价种类实体类
 * @Auther: Anakki
 * @Date: 2019/3/22 23:59
 */
public class Category implements Serializable {
    private Long categoryID;//类目ID
    private String categoryName;//类目名
    private Integer state;//类目状态(0-审核 1-通过 2-驳回 3-封禁)
    private Long subCategoryID;//子类目
    private Long supCategoryID;//父类目
    private String userID;//类目创建者ID
    private Date createTime;//创建时间
    private String likes;//分类热度点赞数
    private String views;//分类浏览量

    public Category() {
    }

    public Category(Long categoryID, String categoryName, Integer state, Long subCategoryID, Long supCategoryID, String userID, Date createTime, String likes, String views) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.state = state;
        this.subCategoryID = subCategoryID;
        this.supCategoryID = supCategoryID;
        this.userID = userID;
        this.createTime = createTime;
        this.likes = likes;
        this.views = views;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", state=" + state +
                ", subCategoryID=" + subCategoryID +
                ", supCategoryID=" + supCategoryID +
                ", userID='" + userID + '\'' +
                ", createTime=" + createTime +
                ", likes='" + likes + '\'' +
                ", views='" + views + '\'' +
                '}';
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(Long subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public Long getSupCategoryID() {
        return supCategoryID;
    }

    public void setSupCategoryID(Long supCategoryID) {
        this.supCategoryID = supCategoryID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
