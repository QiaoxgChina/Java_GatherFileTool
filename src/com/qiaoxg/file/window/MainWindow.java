package com.qiaoxg.file.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.qiaoxg.file.FileConstant.FileType;
import com.qiaoxg.file.utils.DatetimeUtil;
import com.qiaoxg.file.utils.FileUtil;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JScrollPane scrollPane;

	public MainWindow() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		final JMenuItem newItem = new JMenuItem("新建（New）");// 创建菜单项
		newItem.addActionListener(new ItemListener());
		mnFile.add(newItem);
		final JMenuItem secletItem = new JMenuItem("选择文件夹（Select）");// 创建菜单项
		secletItem.addActionListener(new ItemListener());
		mnFile.add(secletItem);
		menuBar.add(mnFile);

		JMenu mnEdit = new JMenu("Edit");
		final JMenuItem copyItem = new JMenuItem("复制（Copy）");// 创建菜单项
		copyItem.addActionListener(new ItemListener());
		mnEdit.add(copyItem);
		final JMenuItem cutItem = new JMenuItem("剪切（Cut）");// 创建菜单项
		cutItem.addActionListener(new ItemListener());
		mnEdit.add(cutItem);
		menuBar.add(mnEdit);

		JMenu mnHelp = new JMenu("Help");
		final JMenuItem aboutItem = new JMenuItem("关于（About）");// 创建菜单项
		aboutItem.addActionListener(new ItemListener());
		mnHelp.add(aboutItem);
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(590, 490));
		contentPane.add(scrollPane);

	}

	private class ItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem menuItem = (JMenuItem) e.getSource();
			System.out.println("您单击的是菜单项：" + menuItem.getText());
			if (menuItem.getText().equals("选择文件夹（Select）")) {
				showFileOpenDialog();
			}
		}
	}

	public void showFileOpenDialog() {

		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("."));// 设置默认显示为当前文件夹
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 设置选择模式（只选文件、只选文件夹、文件和文件均可选）
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
			try {
				File file = fc.getSelectedFile();// 获取打开的文件
				System.out.println(file.getAbsolutePath());

				new Thread(new Runnable() {

					@Override
					public void run() {
						File[] fiList = FileUtil.scanFileFromDir(file.getAbsolutePath(), FileType.ALL);

//								if (mFileNumberTxtLabel == null) {
//									mFileNumberTxtLabel = new Label();
//									jPanel.add(mFileNumberTxtLabel);
//								}
//
//								mFileNumberTxtLabel.setText("此文件夹下共有文件 ： " + fiList.length + " 个");

						final DefaultListModel<Object> d = new DefaultListModel<Object>();// 只有默认的模型有添加/删除方法

						for (File file : fiList) {
							d.addElement(
									file.getName() + "  \n " + DatetimeUtil.formatLongTypeTime(file.lastModified()));
						}
						final JList<Object> jList = new JList<Object>(d);
						jList.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (!jList.getValueIsAdjusting()) { // 设置只有释放鼠标时才触发
									System.out.println(jList.getSelectedValue());
								}
							}
						});

						scrollPane.setViewportView(jList);

					}
				}).start();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
