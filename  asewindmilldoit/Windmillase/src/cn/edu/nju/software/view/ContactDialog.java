package cn.edu.nju.software.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ContactDialog extends JDialog{
	
	private int width = 240;
	private int height = 330;

	private ContactMode root;
	
	ImageIcon back = new ImageIcon("src/res/contact.png");
	private MyJPanel panel;
	private JLabel nameLabel;
	private JLabel mailLabel;
	private JLabel telephoneLabel;
	private JLabel commentLabel;
	private JTextField nameText;
	private JTextField mailText;
	private JTextField telephoneText;
	private JTextArea commentText;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public ContactDialog(ContactMode root1){
		super();
		root = root1;
		this.addWindowListener(new CloseHandler(this));
		JDialog.setDefaultLookAndFeelDecorated(true);
		this.setLayout(null);
		this.setSize(width,height);
		this.setTitle("编辑联系人");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width-width)/2, (screen.height-height)/2);
		this.setVisible(false);
		this.setResizable(false);
		this.setModal(true);
		
		
		panel = new MyJPanel(back.getImage());
		panel.setPreferredSize(new Dimension(width,height));
		panel.setSize(new Dimension(width,height));
		panel.setLayout(null);
		this.add(panel,BorderLayout.CENTER);
		
		
		nameLabel = new JLabel("姓名:",JLabel.RIGHT);
		mailLabel = new JLabel("邮箱:",JLabel.RIGHT);
		telephoneLabel = new JLabel("电话:",JLabel.RIGHT);
		commentLabel = new JLabel("备注:",JLabel.RIGHT);
		nameText = new JTextField(20);
		mailText = new JTextField(40);
		telephoneText = new JTextField(20);
		commentText = new JTextArea(20,14);
		commentText.setWrapStyleWord(true);
		confirmButton = new JButton("保存");
		confirmButton.setOpaque(false);
		cancelButton = new JButton("取消");
		cancelButton.setOpaque(false);
	   
		panel.add(nameLabel);
		panel.add(mailLabel);
		panel.add(telephoneLabel);
		panel.add(commentLabel);
		panel.add(nameText);
		panel.add(mailText);
		panel.add(telephoneText);
		panel.add(commentText);
		panel.add(confirmButton);
		panel.add(cancelButton);
	    
	    nameLabel.setBounds(0, 20, 50, 20);
	    mailLabel.setBounds(0, 55, 50, 20);
	    telephoneLabel.setBounds(0,90,50,20);
	    commentLabel.setBounds(0, 125, 50, 20);
	    nameText.setBounds(50, 20, 150, 20);
	    nameText.addFocusListener(new NameTextListener());
	    mailText.setBounds(50, 55, 150, 20);
	    mailText.addFocusListener(new MailTextListener());
	    telephoneText.setBounds(50, 90, 150, 20);
	    telephoneText.addFocusListener(new TelephoneTextListener());
	    commentText.setBounds(50, 125, 150, 130);
	    confirmButton.setBounds(50, 260, 70, 30);
	    confirmButton.addActionListener(new ConfirmButtonListener());
	    cancelButton.setBounds(125,260,70,30);
	    cancelButton.addActionListener(new CancelButtonListener());
	}
	
	public void setMessage(MyContact mc){
		nameText.setText(mc.getName());
		mailText.setText(mc.getMail());
		telephoneText.setText(mc.getTelephone());
		commentText.setText(mc.getComment());
	}
	
	public MyContact getMessage(){
		MyContact mc = new MyContact();
		mc.setName(nameText.getText());
		mc.setMail(mailText.getText());
		mc.setTelephone(telephoneText.getText());
		mc.setComment(commentText.getText());
		return mc;
	}
	
	private class NameTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			String s  = nameText.getText();
			int length = s.length();
			nameText.setSelectionStart(0);
			nameText.setSelectionEnd(length);
			nameText.setBorder(new LineBorder(Color.black,0));
		}
	}
	private class MailTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			String s  = mailText.getText();
			int length = s.length();
			mailText.setSelectionStart(0);
			mailText.setSelectionEnd(length);
			mailText.setBorder(new LineBorder(Color.black,0));
		}
	}
	
	private class TelephoneTextListener extends FocusAdapter{
		public void focusGained(FocusEvent e){
			String s  = telephoneText.getText();
			int length = s.length();
			telephoneText.setSelectionStart(0);
			telephoneText.setSelectionEnd(length);
			telephoneText.setBorder(new LineBorder(Color.black,0));
		}
	}
	
	private static class CloseHandler extends WindowAdapter{
		ContactDialog cd;
		public CloseHandler(ContactDialog cd1){
			cd = cd1;
		}
		public void windowClosing(final WindowEvent event){
			//要做的動作    
			cd.setVisible(false);
		}
	}
	
	private class ConfirmButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//保存监听
			setVisible(false);
			MyContact mc = new MyContact();
			mc.setName(nameText.getText());
			mc.setMail(mailText.getText());
			mc.setTelephone(telephoneText.getText());
			mc.setComment(commentText.getText());
			root.editContact(mc);
		}
	}
	
	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
		}
	}
}
