package com.yun.dao;

import com.yun.entity.FileInfo;

import java.util.List;

/**
 * @version : V1.0
 * @ClassName: FileStructureDao
 * @Description: 项目文件结构操作
 * @Auther: Anakki
 * @Date: 2019/4/29 13:25
 */
public interface FileStructureDao {
    /**
     * 获取所有文件夹信息
     * @return
     */
   List<FileInfo> retrieveFiles();
}
