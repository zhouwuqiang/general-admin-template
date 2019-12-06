package com.java.business.ui;

import com.java.business.ui.module.ScrollBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.*;


public class MServer extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int i = 0;
	static int runTime = 0;
	static JTextArea ta = new JTextArea();
	static boolean regStatus = false;
	private static TrayIcon trayIcon = null;
	static JFrame mf = new JFrame();
	static SystemTray tray = SystemTray.getSystemTray();

	public static void myFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException { // 窗体
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		mf.setLocation(300, 100);
		mf.setSize(500, 300);
		mf.setTitle("XXXX系统");
		mf.setLayout(new BorderLayout());
		mf.setIconImage(new ImageIcon("E://Users//manbu//Pictures//favicon.png").getImage());
		ImageIcon ll = new ImageIcon("E://Users//manbu//Pictures//favicon.png");//在JFrame中使用图片
		JLabel i = new JLabel(ll);

		JLabel j = new JLabel("XXXX系统v2.02", SwingConstants.CENTER);//设置标签，显示文本居中
		JLabel k = new JLabel("XXXX", SwingConstants.CENTER);//设置标签，显示文本居中
		j.setFont(new java.awt.Font("", 1, 18));//设置标签J显示字体

		Panel p1 = new Panel();//实例化面板P1
		p1.setLayout(new BorderLayout());//设置面板P1中控件排列方式

		final Panel p11 = new Panel();
		p11.setLayout(new BorderLayout());//设置P11控件排列方式
		p11.add(j, BorderLayout.NORTH);//P11上方显示控件J
		p11.add(k, BorderLayout.SOUTH);//P11下方显示控件K

		final JLabel t = new JLabel("", SwingConstants.CENTER);//设置标签t用于显示时钟
		t.setFont(new java.awt.Font("", 1, 26));//设置标签t字体
		t.setForeground(Color.blue);//设置标签t前景色彩

		p11.add(t, BorderLayout.CENTER);//标签t显示在中间位置

		Timer timer_date = new Timer(1000, new ActionListener() {//设置数字时钟
			public void actionPerformed(ActionEvent evt) {
				t.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));

			}
		}
		);
		timer_date.start();


		p1.add(i, BorderLayout.WEST);//左侧图标
		p1.add(p11, BorderLayout.CENTER);//p11在P1中间排列

		mf.add(p1, BorderLayout.NORTH);//将p1显示在窗口上方


		mf.setVisible(true);//使窗口可见

		mf.addWindowListener(new WindowAdapter() {

			/**
			 * 窗口关闭事件
			 * @param e
			 */
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(mf, "确认退出?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}

			/**
			 * 窗口最小化事件
			 * @param e
			 */
			public void windowIconified(WindowEvent e) {
				mf.setVisible(false);
				MServer.miniTray();

			}
		});

		//不执行任何操作;要求程序在已注册的 WindowListener 对象的 windowClosing 方法中处理该操作
		mf.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);



		JScrollPane scroll = new JScrollPane(ta);
		scroll.getVerticalScrollBar().setUI(new ScrollBar());
		scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


		ta.setBackground(Color.DARK_GRAY);
		ta.setCaretColor(Color.white);
		ta.setSelectedTextColor(Color.white);
		ta.setSelectionColor(Color.black);
		ta.setForeground(Color.LIGHT_GRAY);
		mf.add(scroll, BorderLayout.CENTER);//将一个多行文本区域显示在文体中间
		while (true) {
			ta.append(new Date().toString());
			ta.append("\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 窗口最小化到任务栏托盘
	 */
	private static void miniTray() {

		ImageIcon trayImg = new ImageIcon("E://Users//manbu//Pictures//favicon.png");//托盘图标

		PopupMenu pop = new PopupMenu();//增加托盘右击菜单
		MenuItem show = new MenuItem("还原");
		MenuItem exit = new MenuItem("退出");

		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // 按下还原键

				tray.remove(trayIcon);
				mf.setVisible(true);
				mf.setExtendedState(JFrame.NORMAL);
				mf.toFront();
			}

		});

		exit.addActionListener(new ActionListener() { // 按下退出键

			public void actionPerformed(ActionEvent e) {

				tray.remove(trayIcon);
				System.exit(0);

			}

		});

		pop.add(show);
		pop.add(exit);

		trayIcon = new TrayIcon(trayImg.getImage(), "xxxx系统", pop);
		trayIcon.setImageAutoSize(true);

		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) { // 鼠标器双击事件

				if (e.getClickCount() == 2) {

					tray.remove(trayIcon); // 移去托盘图标
					mf.setVisible(true);
					mf.setExtendedState(JFrame.NORMAL); // 还原窗口
					mf.toFront();
				}

			}

		});

		try {

			tray.add(trayIcon);

		} catch (AWTException e1) {
			e1.printStackTrace();
		}

	}


	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

		new MServer();
		MServer.myFrame();

	}

}
