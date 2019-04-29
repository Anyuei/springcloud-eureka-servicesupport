package com.yun;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import freemarker.template.TemplateException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
@EnableDiscoveryClient//代表自己是一个服务提供方
@MapperScan("com.yun.dao")
public class EurekaServiceApplication extends WebMvcConfigurerAdapter {
    public static String uploadPath;
    public static String staticPath;
    public static String templatePath;

    /**
     * 配置静态资源访问路径，上传资源路径映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations(uploadPath);
        registry.addResourceHandler("/**").addResourceLocations(staticPath);
        super.addResourceHandlers(registry);
    }
    /**
     * 设置模板位置
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @Bean
    public FreeMarkerConfigurer freemarkerConfig(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths(templatePath);
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }
    /**
     * 用于对数据结构，时间类型结构等的转换
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonConfigure(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //日期格式化
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(converter);
    }

    /**
     * jar包入口方法
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class,args);
    }


    static {
        try {
            //项目jar包所在目录
            String jar_parent = new File(ResourceUtils.getURL("classpath:").getPath()).getParentFile().getParentFile().getParent();
            System.out.println("666666666"+jar_parent);
            uploadPath =  jar_parent +File.separator+"logistics"+File.separator+"uploads"+File.separator;
            staticPath = jar_parent + File.separator+"logistics"+File.separator+"staticPath"+File.separator;
            templatePath = jar_parent +File.separator+"logistics"+File.separator+"templates"+File.separator;
            if (!uploadPath.startsWith("file:")){
                uploadPath="file:"+uploadPath;
            }
            if (!staticPath.startsWith("file:")){
                staticPath="file:"+staticPath;
            }
            if (!templatePath.startsWith("file:")){
                templatePath="file:"+templatePath;
            }
            final ArrayList<Object> docPathlist = new ArrayList<>();
            docPathlist.add(uploadPath);
            docPathlist.add(staticPath);
            docPathlist.add(templatePath);
            System.out.println(uploadPath+"="+staticPath+"="+templatePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
