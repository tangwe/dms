package com.dms.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用工具类
 * 
 * @author SemF
 * 
 * @create 2014年8月31日 上午12:06:40
 * 
 * @version 1.0
 */
public class CommonUtil {
	private static final String DATEFORMAT_YYYYDDMM = "yyyy-MM-dd";

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            时间
	 * @return 格式化时间字符串 如：2014-08-31 11:11:11
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT_YYYYDDMM);
		return dateFormat.format(date);
	}
}
