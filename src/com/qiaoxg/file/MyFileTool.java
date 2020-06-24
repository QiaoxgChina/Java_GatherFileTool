package com.qiaoxg.file;

import java.io.File;

import com.qiaoxg.file.FileConstant.FileType;
import com.qiaoxg.file.utils.DatetimeUtil;
import com.qiaoxg.file.utils.FileUtil;

public class MyFileTool {

	public static void main(String[] args) {

		File[] fiList = FileUtil.scanFileFromDir("D:\\Source\\", FileType.ALL);

		System.out.println("���з����������ļ����� �� " + fiList.length);
		for (File file : fiList) {
			System.out.println("�ļ��� �� " + file.getName());
			System.out.println("----����ʱ�� �� " + DatetimeUtil.formatLongTypeTime(file.lastModified()));
			FileUtil.moveFile(file);
		}

	}

}
