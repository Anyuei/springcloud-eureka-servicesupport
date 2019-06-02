package com.yun.serviceImpl;
import java.io.File;
import java.util.List;
import java.util.Map;

import com.yun.config.EmailConfig;
import com.yun.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



/**
 * @version : V1.0
 * @ClassName: EmailServiceImpl
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/6/2 16:47
 */
@Service
public class EmailServiceImpl  implements EmailService {
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String sendTo, String titel, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(titel);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments) {

    }

    @Override
    public void sendTemplateMail(String sendTo, String titel, Map<String, Object> content, List<Pair<String, File>> attachments) {

    }


}
