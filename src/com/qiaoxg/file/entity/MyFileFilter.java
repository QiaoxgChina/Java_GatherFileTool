package com.qiaoxg.file.entity;

import java.io.File;
import java.io.FileFilter;

import com.qiaoxg.file.FileConstant.FileType;
import com.qiaoxg.file.FileConstant.PictureSuffix;
import com.qiaoxg.file.FileConstant.VideoSuffix;

/**
 * �Զ�����ļ�������
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
		// �ж��Ƿ����ļ�
		if (pathname.isFile()) {
			// �жϲ�����׺���Ƿ�Ϊ��
//			if (fileType == FileType.ALL) {
//				return true;
//			} else {
//				// �жϵ�ǰ�ļ��ĺ�׺�Ƿ���ָ�������
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
	 * ���ݺ�׺���ж��Ƿ�����Ƶ�ļ�
	 * 
	 * @param suffix ��׺��
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
	 * ���ݺ�׺���ж��Ƿ���ͼƬ�ļ�
	 * 
	 * @param suffix ��׺��
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
