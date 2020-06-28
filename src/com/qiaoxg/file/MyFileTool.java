package com.qiaoxg.file;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Reader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.qiaoxg.file.FileConstant.FileType;
import com.qiaoxg.file.utils.DatetimeUtil;
import com.qiaoxg.file.utils.FileUtil;
import com.qiaoxg.file.window.MainWindow;

public class MyFileTool {

	private static Label mFileNumberTxtLabel;
	private static JPanel jPanel;

	public static void main(String[] args) {

//		File[] fiList = FileUtil.scanFileFromDir("D:\\Source\\", FileType.ALL);
//
//		System.out.println("共有符合条件的文件数量 ： " + fiList.length);
//		for (File file : fiList) {
//			System.out.println("文件名 ： " + file.getName());
//			System.out.println("----创建时间 ： " + DatetimeUtil.formatLongTypeTime(file.lastModified()));
//			FileUtil.moveFile(file);
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

//		JFrame frame = new JFrame("Qiaoxg");
//		frame.setDefaultLookAndFeelDecorated(true);// 确保一个漂亮的外观风格
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置默认的关闭窗口
//		frame.pack();// 显示窗口
//		frame.setVisible(true);// 这个最好放在最后，不然会出现视图看不到的情况
//		frame.setBounds(600, 300, 800, 600);// 设置窗口的x,y位置，窗口大小x,y.
//
//		jPanel = new JPanel();
//		Button btnButton = new Button();
//		btnButton.setLabel("选择文件夹");
//		btnButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showFileOpenDialog(jPanel);
//			}
//		});
//
////		JOptionPane.showMessageDialog(jPanel, "没有选中任何文件", "提示", JOptionPane.WARNING_MESSAGE);
//
//		jPanel.add(btnButton);
//		frame.add(jPanel);
//		
//		
//		
//		JScrollPane scrollPane = new JScrollPane();
//		jPanel.add(scrollPane, BorderLayout.CENTER);
//		
//		JMenuBar menuBar = new JMenuBar();
//		scrollPane.setColumnHeaderView(menuBar);
//		
//		JMenu mnFile = new JMenu("File");
//		menuBar.add(mnFile);
//		
//		JMenu mnEditor = new JMenu("Editor");
//		menuBar.add(mnEditor);
//		
//		JMenu mnRefactor = new JMenu("Refactor");
//		menuBar.add(mnRefactor);

	}
	
}
