package cn.edu.nju.software.view;

/*
 日历面板的使用方法：可以新建一个固定大小的JFrame或JWindow或者另外一个JPanel(大小最好设置为240,280
 将日历面板加到里面即可
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CalendarPanel extends JPanel implements ActionListener,
		ChangeListener {

	// 日历头面板，包含两个按钮，两个微调文本域
	private JPanel headPanel;
	private JButton jbBack;
	private JButton jbForward;
	private JSpinner yearSpinner;
	private JSpinner monthSpinner;
	private String[] monthNames = new DateFormatSymbols().getMonths(); // 一年十二个月的名称

	// 用于显示星期和日期的JButton
	private JButton[] jbDays = new JButton[49];

	private Calendar calendar;
	private int month; 	// 特定的月份
	private int year; 	// 特定的年
	private int date; 	// 特定的日期，被选中的本月的日期
	private Date d; 	// 被选中的日期

	private int startingDayOfMonth; // 当前月第一天对应星期（注意星期日是1，星期一是2，……星期六是7
	private int daysInTheMonth; // 本月的天数

	public CalendarPanel() {

		this.setBounds(0, 0, 210, 240);
		this.setLayout(null);

		headPanel = new JPanel();
		headPanel.setLayout(null); // ？
		headPanel.setBounds(0, 0, 210, 30);
		this.add(headPanel, BorderLayout.NORTH);

		ImageIcon backIcon = new ImageIcon("src/res/back.png");
		jbBack = new JButton(backIcon);
		jbBack.setBounds(2, 2, 25, 25);
		jbBack.setToolTipText("上一个月");
		headPanel.add(jbBack);

		ImageIcon forwardIcon = new ImageIcon("src/res/forward.png");
		jbForward = new JButton(forwardIcon);
		jbForward.setBounds(183, 2, 25, 25);
		jbForward.setToolTipText("下一个月");
		headPanel.add(jbForward);

		monthSpinner = new JSpinner(new SpinnerListModel(Arrays.asList(
				monthNames).subList(0, 12)));
		monthSpinner.setBounds(35, 2, 60, 25);
		headPanel.add(monthSpinner);

		yearSpinner = new JSpinner(new SpinnerNumberModel(
				new GregorianCalendar().get(Calendar.YEAR), 2000, 2020, 1));
		yearSpinner.setBounds(115, 2, 60, 25);
		headPanel.add(yearSpinner);

		// 用于容纳星期和日期的面板
		JPanel jpDays = new JPanel() {
			public void paintComponent(Graphics g) {

				Graphics2D g2d = (Graphics2D) g;
				GradientPaint grd = new GradientPaint(0, 0, Color.white, 210,
						210, Color.darkGray, true);
				g2d.setPaint(grd);

				Rectangle2D obj = new Rectangle2D.Double(0, 0, 210, 210);
				g2d.fill(obj);
			}
		};
		jpDays.setLayout(null);
		jpDays.setBounds(0, 30, 210, 210);

		for (int i = 0; i < 49; i++) {
			jpDays.add(jbDays[i] = new JButton());
			jbDays[i].setBounds((i % 7) * 30, (i / 7) * 30, 30, 30);
			jbDays[i].setBorder(null);
			jbDays[i].setContentAreaFilled(false); // 设置按钮透明
			jbDays[i].setHorizontalAlignment(JLabel.CENTER);
			jbDays[i].setVerticalAlignment(JLabel.CENTER);

		}

		// 将日历头和日历主体加到日历面板，BorderLayout布局
		this.add(headPanel);
		this.add(jpDays);

		// 设置当前的月份和年份
		calendar = new GregorianCalendar();
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);

		// 设置两个按钮(前一个月、后一个月）的监听
		jbBack.addActionListener(this);
		jbForward.addActionListener(this);

		// 设置两个微调文本域的事件监听
		monthSpinner.addChangeListener(this);
		yearSpinner.addChangeListener(this);
		// 显示日历
		showHeader();
		showDayNames();
		showDays();

	}

	// 根据当前所在时区更新日历头的年份和月份
	private void showHeader() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", getLocale());
		String header = sdf.format(calendar.getTime());
		monthSpinner.setValue(header.split(" ")[0]);
		yearSpinner.setValue(Integer.parseInt(header.split(" ")[1]));
	}

	// 显示所在时区的星期名（星期一……）
	private void showDayNames() {
		DateFormatSymbols dfs = new DateFormatSymbols(getLocale());
		String dayNames[] = dfs.getWeekdays();

		for (int i = 0; i < 7; i++) {
			jbDays[i].setText(dayNames[i + 1].substring(2, 3));
		}
	}

	// 显示当前月的日期
	private void showDays() {

		// 设置calendar实例为当前月的第一天
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);

		// 当前月第一天对应星期（注意星期日是1，星期一是2，……星期六是7
		startingDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK); // $

		// System.out.println("startingDayOfMonth（这个月第一天对应的星期数）: " +
		// startingDayOfMonth); //这个月第一天对应的星期数

		// 填写日历前一个月的日期
		Calendar cloneCalendar = (Calendar) calendar.clone();
		cloneCalendar.add(Calendar.DATE, -1);
		int daysInLastMonth = cloneCalendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 0; i < startingDayOfMonth - 1; i++) {
			jbDays[i + 7].setForeground(Color.gray);
			jbDays[i + 7].setText(daysInLastMonth - startingDayOfMonth + 2 + i
					+ "");
			jbDays[i + 7].setEnabled(false); // $
		}

		// 显示这个月的日期
		daysInTheMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 本月的天数
		// $
		for (int i = 1; i <= daysInTheMonth; i++) {
			jbDays[i - 2 + startingDayOfMonth + 7].setForeground(Color.black);
			jbDays[i - 2 + startingDayOfMonth + 7].setText(i + "");
			jbDays[i - 2 + startingDayOfMonth + 7].addActionListener(this); // $
		}

		// 显示日历上下一个月的日期
		int j = 1;
		for (int i = daysInTheMonth + startingDayOfMonth + 6; i < 49; i++) {
			jbDays[i].setForeground(Color.gray);
			jbDays[i].setText(j++ + "");
			jbDays[i].setEnabled(false); // $
		}
		showHeader();

	}

	// 返回月
	public int getMonth() {
		return month;
	}

	// 设置月
	public void setMonth(int month) {
		this.month = month;
		showDays();
	}

	// 返回年
	public int getYear() {
		return year;
	}

	// 设置年
	public void setYear(int year) {
		this.year = year;
		showDays();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbBack) {
			int currentMonth = this.getMonth();
			if (currentMonth == 1) {
				this.setMonth(12);
				this.setYear(this.getYear() - 1);
			} else {
				this.setMonth(currentMonth - 1);
			}
		} else if (e.getSource() == jbForward) {
			int currentMonth = this.getMonth();
			if (currentMonth == 12) {
				this.setMonth(1);
				this.setYear(this.getYear() + 1);
			} else {
				this.setMonth(currentMonth + 1);
			}
		} else {
			for (int i = 1; i <= daysInTheMonth; i++) {
				if (e.getSource() == jbDays[i - 2 + startingDayOfMonth + 7]) {
					date = Integer.parseInt(jbDays[i - 2 + startingDayOfMonth
							+ 7].getText());
					d = new Date(year - 1900, month - 1, date);
					if(CalendarFlag.getFlag() == 0){
						FileMode.getPeriod().setText("");
					}
					else if(CalendarFlag.getFlag() == 1) {
						NewOreditpanel.getStart().setText(
								DateFormat.getDateInstance(DateFormat.MEDIUM)
										.format(d));
					} 
					else if(CalendarFlag.getFlag()==2) {
						NewOreditpanel.getEnd().setText(
								DateFormat.getDateInstance(DateFormat.MEDIUM)
										.format(d));
					}

				}
			}
			CalendarFrame.getFrame().dispose();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		if (e.getSource() == monthSpinner) {
			String month = (String) monthSpinner.getValue();
			if (month == "一月") {
				this.setMonth(1);
			} else if (month == "二月") {
				this.setMonth(2);
			} else if (month == "三月") {
				this.setMonth(3);
			} else if (month == "四月") {
				this.setMonth(4);
			} else if (month == "五月") {
				this.setMonth(5);
			} else if (month == "六月") {
				this.setMonth(6);
			} else if (month == "七月") {
				this.setMonth(7);
			} else if (month == "八月") {
				this.setMonth(8);
			} else if (month == "九月") {
				this.setMonth(9);
			} else if (month == "十月") {
				this.setMonth(10);
			} else if (month == "十一月") {
				this.setMonth(11);
			} else {
				this.setMonth(12);
			}
		} else if (e.getSource() == yearSpinner) {
			int yearValue = (Integer) yearSpinner.getValue();
			this.setYear(yearValue);
		}

	}

}
