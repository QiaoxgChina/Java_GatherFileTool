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
		final JMenuItem newItem = new JMenuItem("�½���New��");// �����˵���
		newItem.addActionListener(new ItemListener());
		mnFile.add(newItem);
		final JMenuItem secletItem = new JMenuItem("ѡ���ļ��У�Select��");// �����˵���
		secletItem.addActionListener(new ItemListener());
		mnFile.add(secletItem);
		menuBar.add(mnFile);

		JMenu mnEdit = new JMenu("Edit");
		final JMenuItem copyItem = new JMenuItem("���ƣ�Copy��");// �����˵���
		copyItem.addActionListener(new ItemListener());
		mnEdit.add(copyItem);
		final JMenuItem cutItem = new JMenuItem("���У�Cut��");// �����˵���
		cutItem.addActionListener(new ItemListener());
		mnEdit.add(cutItem);
		menuBar.add(mnEdit);

		JMenu mnHelp = new JMenu("Help");
		final JMenuItem aboutItem = new JMenuItem("���ڣ�About��");// �����˵���
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
			System.out.println("���������ǲ˵��" + menuItem.getText());
			if (menuItem.getText().equals("ѡ���ļ��У�Select��")) {
				showFileOpenDialog();
			}
		}
	}

	public void showFileOpenDialog() {

		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("."));// ����Ĭ����ʾΪ��ǰ�ļ���
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// ����ѡ��ģʽ��ֻѡ�ļ���ֻѡ�ļ��С��ļ����ļ�����ѡ��
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
			try {
				File file = fc.getSelectedFile();// ��ȡ�򿪵��ļ�
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
//								mFileNumberTxtLabel.setText("���ļ����¹����ļ� �� " + fiList.length + " ��");

						final DefaultListModel<Object> d = new DefaultListModel<Object>();// ֻ��Ĭ�ϵ�ģ�������/ɾ������

						for (File file : fiList) {
							d.addElement(
									file.getName() + "  \n " + DatetimeUtil.formatLongTypeTime(file.lastModified()));
						}
						final JList<Object> jList = new JList<Object>(d);
						jList.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (!jList.getValueIsAdjusting()) { // ����ֻ���ͷ����ʱ�Ŵ���
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
