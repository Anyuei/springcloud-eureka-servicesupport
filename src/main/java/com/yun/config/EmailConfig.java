package com.yun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version : V1.0
 * @ClassName: EmailConfig
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/6/2 16:42
 */
@Component
public class EmailConfig {

    /**
     * 发件邮箱
     */
    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

}