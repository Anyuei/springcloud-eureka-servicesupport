package com.yun.utils;

import com.yun.EurekaServiceApplication;
import com.yun.dao.FileStructureDao;
import com.yun.entity.FileInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @version : V1.0
 * @ClassName: FileUtils
 * @Description:
 * @Auther: Anakki
 * @Date: 2019/4/29 14:35
 */
@Component
public class FileUtils {
    /*上传头像限制大小*/
    private static Integer limitAvatarSizeMB=2;//单位MB
    private static Long limitAvatarSize=1024L*1024*limitAvatarSizeMB;//2MB

    /*上传评论图片限制大小*/
    private static Integer limitImgSizeMB=2;//单位MB
    private static Long limitImgSize=1024L*1024*limitImgSizeMB;//2MB
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

    /**
     * 文件上传工具类
     * @param file 文件
     * @param uploadAbsolutePath 存放路径
     * @return
     */
    public static String uploadFile(MultipartFile file,String uploadAbsolutePath,Long limitFileSize,String filename){
        /*文件大小限制*/
        long imageSize = file.getSize();//单位为字节
        if (imageSize>limitFileSize){
            return "请上传小于"+limitFileSize/1024/1024+"MB的文件";
        }

        File uploadPath = new File(uploadAbsolutePath.replace("file:",""));
        File uploadImg=new File(uploadAbsolutePath.replace("file:",""),filename);

        /*路径不存在时新建路径*/
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        /*开始保存文件*/
        try {
            file.transferTo(uploadImg);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

}
