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
}
