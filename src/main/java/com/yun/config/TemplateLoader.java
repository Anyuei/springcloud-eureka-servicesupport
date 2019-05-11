package com.yun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @version : V1.0
 * @ClassName: TemplateLoader
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/29 20:07
 */
@Configuration
public class TemplateLoader extends WebMvcConfigurerAdapter {
//    @Bean
//    public ViewResolver viewResolver() {
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setCache(true);
//        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
//        resolver.setContentType("text/html; charset=UTF-8");
//        return resolver;
//    }
//
//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
//        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        configurer.setTemplateLoaderPaths("file:绝对路径","http://www.xxx.com/");
//        configurer.setDefaultEncoding("UTF-8");
//        return configurer;
//    }

}
