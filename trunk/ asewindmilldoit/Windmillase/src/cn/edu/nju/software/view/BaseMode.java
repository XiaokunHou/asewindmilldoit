package cn.edu.nju.software.view;

import java.awt.Choice;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * 所有模式（时间、项目、情境、联系人、归档库）的父类
 */

public class BaseMode extends JPanel implements ActionListener, ItemListener, FocusListener {

	protected JPanel imagePanel; // logo面板
	protected ImageIcon ic; // logo图标

	protected JPanel leftPanel; // 左边一列按钮所在面板
	protected ImageIcon leftPanelIcon;
	protected Choice choi;

	protected JPanel inputPanel;
	protected JLabel penIm;
	protected JTextField field;

	protected JToolBar toolBar; // 工具栏，包含搜索，设置，消息，登出四个选项
	protected ImageIcon toolbarIcon; // 工具栏背景图标
	protected JButton search;
	protected JButton set;
	protected JButton mes;
	protected JButton logout;

	protected JPanel taskPanel; // 任务面板

	protected TimerTask tt; // 控制右下角时间显示的TimeTask实例
	protected Timer timer; // 控制右下角时间显示的Timer实例
	protected JLabel timeLabel; // 时间标签

	protected ImageIcon backIcon; // 整个模式面板上的贴图

	public BaseMode() {

		this.setLayout(null);

		// 整个界面左上角的logo
		ic = new ImageIcon("src/res/logo.png");
		imagePanel = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(ic.getImage(), 0, 0, 170, 70, this);
			}
		};
		imagePanel.setBounds(25, 30, 175, 70);
		imagePanel.setLayout(null);
		this.add(imagePanel);

		// 界面的左面板
		leftPanelIcon = new ImageIcon("src/res/leftback.png");
		leftPanel = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(leftPanelIcon.getImage(), 0, 0, 155, 450, this);
			}
		};
		leftPanel.setBounds(33, 112, 155, 450);
//		leftPanel.setBackground(new Color(177, 139, 109));
		leftPanel.setLayout(null);
		this.add(leftPanel);

		// 每个模式下的左面板都必须有模式单选框
		choi = new Choice();
		choi.add("时间");
		choi.add("情境");
		choi.add("项目");
		choi.add("联系人");
		choi.add("归档库");

		choi.setBounds(5, 5, 130, 30);
		leftPanel.add(choi);

		inputPanel = new JPanel();
		inputPanel.setBounds(205, 66, 550, 30);
		inputPanel.setLayout(null);
		this.add(inputPanel);

		field = new JTextField("输入后回车，所添加的任务将进入时间、情境、项目的收集箱");
		field.setBounds(30, 2, 520, 25);
		field.setBorder(null);
		inputPanel.add(field);

		penIm = new JLabel(new ImageIcon("src/res/pen.png"));
		penIm.setBounds(0, 0, 25, 30);
		inputPanel.add(penIm);
		// field.setBorder(new LineBorder(Color.red, 1));

		ImageIcon ser = new ImageIcon("src/res/search.png");
		search = new JButton(ser);
		search.setBounds(0, 0, 25, 25);

		ImageIcon se = new ImageIcon("src/res/set.png");
		set = new JButton(se);
		set.setBounds(50, 0, 25, 25);

		ImageIcon me = new ImageIcon("src/res/mes.png");
		mes = new JButton(me);
		mes.setBounds(100, 0, 25, 25);
		this.add(mes);

		ImageIcon lo = new ImageIcon("src/res/logout.png");
		logout = new JButton(lo);
		logout.setBounds(150, 0, 25, 25);
		this.add(logout);
		logout.addActionListener(this);

		// 工具栏添加上面四个按钮
		toolBar = new JToolBar();
		toolBar.setOpaque(false);	//设置工具栏背景透明
		
		/*下面这种方法作为匿名内部类，重写paintComponent空方法也行
		 {public void paintComponent(Graphics g) {}};*/
		toolBar.setBounds(700, 25, 175, 25);
		toolBar.setBorderPainted(false);
		toolBar.setLayout(null);
		toolBar.setFloatable(true); // 工具栏浮动属性设置为True,即工具栏可以自由拖动到想要的位置
		toolBar.add(search);
		toolBar.add(set);
		toolBar.add(mes);
		toolBar.add(logout);
		this.add(toolBar);
		
		//为输入文本框添加焦点事件和键盘事件
		field.addFocusListener(this);
		field.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					System.out.println(field.getText().trim());
					field.setText("");
				}
			}
		});
		

		// 为工具栏添加监听
		search.addActionListener(this);
		set.addActionListener(this);
		mes.addActionListener(this);
		logout.addActionListener(this);

		// 设置工具栏按钮的鼠标悬停弹出消息
		search.setToolTipText("搜索");
		set.setToolTipText("设置");
		mes.setToolTipText("消息");
		logout.setToolTipText("退出");

		// 显示任务列表的JPanel
		taskPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				ImageIcon taskpanelIcon = new ImageIcon("src/res/mail4.jpg");
				g.drawImage(taskpanelIcon.getImage(), 0, 0, 583, 360, null);
			}
		};
		taskPanel.setBounds(207, 152, 583, 360);
		taskPanel.setLayout(null);
		this.add(taskPanel);

		timeLabel = new JLabel();
		timeLabel.setBounds(702, 544, 146, 20);
		this.add(timeLabel);

		tt = new TimerTask() {
			public void run() {
				timeLabel.setText(new Date().toLocaleString());
			}
		};
		timer = new Timer();
		timer.schedule(tt, 0, 1000);

		// 为模式单选框添加事件监听
		choi.addItemListener(this);

	}

	public void paintComponent(Graphics g) {
		backIcon = new ImageIcon("src/res/background3.png");
		g.drawImage(backIcon.getImage(), 0, 0, 898, 617, null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logout)
			System.exit(0);
		else if (e.getSource() == search) {
			new searchDia();
		} else if (e.getSource() == set) {
			new setDia();
		}
	}

	public void itemStateChanged(ItemEvent ie) {
		if (ie.getItemSelectable() == choi) {
			if (choi.getSelectedItem().equals("时间")) {
				MainFrame.crd.show(MainFrame.mainPanel, "0");
			}
			else if (choi.getSelectedItem()=="情境") {
				MainFrame.crd.show(MainFrame.mainPanel, "1");
			}
			else if (choi.getSelectedItem()=="项目") {
				MainFrame.crd.show(MainFrame.mainPanel, "2");
			}
			else if (choi.getSelectedItem()=="联系人") {
				MainFrame.crd.show(MainFrame.mainPanel, "3");
			}
			else if(choi.getSelectedItem()=="归档库"){
				MainFrame.crd.show(MainFrame.mainPanel, "4");
			}
			else{
				
			}
		}
		
	}

	public void focusGained(FocusEvent fe) {
		if(fe.getSource()==field){
			field.setText("");
		}
	}

	public void focusLost(FocusEvent fe) {
		if(fe.getSource()==field){
			field.setText("输入后回车，所添加的任务将进入时间、情境、项目的收集箱");
		}
	}

}
