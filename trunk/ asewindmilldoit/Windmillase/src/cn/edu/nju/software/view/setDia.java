package cn.edu.nju.software.view;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class setDia extends JDialog implements ActionListener{
	private JLabel title;
	private JLabel nameL;
	private JTextField name;
	private JLabel key1L;
	private JTextField key1;
	private JLabel key2L;
	private JTextField key2;
	private JLabel key3L;
	private JTextField key3;
	private JLabel sexL;
	private JRadioButton boy;
	private JRadioButton girl;
	private ButtonGroup bg;
	private JButton save;
	private JLabel birth;
	private Choice cho1;
	private Choice cho2;
	private Choice cho3;
	String taskName;
	public setDia(){
		this.setLayout(null);
		setSize(409,461);
		setTitle("个人信息设置");
		setResizable(false);
		setVisible(true);
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width-409)/2,(screen.height-461)/2);
		//this.setBounds(20, 20, 409, 461);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		title=new JLabel("个人信息设置");
		this.add(title);
		title.setBounds(133, 23, 88, 15);
		nameL=new JLabel("昵称：");
		this.add(nameL);
		nameL.setBounds(40,53, 50, 15);
		name=new JTextField(10);
		this.add(name);
		name.setBounds(40, 68, 166, 20);
		sexL=new JLabel("性别：");
		this.add(sexL);
		sexL.setBounds(247, 53, 88, 15);
		boy=new JRadioButton("男");
		girl=new JRadioButton("女");
		bg=new ButtonGroup();
		bg.add(boy);
		bg.add(girl);
		this.add(boy);
		this.add(girl);
		boy.setBounds(247, 69, 50, 20);
		girl.setBounds(300, 69, 50, 20);
		birth=new JLabel("生日：");
		this.add(birth);
		birth.setBounds(41, 110, 50, 15);
		cho1=new Choice();
		cho1.add("一月");
		cho1.add("二月");
		cho1.add("三月");
		cho1.add("四月");
		cho1.add("五月");
		cho1.add("六月");
		cho1.add("七月");
		cho1.add("八月");
		cho1.add("九月");
		cho1.add("十月");
		cho1.add("十一月");
		cho1.add("十二月");
		this.add(cho1);
		cho1.setBounds(40, 124, 50, 20);
		cho2=new Choice();
		cho2.add("01");
		cho2.add("02");
		cho2.add("03");
		cho2.add("04");
		cho2.add("05");
		cho2.add("06");
		cho2.add("07");
		cho2.add("08");
		cho2.add("09");
		cho2.add("10");
		cho2.add("11");
		cho2.add("12");
		cho2.add("13");
		cho2.add("14");
		cho2.add("15");
		cho2.add("16");
		cho2.add("17");
		cho2.add("18");
		cho2.add("19");
		cho2.add("20");
		cho2.add("21");
		cho2.add("22");
		cho2.add("23");
		cho2.add("24");
		cho2.add("25");
		cho2.add("26");
		cho2.add("27");
		cho2.add("28");
		cho2.add("29");
		cho2.add("30");
		cho2.add("31");
		this.add(cho2);
		
		cho2.setBounds(90, 124, 50, 20);
		cho3=new Choice();
		for(int i=1900;i<=2009;i++){
			cho3.add(""+i);
		}
		this.add(cho3);
		cho3.setBounds(140, 124, 60, 20);
		key1L=new JLabel("当前密码：");
		this.add(key1L);
		key1L.setBounds(40, 182, 100, 15);
		key1=new JTextField();
		this.add(key1);
		key1.setBounds(40, 200, 135, 20);
		key2L=new JLabel("新密码：");
		this.add(key2L);
		key2L.setBounds(40, 240, 100, 15);
		key2=new JTextField();
		this.add(key2);
		key2.setBounds(40, 258, 135, 20);
		key3L=new JLabel("确认密码：");
		this.add(key3L);
		key3L.setBounds(40, 296, 100, 15);
		key3=new JTextField();
		this.add(key3);
		key3.setBounds(40, 312, 135, 20);
		save=new JButton("保存");
		this.add(save);
		save.setBounds(284, 360, 70, 20);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

}
