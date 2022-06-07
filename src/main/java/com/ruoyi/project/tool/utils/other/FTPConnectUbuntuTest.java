package com.ruoyi.project.tool.utils.other;//package com.ruoyi.project.utils.other;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//
//public class FTPConnectUbuntuTest {
//    private static String username = "ftp";
//    private static String password = "123456";
//    private static String ip = "192.168.1.132";
//    private static Integer port = 21;
//
//    public static void start() {
//        String saveDir = "F:/Program/FTP_Save";
//        //下载远程文件夹下的 中文 文件
//        String remoteFileName = "1234567.pdf";
//        // remoteFileName = "./3/1234567.pdf";
//        remoteFileName = "./belt/2021-10-30_22.txt";
//        String fileName = "津巴多普通心理学.pdf";
//        fileName = "1.txt";
//        downloadFtpFile(remoteFileName, saveDir, fileName);
//    }
//
//    public static void downloadFtpFile(String remoteFileName, String saveDir, String fileName) {
//        FTPClient ftpClient = new FTPClient();
//        int reply;
//        try {
//            ftpClient.connect(ip, port);
//            ftpClient.login(username, password);
//            reply = ftpClient.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                System.out.println("=================== connect fail");
//                ftpClient.disconnect();
//                return;
//            }
//            ftpClient.setControlEncoding("UTF-8");
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
//
//            File localFile = new File(saveDir + File.separatorChar + fileName);
//            OutputStream os = new FileOutputStream(localFile);
//            //ftp中文名需要iso-8859-1字符
//            boolean flag2 = ftpClient.retrieveFile(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"), os);
//            if (!flag2) {
//                System.out.println("没有找到" + remoteFileName + "---该文件");
//                localFile.delete();
//            } else {
//                System.out.println("=================== ubuntu save success");
//            }
//            os.close();
//            ftpClient.logout();
//            ftpClient.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
