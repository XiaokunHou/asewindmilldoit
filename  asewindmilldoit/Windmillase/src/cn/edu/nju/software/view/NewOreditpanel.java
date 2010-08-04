package cn.edu.nju.software.view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NewOreditpanel extends JDialog implements ActionListener {
	private JLabel taskL;
	private JTextField task;
	private JLabel timeL;

	private static JButton start;
	private static JButton end;
	
	private JLabel textL;
	private JTextArea text;
	private Choice todayOrtom;
	private JLabel ling;
	private Choice type;
	private JTextField sum;
	private Choice hour;
	private JButton tag;
	private JButton turn;
	private JLabel levelL;
	private JSlider level;

	private JButton save;
	private JButton over;
	private JButton delete;
	private JButton close;

	private CalendarFrame calendar = new CalendarFrame(); // 日历框架

	private JDialog dialog;

	public NewOreditpanel() {

		dialog = this;

		setSize(641, 394);
		this.setLayout(null);
		setTitle("编辑任务");
		setResizable(false);
		setVisible(true);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 641) / 2, (screen.height - 394) / 2);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dialog.dispose();
				calendar.dispose();
			}
		});

		taskL = new JLabel("任务：");
		this.add(taskL);
		taskL.setBounds(45, 25, 50, 15);
		task = new JTextField();
		
		this.add(task);
		task.setBounds(90, 25, 285, 20);
		timeL = new JLabel("时间：");
		this.add(timeL);
		timeL.setBounds(45, 72, 50, 15);
		start = new JButton("开始时间");
		start.setBackground(Color.gray);
		start.setBounds(90, 70, 100, 20);
		this.add(start);

		end = new JButton("截止时间");
		end.setBackground(Color.gray);
		end.setBounds(213, 70, 100, 20);
		this.add(end);

		todayOrtom = new Choice();
		todayOrtom.add("收集箱");
		todayOrtom.add("今日待办");
		todayOrtom.add("等待");
		todayOrtom.add("明日待办");
		todayOrtom.add("日程");
		todayOrtom.add("择日待办");
		this.add(todayOrtom);
		todayOrtom.setBounds(330, 70, 70, 20);
		textL = new JLabel("备注：");
		this.add(textL);
		textL.setBounds(45, 115, 50, 15);
		text = new JTextArea();
		this.add(text);
		text.setBounds(90, 115, 320, 190);

		ling = new JLabel("添加提醒");
		this.add(ling);
		ling.setBounds(460, 50, 100, 15);
		type = new Choice();
		type.add("邮件");
		type.add("提醒");
		this.add(type);
		type.setBounds(460, 75, 48, 20);
		sum = new JTextField();
		this.add(sum);
		sum.setBounds(510, 75, 25, 20);
		hour = new Choice();
		hour.add("分钟");
		hour.add("小时");
		hour.add("天");
		this.add(hour);
		hour.setBounds(535, 75, 48, 20);

		tag = new JButton("标签");
		this.add(tag);
		turn = new JButton("转发");
		this.add(turn);
		tag.setBounds(460, 120, 70, 20);
		this.add(turn);
		turn.setBounds(460, 155, 70, 20);
		tag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(null, "例：标签1，标签2", "添加标签",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		turn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(null, "例：huotuotuofly", "转发给",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});

		levelL = new JLabel("优先级：");
		this.add(levelL);
		levelL.setBounds(460, 210, 60, 20);
		level = new JSlider(0, 100, 0);
		this.add(level);
		level.setBounds(460, 235, 120, 60);
		level.setMajorTickSpacing(50);
		// level.setMinorTickSpacing(20);
		level.setPaintLabels(false);
		level.setPaintTicks(true);
		JLabel low = new JLabel("低");
		JLabel min = new JLabel("中");
		JLabel high = new JLabel("高");
		low.setBounds(460, 295, 15, 20);
		min.setBounds(515, 295, 15, 20);
		high.setBounds(567, 295, 15, 20);
		this.add(low);
		this.add(min);
		this.add(high);
		level.setSnapToTicks(true);
		level.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider jsl2 = (JSlider) e.getSource();// 得到jsl对象
				if (!jsl2.getValueIsAdjusting())// 判断是否还在移动滑条
					System.out.println(jsl2.getValue());
			}

		});

		save = new JButton("保存");
		over = new JButton("完成");
		delete = new JButton("删除");
		close = new JButton("关闭");
		this.add(save);
		this.add(over);
		this.add(delete);
		this.add(close);
		save.setBounds(118, 315, 70, 20);
		over.setBounds(198, 315, 70, 20);
		delete.setBounds(278, 315, 70, 20);
		close.setBounds(358, 315, 70, 20);

		// 为start和end文本域添加事件监听
		start.addActionListener(this);
		end.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			CalendarFlag.setFlag(1);
			calendar.dispose();
			calendar = new CalendarFrame();
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			calendar.setLocation((screen.width - 641) / 2 + 90,
					(screen.height - 394) / 2 + 120);
			calendar.setVisible(true);
		} else if (e.getSource() == end) {
			CalendarFlag.setFlag(2);
			calendar.dispose();
			calendar = new CalendarFrame();
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			calendar.setLocation((screen.width - 641) / 2 + 213,
					(screen.height - 394) / 2 + 120);
			calendar.setVisible(true);
		}
	}

	public static JButton getStart() { // 获得起始日期按钮
		return start;
	}

	public static JButton getEnd() { // 获得终止日期按钮
		return end;
	}

}
