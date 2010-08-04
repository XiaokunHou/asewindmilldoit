package cn.edu.nju.software.view;

/*
 ��������ʹ�÷����������½�һ���̶���С��JFrame��JWindow��������һ��JPanel(��С�������Ϊ240,280
 ���������ӵ����漴��
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

	// ����ͷ��壬����������ť������΢���ı���
	private JPanel headPanel;
	private JButton jbBack;
	private JButton jbForward;
	private JSpinner yearSpinner;
	private JSpinner monthSpinner;
	private String[] monthNames = new DateFormatSymbols().getMonths(); // һ��ʮ�����µ�����

	// ������ʾ���ں����ڵ�JButton
	private JButton[] jbDays = new JButton[49];

	private Calendar calendar;
	private int month; 	// �ض����·�
	private int year; 	// �ض�����
	private int date; 	// �ض������ڣ���ѡ�еı��µ�����
	private Date d; 	// ��ѡ�е�����

	private int startingDayOfMonth; // ��ǰ�µ�һ���Ӧ���ڣ�ע����������1������һ��2��������������7
	private int daysInTheMonth; // ���µ�����

	public CalendarPanel() {

		this.setBounds(0, 0, 210, 240);
		this.setLayout(null);

		headPanel = new JPanel();
		headPanel.setLayout(null); // ��
		headPanel.setBounds(0, 0, 210, 30);
		this.add(headPanel, BorderLayout.NORTH);

		ImageIcon backIcon = new ImageIcon("src/res/back.png");
		jbBack = new JButton(backIcon);
		jbBack.setBounds(2, 2, 25, 25);
		jbBack.setToolTipText("��һ����");
		headPanel.add(jbBack);

		ImageIcon forwardIcon = new ImageIcon("src/res/forward.png");
		jbForward = new JButton(forwardIcon);
		jbForward.setBounds(183, 2, 25, 25);
		jbForward.setToolTipText("��һ����");
		headPanel.add(jbForward);

		monthSpinner = new JSpinner(new SpinnerListModel(Arrays.asList(
				monthNames).subList(0, 12)));
		monthSpinner.setBounds(35, 2, 60, 25);
		headPanel.add(monthSpinner);

		yearSpinner = new JSpinner(new SpinnerNumberModel(
				new GregorianCalendar().get(Calendar.YEAR), 2000, 2020, 1));
		yearSpinner.setBounds(115, 2, 60, 25);
		headPanel.add(yearSpinner);

		// �����������ں����ڵ����
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
			jbDays[i].setContentAreaFilled(false); // ���ð�ť͸��
			jbDays[i].setHorizontalAlignment(JLabel.CENTER);
			jbDays[i].setVerticalAlignment(JLabel.CENTER);

		}

		// ������ͷ����������ӵ�������壬BorderLayout����
		this.add(headPanel);
		this.add(jpDays);

		// ���õ�ǰ���·ݺ����
		calendar = new GregorianCalendar();
		month = calendar.get(Calendar.MONTH) + 1;
		year = calendar.get(Calendar.YEAR);

		// ����������ť(ǰһ���¡���һ���£��ļ���
		jbBack.addActionListener(this);
		jbForward.addActionListener(this);

		// ��������΢���ı�����¼�����
		monthSpinner.addChangeListener(this);
		yearSpinner.addChangeListener(this);
		// ��ʾ����
		showHeader();
		showDayNames();
		showDays();

	}

	// ���ݵ�ǰ����ʱ����������ͷ����ݺ��·�
	private void showHeader() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", getLocale());
		String header = sdf.format(calendar.getTime());
		monthSpinner.setValue(header.split(" ")[0]);
		yearSpinner.setValue(Integer.parseInt(header.split(" ")[1]));
	}

	// ��ʾ����ʱ����������������һ������
	private void showDayNames() {
		DateFormatSymbols dfs = new DateFormatSymbols(getLocale());
		String dayNames[] = dfs.getWeekdays();

		for (int i = 0; i < 7; i++) {
			jbDays[i].setText(dayNames[i + 1].substring(2, 3));
		}
	}

	// ��ʾ��ǰ�µ�����
	private void showDays() {

		// ����calendarʵ��Ϊ��ǰ�µĵ�һ��
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);

		// ��ǰ�µ�һ���Ӧ���ڣ�ע����������1������һ��2��������������7
		startingDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK); // $

		// System.out.println("startingDayOfMonth������µ�һ���Ӧ����������: " +
		// startingDayOfMonth); //����µ�һ���Ӧ��������

		// ��д����ǰһ���µ�����
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

		// ��ʾ����µ�����
		daysInTheMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // ���µ�����
		// $
		for (int i = 1; i <= daysInTheMonth; i++) {
			jbDays[i - 2 + startingDayOfMonth + 7].setForeground(Color.black);
			jbDays[i - 2 + startingDayOfMonth + 7].setText(i + "");
			jbDays[i - 2 + startingDayOfMonth + 7].addActionListener(this); // $
		}

		// ��ʾ��������һ���µ�����
		int j = 1;
		for (int i = daysInTheMonth + startingDayOfMonth + 6; i < 49; i++) {
			jbDays[i].setForeground(Color.gray);
			jbDays[i].setText(j++ + "");
			jbDays[i].setEnabled(false); // $
		}
		showHeader();

	}

	// ������
	public int getMonth() {
		return month;
	}

	// ������
	public void setMonth(int month) {
		this.month = month;
		showDays();
	}

	// ������
	public int getYear() {
		return year;
	}

	// ������
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
			if (month == "һ��") {
				this.setMonth(1);
			} else if (month == "����") {
				this.setMonth(2);
			} else if (month == "����") {
				this.setMonth(3);
			} else if (month == "����") {
				this.setMonth(4);
			} else if (month == "����") {
				this.setMonth(5);
			} else if (month == "����") {
				this.setMonth(6);
			} else if (month == "����") {
				this.setMonth(7);
			} else if (month == "����") {
				this.setMonth(8);
			} else if (month == "����") {
				this.setMonth(9);
			} else if (month == "ʮ��") {
				this.setMonth(10);
			} else if (month == "ʮһ��") {
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
