package cn.edu.nju.software.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import cn.edu.nju.software.control.LocalDataControl;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 898;   // ....//...//.....//.....//...//
	private int height = 617;
	//private static ModeType modeType;
	//表明处于哪种模式下，有对应的get和set方法,ModeType是枚举类型
	public static JPanel mainPanel = new JPanel();	//最靠近框架的一层面板(JPanel)
	public static CardLayout crd = new CardLayout(0, 0);
	LocalDataControl localcontrol;
	public void setLocalDataControl(LocalDataControl x){
		localcontrol=x;
	}
	public MainFrame() {
		//localcontrol=control;
		Dimension dms = this.getToolkit().getScreenSize();
		int w = dms.width;
		int h = dms.height;
		int x = (w - this.width) / 2;
		int y = (h - this.height) / 2;
		setBounds(x, y, this.width, this.height);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		
		mainPanel.setBounds(0, 0, 898, 617);
		this.add(mainPanel);
		
		mainPanel.setLayout(crd);
		
		JPanel timePanel = new TimeMode();
		JPanel scenePanel = new SceneMode();
		JPanel projectPanel = new ProjectMode();
		JPanel contactPanel = new ContactMode();
		JPanel filePanel = new FileMode();
		
		mainPanel.add("0", timePanel);
		mainPanel.add("1", scenePanel);
		mainPanel.add("2", projectPanel);
		mainPanel.add("3", contactPanel);
		mainPanel.add("4", filePanel);
		
	}

	public static void main(String args[]) {
		new MainFrame().go();
		crd.first(mainPanel);
	}
	private void go() {
		// TODO Auto-generated method stub
		setLocalDataControl(localcontrol);
	}
	
}
