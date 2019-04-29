package com.yun.utils;

import com.yun.dao.FileStructureDao;
import com.yun.entity.FileInfo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: FileUtils
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/29 14:35
 */
@Component
public class FileUtils {
    @Resource
    private FileStructureDao fileStructureDao;

    public static FileUtils fileUtils;
    @PostConstruct
    public void init(){
        fileUtils = this;
        fileUtils.fileStructureDao=this.fileStructureDao;
        System.out.println("工具类已经初始化了，被纳入spring管理");
        List<FileInfo> fileInfos = FileUtils.initFileStructure();
        HashMap<Object, Object> map = new HashMap<>();
        for (FileInfo fileInfo : fileInfos) {
            map.put(fileInfo.getFileID(),fileInfo.getParentFileID());
        }

    }
    public static List<FileInfo> initFileStructure(){
        List<FileInfo> fileInfos = fileUtils.fileStructureDao.retrieveFiles();
        if (fileInfos==null||fileInfos.isEmpty()){
            System.out.println(new Date()+"文件结构初始化失败");
            return  null;
        }else{
            return  fileInfos;
        }

    }
}
