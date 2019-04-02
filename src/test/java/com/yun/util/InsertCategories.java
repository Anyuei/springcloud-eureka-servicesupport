package com.yun.util;

import com.yun.entity.Category;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @version : V1.0
 * @ClassName: InsertCategories
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/3/27 1:06
 */
public class InsertCategories {

    public static void main(String[] args) {

        System.out.println(new Date());
    }
/*
    public static void makeCategoryFromPath(String docPath, String split) {
        List<String> linesList = readLineToListFromPath(docPath);
        String key ="" ;
        Long value = 0L;
        if (linesList != null && linesList.size() > 0) {
            for (String line : linesList) {
                String[] kv = line.split(split);
                try {
                    key = kv[0].trim();
                    value =Long.parseLong(kv[1].trim());
                    Category category = new Category(
                    value,//类目ID
                    key,//类目名
                    1,//类目状态(0-审核 1-通过 2-驳回 3-封禁)
                    null,//子类目
                    1L,//父类目
                    "1",//类目创建者ID
                    new Date(),//创建时间
                    "1",//分类热度点赞数
                    "1"//分类浏览量
                    );

                } catch (Exception e) {
                    throw new RuntimeException("文件格式不支持此读取分割符分割");
                }

            }
        }
    }


    public static HashMap<String, String> getMapFromPath(String docPath, String split) {
        HashMap<String, String> mapOfLine = new HashMap<String, String>();
        List<String> linesList = readLineToListFromPath(docPath);
        String key = "";
        String value = "";
        if (linesList != null && linesList.size() > 0) {
            for (String line : linesList) {
                String[] kv = line.split(split);
                try {//�ָ����ʱ��kvֻ��һ��ֵ��kv[1]�����±�Խ����쳣���ʲ����׳���
                    key = kv[0].trim();
                    value = kv[1].trim();
                } catch (Exception e) {
                    throw new RuntimeException("");
                }
                mapOfLine.put(key, value);
            }
        }
        return mapOfLine;
    }

    public static List<String> readLineToListFromPath(String cfgpath) {
        List<String> pathlist = new ArrayList<>();
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(cfgpath);
            sc = new Scanner(inputStream, "UTF-8");
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
    }*/
}


