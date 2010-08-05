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
	LoginControl control=new LoginControl();//��½����
	User us;//һ����½��ɺ�����һ��user����
	LocalDataControl localcontrol;//�������ݻ���
	boolean online=false;
	boolean lo=false;//��½��ʧ�ܶ���Ϊtrue
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
		
		userLabel = new JLabel("�û���:");
		userLabel.setHorizontalAlignment(JTextField.RIGHT);
		textPanel.add(userLabel);
		
		userText = new JTextField(15);
		textPanel.add(userText);
		userText.addFocusListener(new UserTextListener());
		
		passwordLabel = new  JLabel("��  ��:");
		passwordLabel.setHorizontalAlignment(JTextField.RIGHT);
		textPanel.add(passwordLabel);
		
		passwordText = new JPasswordField(15);
		textPanel.add(passwordText);
		passwordText.addFocusListener(new PassWordTextListener());
		
		setFaultLabel(new  JLabel());
		faultPanel.add(getFaultLabel());
		faultLabel.setHorizontalAlignment(JLabel.CENTER);
		Font faultFont=new Font("����",Font.BOLD,11);
		faultLabel.setFont(faultFont);
		
		login = new JButton("��¼");
		buttonPanel.add(login);
		login.addActionListener(new LoginListener());
		
		sign = new JButton("����ע��");
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
				getFaultLabel().setText("�û�������ֻ������ĸ��ͷ,�����������ַ�,������λ");
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
			// ����LoginControl,�ж��û����Ƿ���ȷ
			
			String name = userText.getText();
			char[] passwords = passwordText.getPassword();
			String password = turnCharsToString(passwords);
			//��LoginControl����
			control.logi=Login.WAITING;
			us=new User();
			us.setInfo(name, password);
			us.setOpration(Operation.QUERY);
		    online=	control.getLogin(us);
		    if(online){
		    	System.out.println("�����ɹ�");
		    	Thread x=new Thread(control);
				x.start();
				Listener p=new Listener();
				p.start();
		    }
		    else{
		    	System.out.println("����ʧ�ܣ���ʼ���ص�½");
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
					getFaultLabel().setText("�û������벻��ȷ");
		        }
		    }
			
			/*boolean s = true;
			if(s){
				setVisible(false);
				new MainFrame();
				MainFrame.crd.first(MainFrame.mainPanel);
			}else{
				userText.setBorder(new LineBorder(Color.red,1));
				getFaultLabel().setText("�û������벻��ȷ");
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
			//����ע�����
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
				System.out.println("��½�ɹ�");
				//getFaultLabel().setText("");
				localcontrol=new LocalDataControl(control);
				localcontrol.setUser(us);
				localcontrol.updateLocalData();
				lo=true;
				break;
			case FAILED:
				System.out.println("��½ʧ��");
				userText.setBorder(new LineBorder(Color.red,1));
				getFaultLabel().setText("�û����������");
				lo=true;
				break;
			case WAITING:
				System.out.println("��½��");
				lo=false;
				break;
			 }
			if(lo){
				break;
			  }
			}
		while(!localcontrol.getDataComplete()){
			System.out.println("����������");
		  }
		if(localcontrol.getDataComplete()){
			System.out.println("�������");
			setVisible(false);
			new MainFrame().setLocalDataControl(localcontrol);
			MainFrame.crd.first(MainFrame.mainPanel);
		 }
		}
	}
	//Listener��Ϊִ�е�½�󷽷�
}
