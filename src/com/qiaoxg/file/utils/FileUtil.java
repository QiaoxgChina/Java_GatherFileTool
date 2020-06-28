package com.qiaoxg.file.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.qiaoxg.file.FileConstant.FileType;
import com.qiaoxg.file.entity.MyFileFilter;

public class FileUtil {

	/**
	 * ɨ��ָ���ļ����µ�ָ����׺���������ļ�
	 * 
	 * @param path ָ�����ļ���
	 * @param type �ļ�����
	 * @return ɨ����ļ�
	 */
	public static File[] scanFileFromDir(String path, FileType type) {

		if (path == null || path.length() <= 0) {
			System.err.println("�����·������Ϊ��");
			return null;
		}

		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		
		System.err.println("�����·���ǣ� " + path);
		
		File dirFile = new File(path);
		if (!dirFile.isDirectory()) {
			System.err.println("�����·�������ļ���");
			return null;
		}

		File[] fileList = dirFile.listFiles(new MyFileFilter(type));

		return fileList;
	}

	public static void moveFile(File file) {
		File targetFile = createTargetFile(file);
		if (!targetFile.exists()) {
			copyFileAndKeepInfo(file, targetFile.getAbsolutePath());
//			copyFile2TargetFile(file, targetFile);
		} else {
			System.out.println("----�ļ��Ѿ����ڣ��ݲ�����");
		}
		System.out.println("----" + targetFile.getAbsolutePath() + "\n");
	}

	/**
	 * �����ļ�������ԭʼ�ļ�����Ϣ
	 * 
	 * @param file
	 * @param targetFilePath
	 * @return
	 */
	private static boolean copyFileAndKeepInfo(File file, String targetFilePath) {
		boolean isOk = true;
		Path source = Paths.get(file.getAbsolutePath());
		Path target = Paths.get(targetFilePath);
		try {
			Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
		} catch (IOException e) {
			isOk = false;
		}
		return isOk;
	}

	/**
	 * �����ļ���ָ���ļ� ���ַ�ʽ�������ļ���������ԭʼ�ļ�����Ϣ���磺����ʱ��ȣ�
	 * 
	 * @param old
	 * @param target
	 * @return
	 */
	@SuppressWarnings({ "finally", "resource", "unused" })
	private static boolean copyFile2TargetFile(File old, File target) {

		boolean isOk = true;
		FileChannel input = null;
		FileChannel output = null;
		try {
			input = new FileInputStream(old).getChannel();
			output = new FileOutputStream(target).getChannel();
			output.transferFrom(input, 0, input.size());
		} catch (Exception e) {
			System.out.println("error occur while copy" + e.getMessage());
			isOk = false;
		} finally {

			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return isOk;
		}

	}

	/**
	 * ����Ŀ���ļ�
	 * 
	 * @param file ԭʼ�ļ�
	 * @return Ŀ���ļ�
	 */
	private static File createTargetFile(File file) {
		String targetPath = file.getParent() + File.separator + "result";

		if (MyFileFilter.isVideoFile(file.getName())) {
			targetPath = targetPath + File.separator + FileType.VIDEO + File.separator
					+ DatetimeUtil.formatLongTypeTime2FileName(file.lastModified());
		} else if (MyFileFilter.isPictureFile(file.getName())) {
			targetPath = targetPath + File.separator + FileType.PICTURE + File.separator
					+ DatetimeUtil.formatLongTypeTime2FileName(file.lastModified());
		}
		File targetDir = new File(targetPath);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
			System.out.println("----�����ļ���");
		} else {
			System.out.println("----�ļ����Ѵ���");
		}
		String targetFilePath = targetPath + File.separator + file.getName();
		return new File(targetFilePath);
	}

}
