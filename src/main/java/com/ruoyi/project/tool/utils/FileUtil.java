package com.ruoyi.project.tool.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangning
 * @version V1.0
 * @description 文件操作流工具类
 * @date 2020-11-05
 * @copyright(c) 北京龙田华远科技有限公司
 */
public class FileUtil {

    /**
     * @param path  文件路径
     * @param value 数据
     * @author 张宁
     * @description 保存数据写入文件
     * @date 2020年11月16日 下午4:06:16
     */
    public static void writeFile(String path, String value) {
        FileWriter fw = null;
        try {
            File f = new File(path);
            //不存在自动创建文件夹
            File fileParent = f.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            f.setWritable(true, false);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(value);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件加时间
     *
     * @param path
     * @param value
     */
    public static void writeFileAndTime(String path, String value) {
        FileWriter fw = null;
        String date = ParseUtils.dateSecond();
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            //保存文件
            File file = new File(path);
            //不存在自动创建文件夹
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(date + " " + value);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param 参数
     * @author 张宁
     * @description 迭代删除文件夹
     * @date 2020年11月16日 下午4:07:17
     */
    public static void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    /**
     * @param 参数
     * @author 张宁
     * @description 获取目录下所有文件按时间排序
     * @date 2020年11月16日 下午4:07:26
     */
    public static List<File> getFileSort(String path) {

        List<File> list = getFiles(path, new ArrayList<File>());

        if (list != null && list.size() > 0) {

            Collections.sort(list);
        }

        return list;
    }

    /**
     * @param 参数
     * @author 张宁
     * @description 获取目录下所有文件
     * @date 2020年11月16日 下午4:07:35
     */
    public static List<File> getFiles(String realpath, List<File> files) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    //getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * @param 参数
     * @author 张宁
     * @description 获取目录下所有文件夹按时间排序
     * @date 2020年11月16日 下午4:07:26
     */
    public static List<File> getFilesSort(String path) {

        List<File> list = getDirectory(path, new ArrayList<File>());

        if (list != null && list.size() > 0) {

            Collections.sort(list);
        }

        return list;
    }

    /**
     * @param 参数
     * @author 张宁
     * @description 获取目录下所有文件夹
     * @date 2020年11月16日 下午4:07:35
     */
    public static List<File> getDirectory(String realpath, List<File> files) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * @return void    返回类型
     * @throws
     * @author 张宁
     * @Description: 文件复制
     * @date 2021年11月13日 下午9:30:40
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            File oldfile = new File(oldPath);
            if (!oldfile.exists()) {
                return;
            }
            InputStream inStream = new FileInputStream(oldPath); //读入原文件
            FileOutputStream fs = new FileOutputStream(newPath);
            int byteread = 0;
            byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
            fs.close();
        } catch (Exception e) {
            // System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    //byte数组到图片到硬盘上
    public static void byte2pcm(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) return;//判断输入的byte是否为空
        try {
            FileOutputStream fs = new FileOutputStream(new File(path));
            fs.write(data, 0, data.length);//将byte写入硬盘
            fs.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
}
