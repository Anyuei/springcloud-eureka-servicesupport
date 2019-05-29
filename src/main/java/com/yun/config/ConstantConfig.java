package com.yun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @version : V1.0
 * @ClassName: ConstantConfig
 * @Description: 项目常量实体类
 * @Auther: Anakki
 * @Date: 2019/5/15 1:53
 */
@Configuration
@PropertySource("classpath:constant.properties")
public class ConstantConfig {
    @Value("${MAX_COMMENTS_PER_PAGE}")
    private Integer MAX_COMMENTS_PER_PAGE;

    @Value("${limitImgSizeMB}")
    private Integer Limit_Img_Size_MB;

    private Long limit_Img_Size;

    @Value("${limitAvatarSizeMB}")
    private Integer Limit_Avatar_Size_MB;

    private Long Limit_Avatar_Size;
    //评论一次获得的经验值
    @Value("${Comment_XP}")
    private Integer Comment_XP;
    //实名评论一次获得的经验值
    @Value("${RealNameComment_XP}")
    private Integer RealNameComment_XP;
    //对评论进行点赞，不喜欢，反对等操作的经验值
    @Value("${Operate_Comment_XP}")
    private Integer Operate_Comment_XP;

    //对对象进行点赞，不喜欢等操作的经验值
    @Value("${Operate_Object_XP}")
    private Integer Operate_Object_XP;

    public Long getLimit_Img_Size() {
        return 1024L*1024*Limit_Img_Size_MB;
    }

    public void setLimit_Img_Size(Long limit_Img_Size) {
        this.limit_Img_Size = limit_Img_Size;
    }

    public Integer getMAX_COMMENTS_PER_PAGE() {
        return MAX_COMMENTS_PER_PAGE;
    }

    public void setMAX_COMMENTS_PER_PAGE(Integer MAX_COMMENTS_PER_PAGE) {
        this.MAX_COMMENTS_PER_PAGE = MAX_COMMENTS_PER_PAGE;
    }

    public Integer getLimit_Img_Size_MB() {
        return Limit_Img_Size_MB;
    }

    public void setLimit_Img_Size_MB(Integer limit_Img_Size_MB) {
        Limit_Img_Size_MB = limit_Img_Size_MB;
    }

    public Integer getLimit_Avatar_Size_MB() {
        return Limit_Avatar_Size_MB;
    }

    public void setLimit_Avatar_Size_MB(Integer limit_Avatar_Size_MB) {
        Limit_Avatar_Size_MB = limit_Avatar_Size_MB;
    }

    public Long getLimit_Avatar_Size() {
        return 1024L*1024*Limit_Avatar_Size_MB;
    }

    public void setLimit_Avatar_Size(Long limit_Avatar_Size) {
        Limit_Avatar_Size = limit_Avatar_Size;
    }

    public Integer getComment_XP() {
        return Comment_XP;
    }

    public void setComment_XP(Integer comment_XP) {
        Comment_XP = comment_XP;
    }

    public Integer getOperate_Comment_XP() {
        return Operate_Comment_XP;
    }

    public void setOperate_Comment_XP(Integer operate_Comment_XP) {
        Operate_Comment_XP = operate_Comment_XP;
    }

    public Integer getRealNameComment_XP() {
        return RealNameComment_XP;
    }

    public void setRealNameComment_XP(Integer realNameComment_XP) {
        RealNameComment_XP = realNameComment_XP;

    }

    public Integer getOperate_Object_XP() {
        return Operate_Object_XP;
    }

    public void setOperate_Object_XP(Integer operate_Object_XP) {
        Operate_Object_XP = operate_Object_XP;
    }
}
