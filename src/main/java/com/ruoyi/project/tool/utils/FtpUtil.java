package com.ruoyi.project.tool.utils;

import ch.qos.logback.classic.Logger;
import org.apache.commons.net.ftp.*;

import java.io.*;

public class FtpUtil {
    private Logger logError = LogbackUtil.getErrorLogger();
    private FTPClient ftpClient;

    public static void main(String[] args) throws Exception {
        //downBelt();
        //downEWS();
        //downLIDAR();
        //downScraper();
        downTest();
    }

    private static void downTest() {
        FtpUtil ftpUtil = new FtpUtil();
        FtpModel ftpModel = ftpUtil.getFtpModel("192.168.1.8", 21, "ftp", "123456");
        String connect = ftpUtil.connect(ftpModel);
        System.out.println("======== connect " + connect);
        String downloadDir3 = ftpUtil.down_dir_Files("./A01", "C:\\Users\\K\\Desktop\\桌面临时\\ftpSave");
        System.out.println("downloadDir " + downloadDir3);
    }

    private static void downBelt() {
        FtpUtil ftpUtil = new FtpUtil();
        FtpModel ftpModel = ftpUtil.getFtpModel("192.168.1.61", 21, "ftp", "123456");
        String connect = ftpUtil.connect(ftpModel);
        System.out.println("======== connect " + connect);
        String downloadDir2 = ftpUtil.down_del_dir_Files("./belt", "C:\\Users\\bjlthy\\Desktop\\桌面临时\\ftpSave\\Belt");
        System.out.println("downloadDir " + downloadDir2);

        // String uploadFile = ftpUtil.uploadFile("./testDir", "C:\\Users\\A\\Desktop\\桌面临时\\FtpUtil.java");
        // System.out.println("uploadFile " + uploadFile);

        //String downloadDir = ftpUtil.down_del_dir_Files("./belt", "C:\\Users\\bjlthy\\Desktop\\桌面临时\\ftpSave");

        // String res = ftpUtil.downloadFile("./testDir/FtpUtil.java", "C:\\Users\\A\\Desktop\\桌面临时\\ftpSave", "FtpUtil222.java");
        // System.out.println("======== res " + res);

        //String deleteFile = ftpUtil.deleteFile("./testDir", "FtpUtil.java");
        //System.out.println("======== deleteFile " + deleteFile);
    }

    private static void downEWS() {
        FtpUtil ftpUtil = new FtpUtil();
        FtpModel ftpModel = ftpUtil.getFtpModel("192.168.1.61", 21, "ftp", "123456");
        String connect = ftpUtil.connect(ftpModel);
        System.out.println("======== connect " + connect);
        String downloadDir3 = ftpUtil.down_del_dir_Files("./EWS", "C:\\Users\\bjlthy\\Desktop\\桌面临时\\ftpSave\\EWS");
        System.out.println("downloadDir " + downloadDir3);
    }

    private static void downLIDAR() {
        FtpUtil ftpUtil = new FtpUtil();
        FtpModel ftpModel = ftpUtil.getFtpModel("192.168.1.61", 21, "ftp", "123456");
        String connect = ftpUtil.connect(ftpModel);
        System.out.println("======== connect " + connect);
        String downloadDir4 = ftpUtil.down_del_dir_Files("./LIDAR", "C:\\Users\\bjlthy\\Desktop\\桌面临时\\ftpSave\\LIDAR");
        System.out.println("downloadDir " + downloadDir4);
    }

    private static void downScraper() {
        FtpUtil ftpUtil = new FtpUtil();
        FtpModel ftpModel = ftpUtil.getFtpModel("192.168.1.8", 21, "ftp", "123456");
        String connect = ftpUtil.connect(ftpModel);
        System.out.println("======== connect " + connect);
        String downloadDir2 = ftpUtil.down_del_dir_Files("/Scraper/SCRAPER", "C:\\Users\\bjlthy\\Desktop\\桌面临时\\ftpSave\\Scraper");
        System.out.println("downloadDir " + downloadDir2);
    }


    public String connect(FtpModel ftpModel) {
        ftpClient = new FTPClient();
        String res;
        try {
            ftpClient.connect(ftpModel.getIp(), ftpModel.getPort());
            ftpClient.login(ftpModel.getUserName(), ftpModel.getPassword());
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");
            // ftpClient.changeWorkingDirectory(ftpModel.getWorkDir());
            ftpClient.enterLocalPassiveMode();
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                res = "-1 " + reply;
            } else {
                res = "0 " + reply;
            }
        } catch (Exception e) {
            logError.error("连接ftp服务失败" + e.getMessage(), e);
            e.printStackTrace();
            res = "-1 " + e.getMessage();
        }
        return res;
    }

    public String uploadFile(String remoteBaseDir, String fileName) throws Exception {
        try {
            boolean makeDirectory = ftpClient.makeDirectory(remoteBaseDir);
            boolean b1 = ftpClient.changeWorkingDirectory(remoteBaseDir);
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            boolean b = ftpClient.storeFile(file.getName(), fileInputStream);
            fileInputStream.close();
            return b ? "0" : "-1";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1 " + e.getMessage();
        }
    }

    public String downloadFile(String remoteFileName, String saveDir, String fileName) {
        String res;
        try {
            File fileDir = new File(saveDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File localFile = new File(saveDir + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFile);
            //ftp中文名需要iso-8859-1字符
            boolean flag2 = ftpClient.retrieveFile(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"), os);
            os.close();
            close();
            if (!flag2) {
                localFile.delete();
                res = "-1 " + "没有找到 " + remoteFileName + " 该文件";
            } else {
                res = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "-1 " + e.getMessage();
        }
        return res;
    }

    public void close() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp上传文件
     */
    public void upload(File file) throws Exception {
        if (!file.isDirectory()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ftpClient.storeFile(file.getName(), fileInputStream);
            fileInputStream.close();
        } else {
            ftpClient.makeDirectory(file.getName());
            ftpClient.changeWorkingDirectory(file.getName());
            String[] files = file.list();
            for (String fileStr : files) {
                File file1 = new File(file.getPath() + "/" + fileStr);
                if (file1.isDirectory()) {
                    upload(file1);
                    ftpClient.changeToParentDirectory();
                } else {
                    File file2 = new File(file.getPath() + "/" + fileStr);
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    ftpClient.storeFile(file2.getName(), fileInputStream);
                    fileInputStream.close();
                }
            }
        }
    }

    public String down_dir_Files(String remoteBaseDir, String localBaseDir) {
        try {
            FTPFile[] files;
            boolean isChangeDir = ftpClient.changeWorkingDirectory(remoteBaseDir);
            if (!isChangeDir) {
                return "-1 changeWorkingDirectory fail";
            }
            ftpClient.setControlEncoding("UTF-8");
            files = ftpClient.listFiles();
            if (files.length <= 0) {
                return "1";
            }
            for (FTPFile ftpFile : files) {
                downloadFile(ftpFile, localBaseDir);
            }
            close();
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1 下载过程中出现异常" + e.getMessage();
        }
    }

    public String down_del_dir_Files(String remoteBaseDir, String localBaseDir) {
        try {
            FTPFile[] files;
            boolean isChangeDir = ftpClient.changeWorkingDirectory(remoteBaseDir);
            if (!isChangeDir) {
                logError.error("-1 changeWorkingDirectory fail");
                return "-1 changeWorkingDirectory fail";
            }
            ftpClient.setControlEncoding("UTF-8");
            files = ftpClient.listFiles();
            if (files.length <= 0) {
                logError.error("files.length <= 0");
                return "1";
            }
            for (FTPFile ftpFile : files) {
                downloadFile(ftpFile, localBaseDir);
                deleteFile(remoteBaseDir, ftpFile.getName());
            }
            close();
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            logError.error("", e);
            return "-1 下载过程中出现异常" + e.getMessage();
        }
    }

    private void downloadFile(FTPFile ftpFile, String localBaseDir) {
        OutputStream outputStream;
        try {
            String localFilePath = localBaseDir + File.separatorChar + ftpFile.getName();
            File locaFile = new File(localFilePath);
            if (locaFile.exists()) {
                return;
            }
            File fileParent = locaFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            outputStream = new FileOutputStream(localFilePath);
            ftpClient.retrieveFile(ftpFile.getName(), outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            logError.error(e.toString());
            e.printStackTrace();
        }
    }

    public String deleteFile(String remoteDir, String remoteFileName) {
        String res;
        try {
            ftpClient.changeWorkingDirectory(remoteDir);
            int dele = ftpClient.dele(remoteFileName);

            res = dele == 250 ? "0" : "-1";
        } catch (Exception e) {
            logError.error("删除的文件失败");
            e.printStackTrace();
            res = "-1 " + e.getMessage();
        }
        return res;
    }

    public FtpModel getFtpModel(String ip, Integer port, String userName, String password) {
        return new FtpModel(ip, port, userName, password);
    }

    public class FtpModel {
        public String ip;
        public Integer port;
        public String userName;
        public String password;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public FtpModel() {
        }

        public FtpModel(String ip, Integer port, String userName, String password) {
            this.ip = ip;
            this.port = port;
            this.userName = userName;
            this.password = password;
        }
    }
}

