package cn.edu.nju.software.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddContactDialog extends JDialog{
	
	private int width = 300;
	private int height = 160;
	
	private ContactMode root;
	
	private MyJPanel backPanel;
	ImageIcon back = new ImageIcon("src/res/contact.png");
	
	private JLabel label;
	private JTextField nameText;
	
	private JButton invite;
	private JButton cancel;
	
	public AddContactDialog(ContactMode root1){
		super();
		root = root1;
		this.addWindowListener(new CloseHandler(this));
		JDialog.setDefaultLookAndFeelDecorated(true);
		this.setLayout(null);
		this.setSize(width,height);
		this.setTitle("�����ϵ��");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Dimension screen= Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width-width)/2, (screen.height-height)/2);
		this.setVisible(false);
		this.setResizable(false);
		this.setModal(true);
		
		backPanel = new MyJPanel(back.getImage());
		backPanel.setPreferredSize(new Dimension(width,height));
		backPanel.setSize(new Dimension(width,height));
		backPanel.setLayout(null);
		this.add(backPanel,BorderLayout.CENTER);
		
		label = new JLabel("����Ҫ������˵��˺�",JLabel.CENTER);
		nameText = new JTextField(20);
		invite = new JButton("����");
		invite.setOpaque(false);
		invite.addActionListener(new InviteButtonListener());
		cancel = new JButton("ȡ��");
		cancel.setOpaque(false);
		cancel.addActionListener(new CancelButtonListener());
		
		backPanel.add(label);
		backPanel.add(nameText);
		backPanel.add(invite);
		backPanel.add(cancel);
		
		label.setBounds(0, 0, 300, 25);
		nameText.setBounds(30, 30, 250, 25);
		
		invite.setBounds(50, 60, 70, 25);
		cancel.setBounds(170, 60, 70, 25);
		
	}
	private static class CloseHandler extends WindowAdapter{
		AddContactDialog ad;
		public CloseHandler(AddContactDialog ad1){
			ad = ad1;
		}
		public void windowClosing(final WindowEvent event){
			//Ҫ���Ą���    
			ad.setVisible(false);
		}
	}
	
	private class InviteButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//������ϵ��,����Control����ȡ����ϵ�˵���Ϣ
			setVisible(false);
			MyContact mc = new MyContact();
			mc.setName(nameText.getText());
			root.addContact(mc);
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
