package com.yun;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootApplication
@EnableDiscoveryClient//代表自己是一个服务提供方
@MapperScan("com.yun.dao")
public class EurekaServiceApplication extends WebMvcConfigurerAdapter {

    /**
     * 配置资源路径映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String gitPath = path.getParentFile().getParentFile().getParent()+File.separator+"logistics"+File.separator+"uploads"+File.separator;
        String staticPath = path.getParentFile().getParentFile().getParent()+File.separator+"logistics"+File.separator+"staticPath"+File.separator;
        registry.addResourceHandler("/uploads/**").addResourceLocations(gitPath);
        registry.addResourceHandler("/**").addResourceLocations(staticPath);
        super.addResourceHandlers(registry);
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
}
