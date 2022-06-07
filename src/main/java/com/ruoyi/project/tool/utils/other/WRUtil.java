package com.ruoyi.project.tool.utils.other;//package com.ruoyi.project.utils.other;
//
//import com.ruoyi.project.utils.ParseUtils;
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by A on 2021/2/6.
// */
//public class WRUtil {
//    /**
//     * 写入文件
//     *
//     * @param path
//     * @param value
//     */
//    public static void writeFile(String path, String value) {
//        FileWriter fw = null;
//        String date = ParseUtils.dateSecond();
//        try {
//            //如果文件存在，则追加内容；如果文件不存在，则创建文件
//            File f = new File(path);
//            fw = new FileWriter(f, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        PrintWriter pw = new PrintWriter(fw);
//        pw.println("[" + date + "] " + value);
//        pw.flush();
//        try {
//            fw.flush();
//            pw.close();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String sumCheck(byte[] b, int len) {
//        int sum = 0;
//        for (int i = 0; i < len; i++) {
//            sum = sum + b[i];
//        }
//        // System.out.println(sum);
//        /*
//         * if(sum > 0xff){ //超过了255，使用补码（补码 = 原码取反 + 1） sum = ~sum; sum = sum +
//		 * 1; }
//		 */
//        return Integer.toHexString((sum & 0x0ff)).toUpperCase();
//    }
//
//    public static String sumCheck1(char[] b, int len) {
//        int sum = 0;
//        for (int i = 0; i < len; i++) {
//            sum = sum + b[i];
//        }
//        // System.out.println(sum);
//        /*
//         * if(sum > 0xff){ //超过了255，使用补码（补码 = 原码取反 + 1） sum = ~sum; sum = sum +
//		 * 1; }
//		 */
//        return Integer.toHexString((sum & 0x0ff)).toUpperCase();
//    }
//
//    public static long StringToDate(String str) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS");
//        Date date = sdf.parse(str);
//        return date.getTime();
//    }
//
//    /**
//     * 保存整合6路后的DTU数据
//     *
//     * @param msg
//     * @param
//     * @throws IOException
//     */
//    public static void PrintTo(String msg, String path) throws IOException {
//        String date = ParseUtils.dateSecond();
//        File file = new File(path);
//        FileWriter fw = new FileWriter(file, true);
//        fw.write("[" + date + "] " + msg);
//        fw.write("\n");
//        fw.close();
//    }
//
//    /**
//     * 保存C反馈给上位机的数据
//     *
//     * @param
//     * @throws IOException
//     */
//    public static void JavaToWeb(String path, String value) throws IOException {
//        FileWriter fw = null;
//        String date = ParseUtils.dateSecond();
//        try {
//            //如果文件存在，则追加内容；如果文件不存在，则创建文件
//            File f = new File(path);
//            fw = new FileWriter(f, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        PrintWriter pw = new PrintWriter(fw);
//        pw.println(value);
//        pw.flush();
//        try {
//            fw.flush();
//            pw.close();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 使用FileOutputStream来写入txt文件
//     *
//     * @param txtPath txt文件路径
//     * @param content 需要写入的文本
//     */
//    public static void writeTxt(String txtPath, String content) {
//        FileOutputStream fileOutputStream = null;
//        File file = new File(txtPath);
//        try {
//            if (file.exists()) {
//                file.delete();
//                file.createNewFile();
//            } else {
//                file.createNewFile();
//            }
//            fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(content.getBytes());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        String tar = "AA 2020-6-28-2-38-46.497,39.9170427769,112.9671661802,849.9770036377,0.0549333171,155.4173406171,-0.1153599658,0.0032190937,-0.0000004531,8560,30,-205.8221573937,-1754.0214855019";
//        System.out.println(sumCheck(tar.getBytes(), tar.length()));
//    }
//}
