package com.qiaoxg.file.utils;

import java.text.SimpleDateFormat;

public class DatetimeUtil {

	private static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat SDF_DATE_FORMAT_FILE = new SimpleDateFormat("yyyyMM");

	/**
	 * 格式化long类型的时间
	 * 
	 * @param timeLong
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	public static String formatLongTypeTime(long timeLong) {
		return SDF_DATE_FORMAT.format(timeLong);
	}
	
	/**
	 * 格式化long类型的时间
	 * 
	 * @param timeLong
	 * @return "@SDF_DATE_FORMAT_FILE"
	 */
	public static String formatLongTypeTime2FileName(long timeLong) {
		return SDF_DATE_FORMAT_FILE.format(timeLong);
	}


}
