package com.ruoyi.project.tool.utils;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtils {

    /**
     * 截取byte数组
     *
     * @param src
     * @param begin
     * @param count
     * @return 小端，有符号
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    /**
     * 字节数组转int（小端排序）
     *
     * @param b
     * @return
     */
    public static int byteArrayToInt(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * i);
        }
        return intValue;
    }

    /**
     * 字节数组转不同进制字符串
     *
     * @param bytes
     * @param radix
     * @return
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    /**
     * 时间格式为yyyyMMddHH
     *
     * @return
     */
    public static String dateHour() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
        return df.format(date);
    }

    /**
     * 时间格式为yyyy-MM-dd HH:mm:ss.SSS
     *
     * @return
     */
    public static String dateSecond() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return df.format(date);
    }

    public static String dateSecond1() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 时间格式为yyyyMMdd
     *
     * @return
     */
    public static String dateDay() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    /**
     * 双精度浮点型转byte[]
     *
     * @param d double值
     * @return byteRet byte[]
     */
    public static byte[] double2Bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    /**
     * 整型数值转为 byte[]
     *
     * @param number
     * @return byte[]
     */
    public byte[] int2Bytes(int number) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) number;
        bytes[2] = (byte) ((number >> 8) & 0xFF);
        bytes[1] = (byte) ((number >> 16) & 0xFF);
        bytes[0] = (byte) ((number >> 24) & 0xFF);
        return bytes;
    }

    /**
     * 将byte 数值转为 HEX字符串
     *
     * @param bytes
     * @return String
     */
    public static String bytesToHex(byte[] bytes) {
        String hex = new BigInteger(1, bytes).toString(16);
        return hex;
    }

    /**
     * 计算校验和
     *
     * @param msg
     */
    public static String checkData(String msg) {
        char[] result = msg.toCharArray();
        String he = sumCheck(result, result.length);
        if (he.length() < 2) {
            he = "0" + he;
        }
        return he;
    }

    public static String sumCheck(char[] b, int len) {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum = sum + b[i];
        }
        // System.out.println(sum);
        /*
         * if(sum > 0xff){ //超过了255，使用补码（补码 = 原码取反 + 1） sum = ~sum; sum = sum +
		 * 1; }
		 */
        return Integer.toHexString((sum & 0x0ff)).toUpperCase();
    }

    /**
     * ping IP加端口
     *
     * @param ip
     * @param port
     * @return
     */
    public static boolean pingIpAndPort(String ip, int port) {
        if (null == ip || 0 == ip.length() || port > 65535) {
            return false;
        }
        if (!pingIp(ip)) {
            return false;
        }
        Socket s = new Socket();
        try {
            SocketAddress add = new InetSocketAddress(ip, port);
            s.connect(add, 3000);// 超时3秒
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                s.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * ping ip
     *
     * @param ip
     * @return
     */
    public static boolean pingIp(String ip) {
        if (null == ip || 0 == ip.length()) {
            return false;
        }
        try {
            InetAddress.getByName(ip);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @param jexlExp
     * @param map
     * @return 设定文件 Object 返回类型
     * @author 张宁
     * @Title: converToCode
     * @Description: java 将字符串转成可执行代码  工具类
     * @date 2021年8月20日 下午3:10:06
     */
    public static Object converToCode(String jexlExp, Map<String, Object> map) {

        JexlEngine jexl = new JexlEngine();
        Expression expression = jexl.createExpression(jexlExp);

        JexlContext jc = new MapContext();

        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        if (null == expression.evaluate(jc)) {
            return "";
        }

        return expression.evaluate(jc);

    }

    /**
     * 保留小数,四舍五入
     *
     * @param data
     * @return
     */
    public static Double formart(String data) {
        if (StringUtils.isEmpty(data)) {
            return 0.00;
        }
        //利用BigDecimal来实现四舍五入.保留一位小数
        double result = new BigDecimal(data).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //1代表保留1位小数,保留两位小数就是2,依此累推
        //BigDecimal.ROUND_HALF_UP 代表使用四舍五入的方式
        return result;
    }


    public static boolean isIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }
}