package com.ruoyi.project.tool.utils;

/**
 * @description 这是一个IP地址工具类
 * @author zhangning
 * @version V1.0
 * @date 2020-11-05
 * @copyright(c) 北京龙田华远科技有限公司
 *
 */
public class LidarUtil {
	/***
	 * 说明：
	 * 帧头：0x02,0x73,0x4D,0x4E,0x20
	 * 帧尾：0x03
	 * 查询雾化状态：LMS511:0x43,0x4C,0x46,0x6F,0x67,0x46,0x69,0x6C,0x74,0x65,0x72,0x45,0x6E
	 * 查询雾化状态：LMS111:0x4D,0x53,0x73,0x75,0x70,0x70,0x6D,0x6F,0x64,0x65
	 * 登录命令:0x53,0x65,0x74,0x41,0x63,0x63,0x65,0x73,0x73,0x4D,0x6F,0x64,0x65
	 * 用户名和密码：0x20,0x30,0x33,0x20,0x46,0x34,0x37,0x32,0x34,0x37,0x34,0x34
	 * 修改指令:LMS511:0x43,0x4C,0x46,0x6F,0x67,0x46,0x69,0x6C,0x74,0x65,0x72,0x45,0x6E,0x20,0x30
	 * 修改指令:LMS111:0x4D,0x53,0x73,0x75,0x70,0x70,0x6D,0x6F,0x64,0x65,0x20,0x30
	 */
	/** 检查雾化状态指令LMS511 sRN CLFogFiltterEn*/
	public static final byte[] statusBuff_511 ={0x02,0x73,0x52,0x4E,0x20,0x43,0x4C,0x46,0x6F,0x67,0x46,0x69,0x6C,0x74,0x65,0x72,0x45,0x6E,0x03};
	/** 检查雾化状态指令LMS111 sRN MSsuppmode*/
	public static final byte[] statusBuff_111 ={0x02,0x73,0x52,0x4E,0x20,0x4D,0x53,0x73,0x75,0x70,0x70,0x6D,0x6F,0x64,0x65,0x03};
	/** 登录指令  sMN SetAccessMode 3 F4724744*/
	public static final byte[] loginBuff ={0x02,0x73,0x4D,0x4E,0x20,0x53,0x65,0x74,0x41,0x63,0x63,0x65,0x73,0x73,0x4D,0x6F,0x64,0x65,0x20,0x30,0x33,0x20,0x46,0x34,0x37,0x32,0x34,0x37,0x34,0x34,0x03};
	/** 修改雾化指令LMS511 sWN CLFogFilterEn 0*/
	public static final byte[] updateStatusBuff_511 ={0x02,0x73,0x57,0x4E,0x20,0x43,0x4C,0x46,0x6F,0x67,0x46,0x69,0x6C,0x74,0x65,0x72,0x45,0x6E,0x20,0x30,0x03};
	/** 修改雾化指令LMS111*/
	public static final byte[] updateStatusBuff_111 ={0x02,0x73,0x57,0x4E,0x20,0x43,0x4C,0x46,0x6F,0x67,0x46,0x69,0x6C,0x74,0x65,0x72,0x45,0x6E,0x20,0x30,0x03};
	/** 永久保存指令 sMN mEEwriteall*/
	public static final byte[] saveBuff ={0x02,0x73,0x4D,0x4E,0x20,0x6D,0x45,0x45,0x77,0x72,0x69,0x74,0x65,0x61,0x6C,0x6C,0x03};
	/** 重启设备指令 sMN Run*/
	public static final byte[] rebootBuff ={0x02,0x73,0x4D,0x4E,0x20,0x52,0x75,0x6E,0x03};
	/** 检查雷达状态 sRA STlms*/
	public static final byte[] statusBuff ={0x02,0x73,0x52,0x4E,0x20,0x53,0x54,0x6C,0x6D,0x73,0x03};
	/** 连续读取数据 sEN LMDscandata 1*/
	public static final byte[] readDataBuff ={0x02, 0x73, 0x45, 0x4E, 0x20, 0x4C, 0x4D, 0x44, 0x73, 0x63, 0x61, 0x6E, 0x64, 0x61, 0x74, 0x61, 0x20, 0x31, 0x03};

}
