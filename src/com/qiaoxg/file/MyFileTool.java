package com.qiaoxg.file;

import java.io.File;

import com.qiaoxg.file.FileConstant.FileType;
import com.qiaoxg.file.utils.DatetimeUtil;
import com.qiaoxg.file.utils.FileUtil;

public class MyFileTool {

	public static void main(String[] args) {

		File[] fiList = FileUtil.scanFileFromDir("D:\\Source\\", FileType.ALL);

		System.out.println("共有符合条件的文件数量 ： " + fiList.length);
		for (File file : fiList) {
			System.out.println("文件名 ： " + file.getName());
			System.out.println("----创建时间 ： " + DatetimeUtil.formatLongTypeTime(file.lastModified()));
			FileUtil.moveFile(file);
		}

	}

}
