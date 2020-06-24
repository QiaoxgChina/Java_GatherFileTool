package com.qiaoxg.file.entity;

import java.io.File;
import java.io.FileFilter;

import com.qiaoxg.file.FileConstant.FileType;
import com.qiaoxg.file.FileConstant.PictureSuffix;
import com.qiaoxg.file.FileConstant.VideoSuffix;

/**
 * 自定义的文件过滤器
 * 
 * @author Qiaoxg_PC
 *
 */
public class MyFileFilter implements FileFilter {

	private FileType fileType;

	public MyFileFilter(FileType type) {
		this.fileType = type;
	}

	@Override
	public boolean accept(File pathname) {
		// 判断是否是文件
		if (pathname.isFile()) {
			// 判断参数后缀名是否为空
//			if (fileType == FileType.ALL) {
//				return true;
//			} else {
//				// 判断当前文件的后缀是否与指定的相符
//				if (fileType == FileType.VIDEO) {
//					return isVideoFile(pathname.getName());
//				} else if (fileType == FileType.PICTURE) {
//					return isPictureFile(pathname.getName());
//				} else {
//					return false;
//				}
//			}

			switch (fileType) {
			case ALL:
				return true;
			case VIDEO:
				return isVideoFile(pathname.getName());
			case PICTURE:
				return isPictureFile(pathname.getName());
			default:
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 根据后缀名判断是否是视频文件
	 * 
	 * @param suffix 后缀名
	 * @return
	 */
	public static boolean isVideoFile(String suffix) {

		boolean isVideo = false;
		for (VideoSuffix myVar : VideoSuffix.values()) {
//			System.out.println("video  : " + myVar.name() + " and suffix is " + suffix);
			if (suffix.toUpperCase().endsWith(myVar.name())) {
				isVideo = true;
				break;
			}
		}
		return isVideo;
	}

	/**
	 * 根据后缀名判断是否是图片文件
	 * 
	 * @param suffix 后缀名
	 * @return
	 */
	public static boolean isPictureFile(String suffix) {

		boolean isPicture = false;
		for (PictureSuffix myVar : PictureSuffix.values()) {
//			System.out.println("pictrue  : " + myVar.name() + " and suffix is " + suffix);
			if (suffix.toUpperCase().endsWith(myVar.name())) {
				isPicture = true;
				break;
			}
		}
		return isPicture;
	}

}
