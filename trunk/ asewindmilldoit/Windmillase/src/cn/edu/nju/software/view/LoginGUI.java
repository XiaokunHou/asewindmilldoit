package cn.edu.nju.software.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.control.LoginControl;
import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.control.LoginControl.Login;
import cn.edu.nju.software.database.User;


public class LoginGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int width = 340;
	private int height = 220;
	
	private MyJPanel mainpanel;
	private JPanel imagePanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JPanel faultPanel;
	
	private ImageIcon image =new ImageIcon("src/res/GTDT.jpg");
	private ImageIcon backImage =new ImageIcon("src/res/back1.jpg");
	

	private JLabel imageLabel;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JLabel faultLabel;
	private JButton login;
	private  JButton sign;
	LoginControl control=new LoginControl();//登陆控制
	User us;//一个登陆完成后生成一个user对象
	LocalDataControl localcontrol;//本地数据缓存
	boolean online=false;
	boolean lo=false;//登陆或失败都会为true
	public LoginGUI(){
		
		this.setSize(width, height);
		this.setLayout(null);
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width-width)/2, (screen.height-height)/2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		mainpanel = new MyJPanel(backImage.getImage());
		mainpanel.setSize(width, height);
		this.add(mainpanel);
		mainpanel.setLayout(null);
		
		imagePanel= new JPanel();
		imagePanel.setLayout(new GridLayout(1,1));
		imagePanel.setOpaque(false);
		
		
		
		textPanel= new JPanel();
		textPanel.setLayout(new GridLayout(2,2));
		textPanel.setOpaque(false);
		
		buttonPanel= new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.setOpaque(false);
		
		faultPanel= new JPanel();
		faultPanel.setLayout(new GridLayout(1,1));
		faultPanel.setOpaque(false);
		
		imageLabel = new JLabel();
		imageLabel.setIcon(image);
		imagePanel.add(imageLabel);
		
		userLabel = new JLabel("用户名:");
		userLabel.setHorizontalAlignment(JTextField.RIGHT);
		textPanel.add(userLabel);
		
		userText = new JTextField(15);
		textPanel.add(userText);
		userText.addFocusListener(new UserTextListener());
		
		passwordLabel = new  JLabel("密  码:");
		passwordLabel.setHorizontalAlignment(JTextField.RIGHT);
		textPanel.add(passwordLabel);
		
		passwordText = new JPasswordField(15);
		textPanel.add(passwordText);
		passwordText.addFocusListener(new PassWordTextListener());
		
		setFaultLabel(new  JLabel());
		faultPanel.add(getFaultLabel());
		faultLabel.setHorizontalAlignment(JLabel.CENTER);
		Font faultFont=new Font("宋体",Font.BOLD,11);
		faultLabel.setFont(faultFont);
		
		login = new JButton("登录");
		buttonPanel.add(login);
		login.addActionListener(new LoginListener());
		
		sign = new JButton("快速注册");
		buttonPanel.add(sign);
		sign.addActionListener(new RegisterListener());
	
		imagePanel.setBounds(90, 10, 126, 53);
		textPanel.setBounds(10, 78, 220, 50);
		buttonPanel.setBounds(70, 159, 180, 30);
		faultPanel.setBounds(20, 129, 310, 20);
		
		mainpanel.add(imagePanel);
		mainpanel.add(textPanel);
		mainpanel.add(buttonPanel);
		mainpanel.add(faultPanel);
		
	}
	
	public LoginGUI getThis(){
		return this;
	}
	
	public void setFaultLabel(JLabel faultLabel) {
		this.faultLabel = faultLabel;
	}

	public JLabel getFaultLabel() {
		return faultLabel;
	}

	private class UserTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			String s  = userText.getText();
			int length = s.length();
			userText.setSelectionStart(0);
			userText.setSelectionEnd(length);
			userText.setBorder(new LineBorder(Color.black,0));
			getFaultLabel().setText("");
		}
		public void focusLost(FocusEvent e){
			String s  = userText.getText();
			if(!format(s)){
				userText.setBorder(new LineBorder(Color.red,1));
				getFaultLabel().setText("用户名错误，只能以字母开头,不包含特殊字符,多于六位");
			}else{
				userText.setBorder(new LineBorder(Color.black,1));
				getFaultLabel().setText("");
			}
		}
		public boolean format(String s){
			String format = "^[a-z A-Z][a-z A-Z 0-9]{5,}$";;
			return s.matches(format);
		}
	}
	
	private class PassWordTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			char[] passwords = passwordText.getPassword();
			String password = turnCharsToString(passwords);
			int length = password.length();
			passwordText.setSelectionStart(0);
			passwordText.setSelectionEnd(length);
		}
		private String turnCharsToString(char[] chars){
			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
			}
			return strBuf.toString().trim();
		}
		
	}
	
	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// 调用LoginControl,判断用户名是否正确
			
			String name = userText.getText();
			char[] passwords = passwordText.getPassword();
			String password = turnCharsToString(passwords);
			//与LoginControl交互
			control.logi=Login.WAITING;
			us=new User();
			us.setInfo(name, password);
			us.setOpration(Operation.QUERY);
		    online=	control.getLogin(us);
		    if(online){
		    	System.out.println("联网成功");
		    	Thread x=new Thread(control);
				x.start();
				Listener p=new Listener();
				p.start();
		    }
		    else{
		    	System.out.println("联网失败，开始本地登陆");
		    	localcontrol=new LocalDataControl();
		    	localcontrol.loadFromLocal();
		        boolean  login= control.getLocalLogin(localcontrol,us);
		      if(login){
		    	setVisible(false);
				new MainFrame().setLocalDataControl(localcontrol);
				MainFrame.crd.first(MainFrame.mainPanel);
		        }else
		        {
		        	userText.setBorder(new LineBorder(Color.red,1));
					getFaultLabel().setText("用户名密码不正确");
		        }
		    }
			
			/*boolean s = true;
			if(s){
				setVisible(false);
				new MainFrame();
				MainFrame.crd.first(MainFrame.mainPanel);
			}else{
				userText.setBorder(new LineBorder(Color.red,1));
				getFaultLabel().setText("用户名密码不正确");
			}*/
		}
		
		private String turnCharsToString(char[] chars){
			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
			}
			return strBuf.toString().trim();
		}
	}
	
	private class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//进入注册界面
			RegisterGUI rg =new RegisterGUI(getThis());
			setVisible(false);
		}
	}
	class Listener extends Thread{
		public void run(){
		while(true){
			Login x=control.logi;
			switch(x){
			case SUCCESS:
				System.out.println("登陆成功");
				//getFaultLabel().setText("");
				localcontrol=new LocalDataControl(control);
				localcontrol.setUser(us);
				localcontrol.updateLocalData();
				lo=true;
				break;
			case FAILED:
				System.out.println("登陆失败");
				userText.setBorder(new LineBorder(Color.red,1));
				getFaultLabel().setText("用户名密码错误");
				lo=true;
				break;
			case WAITING:
				System.out.println("登陆中");
				lo=false;
				break;
			 }
			if(lo){
				break;
			  }
			}
		while(!localcontrol.getDataComplete()){
			System.out.println("更新数据中");
		  }
		if(localcontrol.getDataComplete()){
			System.out.println("更新完毕");
			setVisible(false);
			new MainFrame().setLocalDataControl(localcontrol);
			MainFrame.crd.first(MainFrame.mainPanel);
		 }
		}
	}
	//Listener类为执行登陆后方法
}
