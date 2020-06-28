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
//		System.out.println("���з����������ļ����� �� " + fiList.length);
//		for (File file : fiList) {
//			System.out.println("�ļ��� �� " + file.getName());
//			System.out.println("----����ʱ�� �� " + DatetimeUtil.formatLongTypeTime(file.lastModified()));
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
//		frame.setDefaultLookAndFeelDecorated(true);// ȷ��һ��Ư������۷��
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����Ĭ�ϵĹرմ���
//		frame.pack();// ��ʾ����
//		frame.setVisible(true);// �����÷�����󣬲�Ȼ�������ͼ�����������
//		frame.setBounds(600, 300, 800, 600);// ���ô��ڵ�x,yλ�ã����ڴ�Сx,y.
//
//		jPanel = new JPanel();
//		Button btnButton = new Button();
//		btnButton.setLabel("ѡ���ļ���");
//		btnButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showFileOpenDialog(jPanel);
//			}
//		});
//
////		JOptionPane.showMessageDialog(jPanel, "û��ѡ���κ��ļ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
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
