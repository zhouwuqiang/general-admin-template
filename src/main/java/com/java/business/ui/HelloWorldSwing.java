package com.java.business.ui;

import javax.swing.*;

/**
 * @author manbu
 * @description
 * @date 2019/12/615:01
 */
public class HelloWorldSwing {

	/**
	 * {
	 * 创建并显示GUI。出于线程安全的考虑，
	 * 这个方法在事件调用线程中调用。
	 */
	private static void createAndShowGUI() {
		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);

		// 创建及设置窗口
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 添加 "Hello World" 标签
		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);

		// 显示窗口
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {


		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格

		// 1. 创建一个顶层容器（窗口）
		JFrame jf = new JFrame("测试窗口");          // 创建窗口

//		jf.setUndecorated(true); // 去掉窗口的装饰
		jf.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG );
		jf.setSize(250, 600);                       // 设置窗口大小
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）

		// 2. 创建中间容器（面板容器）
		JPanel panel = new JPanel();                // 创建面板容器，使用默认的布局管理器

		// 3. 创建一个基本组件（按钮），并添加到 面板容器 中
		JButton btn = new JButton("测试按钮");
		panel.add(btn);

		// 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
		jf.setContentPane(panel);

		// 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
		jf.setVisible(true);
	}
}
