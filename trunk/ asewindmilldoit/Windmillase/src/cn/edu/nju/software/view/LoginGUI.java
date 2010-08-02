package cn.edu.nju.software.view;


import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cn.edu.nju.software.control.LoginControl;
import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.control.LoginControl.Login;
import cn.edu.nju.software.database.*;


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
	
	JLabel backLabel;
	private JLabel imageLabel;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JLabel faultLabel;
	private JButton login;
	private  JButton sign;
	LoginControl control=new LoginControl();//登陆控制
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
		//control.SetLoginGUI(this);//传入参数
		
	}
	
	public void loginSuccess(){
		System.out.println("登陆成功");
	}
	public void loginFailed(){

		userText.setBorder(new LineBorder(Color.red,1));
		getFaultLabel().setText("用户名密码错误");
	}
	//为Control调用的登陆后方法
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
				getFaultLabel().setText("用户名错误，只能以字母开头,不包含特殊字符,超过六位");
			}else{
				userText.setBorder(new LineBorder(Color.black,0));
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
	class Listener extends Thread{
		public void run(){
		while(true){
			Login x=control.logi;
			switch(x){
			case SUCCESS:
				System.out.println("登陆成功");
				getFaultLabel().setText("");
				//new MainFrame();
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
		}
	}
	//Listener类为执行登陆后方法
	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// 调用LoginControl,判断用户名是否正确
			
			String name = userText.getText();
			char[] passwords = passwordText.getPassword();
			String password = turnCharsToString(passwords);
			//与LoginControl交互
			control.logi=Login.WAITING;
			User us=new User();
			us.setInfo(name, password);
			us.setOpration(Operation.QUERY);
		    online=	control.getLogin(us);
		    if(online){
		    	//JOptionPane.showMessageDialog(null, "联网成功", "联网", JOptionPane.OK_CANCEL_OPTION);
		    	System.out.println("联网成功");
		    }
		    else{
		    	System.out.println("联网失败，载入本地最近数据");
		    	//JOptionPane.showMessageDialog(null, "联网失败，将载入本地最近登录人数据", "联网", JOptionPane.ERROR_MESSAGE);
		    }
			Thread x=new Thread(control);
			x.start();
			Listener p=new Listener();
			p.start();
			/*if(b){
				//new MainFrame();
				System.out.println("登陆成功");
			}else{
				
				userText.setBorder(new LineBorder(Color.red,1));
				getFaultLabel().setText("用户名密码错误");
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
			getThis().setVisible(false);
		}
	}
}
