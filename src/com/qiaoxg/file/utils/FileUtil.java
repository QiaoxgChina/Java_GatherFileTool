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
	 * 扫描指定文件夹下的指定后缀名的所有文件
	 * 
	 * @param path 指定的文件夹
	 * @param type 文件类型
	 * @return 扫描的文件
	 */
	public static File[] scanFileFromDir(String path, FileType type) {

		if (path == null || path.length() <= 0) {
			System.err.println("输入的路径不能为空");
			return null;
		}

		if (!path.endsWith(File.separator)) {
			path = path + File.separator;
		}
		
		System.err.println("输入的路径是： " + path);
		
		File dirFile = new File(path);
		if (!dirFile.isDirectory()) {
			System.err.println("输入的路径不是文件夹");
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
			System.out.println("----文件已经存在，暂不拷贝");
		}
		System.out.println("----" + targetFile.getAbsolutePath() + "\n");
	}

	/**
	 * 拷贝文件并保留原始文件的信息
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
	 * 拷贝文件到指定文件 这种方式拷贝的文件，不保留原始文件的信息（如：创建时间等）
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
	 * 创建目标文件
	 * 
	 * @param file 原始文件
	 * @return 目标文件
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
			System.out.println("----创建文件夹");
		} else {
			System.out.println("----文件夹已存在");
		}
		String targetFilePath = targetPath + File.separator + file.getName();
		return new File(targetFilePath);
	}

}
