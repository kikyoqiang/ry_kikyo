package com.ruoyi.project.tool.utils.other;//package com.ruoyi.project.utils.other;
//
///**
// * 自定义进制转换类
// */
//public class CustomHEXUtil {
//
//    public static void main(String[] args) {
//        byte b = (byte) 125;
//        System.out.println(b);
//        // byte b = strToByte("1");
//        // String s = byteToStr(b);
//        // System.out.println(s);
//    }
//
//    /**
//     * 将字符串 0 转换为 byte 0
//     */
//    public static byte strToByte(String str) {
//        int i = Integer.parseInt(str);
//        return (byte) i;
//        // if ("0".equals(str)) {
//        //     return 0x00;
//        // }
//        // Integer i = Integer.parseInt(str);
//        // String hexItr = Integer.toHexString(i);
//        //
//        // byte[] bytes = new byte[0];
//        // try {
//        //     bytes = Hex.decodeHex(hexItr.toCharArray());
//        // } catch (DecoderException e) {
//        //     e.printStackTrace();
//        // }
//        // // byte[] bytes = hexStringToBytes(HexStr);
//        // return bytes[0];
//    }
//
//    /**
//     * 将byte 0 转换为 字符串 0
//     */
//    public static String byteToStr(byte b) {
//        return b + "";
//        // String s = bytesToHexString(new byte[]{b});
//        // Integer integer = Integer.valueOf(s, 16);
//        // return integer + "";
//    }
//
//    /* Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
//            * @param src byte[] data
//    * @return hex string
//    */
//    public static String bytesToHexString(byte[] src) {
//        StringBuilder stringBuilder = new StringBuilder("");
//        if (src == null || src.length <= 0) {
//            return null;
//        }
//        for (int i = 0; i < src.length; i++) {
//            int v = src[i] & 0xFF;
//            String hv = Integer.toHexString(v);
//            if (hv.length() < 2) {
//                stringBuilder.append(0);
//            }
//            stringBuilder.append(hv);
//        }
//        return stringBuilder.toString();
//    }
//
//    /**
//     * Convert hex string to byte[]
//     *
//     * @param hexString the hex string
//     * @return byte[]
//     */
//    public static byte[] hexStringToBytes(String hexString) {
//        if (hexString == null || hexString.equals("")) {
//            return null;
//        }
//        hexString = hexString.toUpperCase();
//        int length = hexString.length() / 2;
//        char[] hexChars = hexString.toCharArray();
//        byte[] d = new byte[length];
//        for (int i = 0; i < length; i++) {
//            int pos = i * 2;
//            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
//        }
//        return d;
//    }
//
//    /**
//     * Convert char to byte
//     *
//     * @param c char
//     * @return byte
//     */
//    private static byte charToByte(char c) {
//        return (byte) "0123456789ABCDEF".indexOf(c);
//    }
//}
