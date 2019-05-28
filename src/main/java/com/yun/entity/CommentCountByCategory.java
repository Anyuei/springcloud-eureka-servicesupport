package com.yun.entity;

/**
 * @version : V1.0
 * @ClassName: CommentCountByCategory
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/5/28 22:15
 */
public class CommentCountByCategory {

    private Integer commentCountNum;
    private String categoryName;

    public CommentCountByCategory() {
    }

    public CommentCountByCategory(Integer commentCountNum, String categoryName) {
        this.commentCountNum = commentCountNum;
        this.categoryName = categoryName;
    }

    public Integer getCommentCountNum() {
        return commentCountNum;
    }

    public void setCommentCountNum(Integer commentCountNum) {
        this.commentCountNum = commentCountNum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CommentCountByCategory{" +
                "commentCountNum=" + commentCountNum +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
