package com.yun.entity;

/**
 * @version : V1.0
 * @ClassName: FileInfo
 * @Description: 文件夹信息
 * @Auther: Anakki
 * @Date: 2019/4/29 14:13
 */
public class FileInfo {
    private Integer fileID;//文件夹ID
    private String fileName;//文件名
    private Integer fileLevel;//文件目录级别
    private Integer parentFileID;//父目录文件夹ID
    public FileInfo() {
    }
    public FileInfo(Integer fileID, String fileName, Integer fileLevel, Integer parentFileID) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileLevel = fileLevel;
        this.parentFileID = parentFileID;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileID=" + fileID +
                ", fileName='" + fileName + '\'' +
                ", fileLevel=" + fileLevel +
                ", parentFileID=" + parentFileID +
                '}';
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileLevel() {
        return fileLevel;
    }

    public void setFileLevel(Integer fileLevel) {
        this.fileLevel = fileLevel;
    }

    public Integer getParentFileID() {
        return parentFileID;
    }

    public void setParentFileID(Integer parentFileID) {
        this.parentFileID = parentFileID;
    }
}
