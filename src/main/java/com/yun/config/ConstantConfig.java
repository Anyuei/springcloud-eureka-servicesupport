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

    public Integer getMAX_COMMENTS_PER_PAGE() {
        return MAX_COMMENTS_PER_PAGE;
    }

    public void setMAX_COMMENTS_PER_PAGE(Integer MAX_COMMENTS_PER_PAGE) {
        this.MAX_COMMENTS_PER_PAGE = MAX_COMMENTS_PER_PAGE;
    }
}
