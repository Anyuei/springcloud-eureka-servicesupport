package com.yun;

import com.yun.dao.CategoryDao;
import com.yun.dao.UserDao;
import com.yun.entity.Category;
import com.yun.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//@WebAppConfiguration//开启Web应用的配置，用于模拟ServletContext

@RunWith(SpringJUnit4ClassRunner.class)//引入Spring对JUnit4的支持
@SpringBootTest
public class Application
{
    //MockMvc:用于模拟调用Controller的接口发起请求。
    /*Controller测试
    private MockMvc mvc;
    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.standaloneSetup(new 需要测试的controller()).build();
    }
    @Test
    public void XXX() throws Exception{
        mvc.perform(
                MockMvcRequestBuilders.get("")
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(equalTo("XXXX")));
    }*/
    @Resource
    private UserDao userDao;
    @Resource
    private CategoryDao categoryDao;
    @Test
    public void insertUser(){
        User newUser = new User();
        newUser.setUserNickname("gakki1");
        newUser.setUserPassword("43520");
        newUser.setEmail("gakki1.com");
        userDao.insertUser(
                newUser
        );
    }
    @Test
    public void selectCategorysByName(){
        System.out.println(categoryDao.retrieveCategoriesByName("衣"));
    }

    /**
     * 读取TXT文件，录种类入数据库
     */
    @Test
    public void insertCategories(){
        makeCategoryFromPath("C:\\Users\\anxiaopei\\Desktop\\2200009000.txt","##");
    }
    public void makeCategoryFromPath(String docPath, String split) {
        List<String> linesList = readLineToListFromPath(docPath);
        String key ="" ;
        Long value = 0L;
        if (linesList != null && linesList.size() > 0) {
            for (String line : linesList) {
                String[] kv = line.split(split);
                try {
                    key = kv[1].trim();
                    value =Long.parseLong(kv[0].trim());
                    Category category = new Category(
                            value,//类目ID
                            key,//类目名
                            1,//类目状态(0-审核 1-通过 2-驳回 3-封禁)
                            null,//子类目
                            2200009000L,//父类目
                            "1",//类目创建者ID
                            new Date(),//创建时间
                            "1",//分类热度点赞数
                            "1"//分类浏览量
                    );
                    categoryDao.insertCategory(category);
                } catch (Exception e) {
                    throw new RuntimeException("文件格式不支持此读取分割符分割");
                }

            }
        }
    }
    public static List<String> readLineToListFromPath(String cfgpath) {
        List<String> pathlist = new ArrayList<>();
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(cfgpath);
            sc = new Scanner(inputStream, "GBK");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line != null && !"".equals(line)) {
                    pathlist.add(line.trim());
                }
            }
            return pathlist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
        return null;
    }
}
