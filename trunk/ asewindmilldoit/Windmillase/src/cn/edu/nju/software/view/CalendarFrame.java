package cn.edu.nju.software.view;

import javax.swing.JFrame;

public class CalendarFrame extends JFrame {
	
	public CalendarPanel calendarPanel = new CalendarPanel();
	private static JFrame frame;
	
	public CalendarFrame(){
		frame = this;
		this.setSize(210, 240);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.add(calendarPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static JFrame getFrame(){
		return frame;
	}
	
	public static void main(String[]args){
		CalendarFrame frame = new CalendarFrame();
		frame.setVisible(true);
	}
	
}
