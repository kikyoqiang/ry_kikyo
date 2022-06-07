package com.ruoyi.project.tool.utils.other;//package com.ruoyi.project.utils.other;// package com.ruoyi.project.front.index.service;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public final class JdbcUtils {
//    private static String url = "jdbc:mysql://localhost:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
//    private static String user = "root";
//    private static String password = "root";
//
//    public static void main(String[] args) {
//        // String sql = "UPDATE `user` set money=666 WHERE `name` =? ";
//        // System.out.println(executeUpdate(sql, new Object[]{"name1"}));
//        List<Map<String, Object>> list = executeQuery("select * from user", null);
//        System.out.println(list);
//    }
//
//    public static List<Map<String, Object>> executeQuery(String sql, Object[] parms) {
//        Connection conn = null;
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        try {
//            conn = JdbcUtils.getConnection();
//            st = conn.prepareStatement(sql);
//            if (parms != null && parms.length > 0) {
//                for (int i = 0; i < parms.length; i++) {
//                    st.setObject(i + 1, parms[i]);
//                }
//            }
//            rs = st.executeQuery();
//            ResultSetMetaData rsmd = rs.getMetaData();
//            while (rs.next()) {
//                Map<String, Object> map = new HashMap<>();
//                for (int i = 0; i < rsmd.getColumnCount(); i++) {
//                    String columnLabel = rsmd.getColumnLabel(i + 1);
//                    Object columnValue = rs.getObject(columnLabel);
//                    map.put(columnLabel, columnValue);
//                }
//                list.add(map);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.free(rs, st, conn);
//        }
//        return list;
//    }
//
//    public static int executeUpdate(String sql, Object[] parms) {
//        int res = -1;
//        Connection conn = null;
//        PreparedStatement pst = null;
//        ResultSet set = null;
//        try {
//            conn = getConnection();
//            pst = conn.prepareStatement(sql);
//            if (parms != null && parms.length > 0) {
//                for (int i = 0; i < parms.length; i++) {
//                    pst.setObject(i + 1, parms[i]);
//                }
//            }
//            res = pst.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            free(set, pst, conn);
//        }
//        return res;
//    }
//
//    private JdbcUtils() {
//    }
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(url, user, password);
//    }
//
//    public static void free(ResultSet rs, Statement st, Connection conn) {
//        try {
//            if (rs != null)
//                rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (st != null)
//                    st.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                if (conn != null) {
//                    try {
//                        conn.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//}