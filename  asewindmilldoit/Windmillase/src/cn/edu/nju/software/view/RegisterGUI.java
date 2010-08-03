package cn.edu.nju.software.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cn.edu.nju.software.control.RegisterControl;
import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.control.LoginControl.Login;
import cn.edu.nju.software.control.RegisterControl.Register;
import cn.edu.nju.software.database.User;
import cn.edu.nju.software.view.LoginGUI.Listener;

public class RegisterGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginGUI lg; 
	
	private int width = 350;
	private int height = 395;
	
	private MyJPanel registerPanel;
	
	private ImageIcon image =new ImageIcon("src/res/GTDT.jpg");
	private ImageIcon backImage =new ImageIcon("src/res/back.jpg");
	
	private JLabel imageLabel;
	private JLabel userLabel;
	private JLabel pwdLabel;
	private JLabel pwdAgainLabel;
	private JLabel mailLabel;
	private JLabel userHint;
	private JLabel userHint2;
	private JLabel pwdHint;
	private JLabel pwdAgainHint;
	private JLabel mailHint;
	private JLabel mailHint2;
	private JLabel mailFault;
	private JLabel userFault;
	private JLabel pwdFault;
	private JLabel pwdAgainFault;
	private JTextField userText;
	private JPasswordField pwdText;
	private JPasswordField pwdAgainText;
	private JTextField mailText;
	private JButton registerButton;
	private JButton resetButton;
	
	private JLabel successLabel;
	
	private boolean isUserRight = false;
	private boolean isPwdRight = false;
	private boolean isMailRight = false;
	//与RegisterControl 交互
	RegisterControl recontrol=new RegisterControl();
	boolean lo=false;//登陆或失败都会为true
	public RegisterGUI(LoginGUI lg1){
		lg = lg1;
		registerPanel = new MyJPanel(backImage.getImage());

		imageLabel = new JLabel();
		imageLabel.setIcon(image);
		
		userLabel = new JLabel("用户名:");
		userLabel.setHorizontalAlignment(JTextField.RIGHT);
		pwdLabel = new JLabel("密码:");
		pwdLabel.setHorizontalAlignment(JTextField.RIGHT);
		pwdAgainLabel = new JLabel("确认密码:");
		pwdAgainLabel.setHorizontalAlignment(JTextField.RIGHT);
		mailLabel = new JLabel("邮箱:");
		mailLabel.setHorizontalAlignment(JTextField.RIGHT);
		
		Font faultFont=new Font("宋体",Font.ITALIC|Font.BOLD,15);
		userFault = new JLabel();
		userFault.setFont(faultFont);
		pwdFault = new JLabel();
		pwdFault.setFont(faultFont);
		pwdAgainFault = new JLabel();
		pwdAgainFault.setFont(faultFont);
		mailFault = new JLabel();
		mailFault.setFont(faultFont);
		
		userHint = new JLabel();
		userHint.setText("用户名错误，只能以字母开头");
		userHint2 = new JLabel();
		userHint2.setText("不包含特殊字符,超过六位");
		
		pwdHint = new JLabel();
		pwdHint.setText("密码只能由数字和字母组成，最短不能小于六位");
		pwdAgainHint = new JLabel();
		pwdAgainHint.setText("再次输入密码");
		mailHint = new JLabel();
		mailHint.setText("邮箱只能为139,yahoo,gmail,");
		mailHint2 = new JLabel();
		mailHint2.setText("sina,163,126中的一种");
		
		userText = new JTextField(15);
		userText.addFocusListener(new UserTextListener());
		mailText = new JTextField(30);
		mailText.addFocusListener(new MailTextListener());
		pwdText = new JPasswordField(15);
		pwdText.addFocusListener(new PassWordTextListener());
		pwdAgainText = new JPasswordField(15);
		pwdAgainText.addFocusListener(new PassWordAgainTextListener());
		
		registerButton = new JButton("快速注册");
		registerButton.addActionListener(new RegisterListener(this,lg));
		resetButton = new JButton("重新填写");
		resetButton.addActionListener(new ResetListener());
		
		
		
		registerPanel.add(imageLabel);
		registerPanel.add(userLabel);
		registerPanel.add(userText);
		registerPanel.add(userHint);
		registerPanel.add(userHint2);
		registerPanel.add(pwdLabel);
		registerPanel.add(pwdHint);
		registerPanel.add(pwdText);
		registerPanel.add(pwdAgainLabel);
		registerPanel.add(pwdAgainText);
		registerPanel.add(pwdAgainHint);	
		registerPanel.add(mailLabel);
		registerPanel.add(mailText);
		registerPanel.add(mailHint);		
		registerPanel.add(mailHint2);	
		registerPanel.add(registerButton);
		registerPanel.add(resetButton);
		registerPanel.add(userFault);
		registerPanel.add(pwdFault);
		registerPanel.add(pwdAgainFault);
		registerPanel.add(mailFault);
		registerPanel.setLayout(null);
		
		imageLabel.setBounds(110, 10, 126, 53);
		userLabel.setBounds(10, 73, 80, 20);
		userText.setBounds(99, 73, 160, 20);
		userFault.setBounds(260, 73,100, 20);
		userHint.setBounds(99,93,160,20);
		userHint2.setBounds(99,113,160,20);
		pwdLabel.setBounds(10, 137, 80, 20);
		pwdText.setBounds(99, 137, 160, 20);
		pwdFault.setBounds(260, 137, 100, 20);
		pwdHint.setBounds(99, 157, 160, 20);
		pwdAgainLabel.setBounds(10, 180, 80, 20);
		pwdAgainText.setBounds(99,180,160,20);
		pwdAgainFault.setBounds(260,180,100,20);
		pwdAgainHint.setBounds(99,200,160,20);
		mailLabel.setBounds(10,224, 80, 20);
		mailText.setBounds(99,224,160,20);
		mailFault.setBounds(260,224,100,20);
		mailHint.setBounds(99,244,160,20);
		mailHint2.setBounds(99,264,160,20);
		registerButton.setBounds(70, 300, 90, 40);
		resetButton.setBounds(170, 300, 90, 40);
		
		this.add(registerPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(width, height);
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width-width)/2, (screen.height-height)/2);
		this.setVisible(true);
		this.setResizable(false);
		this.addWindowListener(new CloseHandler(this,lg));
	}
	public RegisterGUI getThis(){
		return this;
	}

	
	private class UserTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			String s  = userText.getText();
			int length = s.length();
			userText.setSelectionStart(0);
			userText.setSelectionEnd(length);
		}
		public void focusLost(FocusEvent e){
			String s  = userText.getText();
			if(!format(s)){
				userText.setBorder(new LineBorder(Color.red,1));
				userFault.setText("用户名有误");
				isUserRight = false;
			}else{
				userText.setBorder(new LineBorder(Color.black,0));
				userFault.setText("");
				isUserRight = true;
			}
		}
		public boolean format(String s){
			String format = "^[a-z A-Z][a-z A-Z 0-9]{5,}$";;
			return s.matches(format);
		}
	}
	
	
	private class PassWordTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			char[] passwords = pwdText.getPassword();
			String password = turnCharsToString(passwords);
			int length = password.length();
			pwdText.setSelectionStart(0);
			pwdText.setSelectionEnd(length);
		}
		private String turnCharsToString(char[] chars){
			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
			}
			return strBuf.toString().trim();
		}
	}
	private class PassWordAgainTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			char[] passwords = pwdAgainText.getPassword();
			String password = turnCharsToString(passwords);
			int length = password.length();
			pwdAgainText.setSelectionStart(0);
			pwdAgainText.setSelectionEnd(length);
		}
		public void focusLost(FocusEvent e){
			char[] passwords1 = pwdText.getPassword();
			String password1 = turnCharsToString(passwords1).trim();
			char[] passwords2 = pwdAgainText.getPassword();
			String password2 = turnCharsToString(passwords2).trim();
			if(!password1.equals(password2)){
				pwdAgainText.setBorder(new LineBorder(Color.red,1));
				pwdAgainFault.setText("两次密码不一致");
				isPwdRight = false;
			}else{
				pwdAgainText.setBorder(new LineBorder(Color.black,0));
				pwdAgainFault.setText("");
				isPwdRight = true;
			}
		}
		private String turnCharsToString(char[] chars){
			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
			}
			return strBuf.toString().trim();
		}
		
	}
	private class MailTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			String mailAddress = mailText.getText();
			int length = mailAddress.length();
			mailText.setSelectionStart(0);
			mailText.setSelectionEnd(length);
		}
		public void focusLost(FocusEvent e){
			String mailAddress = mailText.getText();
			if(!isMailAddress(mailAddress)){
				mailText.setBorder(new LineBorder(Color.red,1));
				mailFault.setText("邮箱错误");
				isMailRight = false;
			}else{
				mailText.setBorder(new LineBorder(Color.black,0));
				mailFault.setText("");
				isMailRight = true;
			}
		}
		private boolean isMailAddress(String s){
			
			String format = "^[a-z A-Z 0-9]{1,}@(139|yahoo|gmail|sina|163|126).(com|com.cn)$";
			return s.matches(format);
		}
	}
	class Listener extends Thread{
		RegisterGUI rg;
		public Listener(RegisterGUI s){
			rg=s;
		}
		public void run(){
		while(true){
			Register x=recontrol.reg;
			switch(x){
			case SUCCESS:
				System.out.println("注册成功");
				lg.getFaultLabel().setText("注册成功，请登陆");
				rg.dispose();
				lg.setVisible(true);
				lo=true;
				break;
			case FAILED:
				System.out.println("注册失败");
				JOptionPane.showMessageDialog(null, "用户名已存在，重新填写", "错误提示", JOptionPane.ERROR_MESSAGE);
				lo=true;
				break;
			case WAITING:
				System.out.println("注册中");
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
	private class RegisterListener implements ActionListener {

		private RegisterGUI rg;
		private LoginGUI lg;
		public RegisterListener(RegisterGUI rg1,LoginGUI lg1){
			rg = rg1;
			lg = lg1;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// 调用RegisterControl,完成用户注册任务
			if(isUserRight&&isPwdRight &&isMailRight){
				String name = userText.getText();
				char[] passwords = pwdText.getPassword();
				String password = turnCharsToString(passwords);
				String mailAddress = mailText.getText();
				boolean b = true;
				//与Control交互
				recontrol.reg=Register.WAITING;
				User us=new User();
				us.setInfo(name, password);
				us.setuemail(mailAddress);
				us.setOpration(Operation.INSERT);
				recontrol.getRegister(us);
				Thread x=new Thread(recontrol);
				x.start();
				Listener p=new Listener(rg);
				p.start();
				/*if(b){
					lg.getFaultLabel().setText("注册成功，请登陆");
					rg.dispose();
					lg.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "用户名已存在，重新填写", "错误提示", JOptionPane.ERROR_MESSAGE);
				}*/
			}
			else{
				JOptionPane.showMessageDialog(null, "内容存在错误，重新填写", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private String turnCharsToString(char[] chars){
			StringBuffer strBuf = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
			}
			return strBuf.toString().trim();
		}
		
	}
	//重置按钮监听，将所有信息重置从新填写
	private class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			userText.setText("");
			pwdText.setText("");
			pwdAgainText.setText("");
			mailText.setText("");
			isUserRight = false;
			isPwdRight = false;
			isMailRight = false;
			userText.setBorder(new LineBorder(Color.black,1));
			pwdText.setBorder(new LineBorder(Color.black,1));
			pwdAgainText.setBorder(new LineBorder(Color.black,1));
			mailText.setBorder(new LineBorder(Color.black,1));
			userFault.setText("");
			pwdFault.setText("");
			pwdAgainFault.setText("");
			mailFault.setText("");
		}
	}
	private static class CloseHandler extends WindowAdapter{
		private RegisterGUI rg;
		private LoginGUI lg;
		public CloseHandler(RegisterGUI rg1,LoginGUI lg1){
			rg = rg1;
			lg = lg1;
		}
		public void windowClosing(final WindowEvent event){
			//要做的動作    
			rg.dispose();
			lg.setVisible(true);
		}
	}	
}

