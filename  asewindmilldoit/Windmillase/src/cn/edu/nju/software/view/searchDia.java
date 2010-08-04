package cn.edu.nju.software.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class searchDia extends JDialog implements ActionListener{
	private JTextField text;
	private JButton search;
	private JRadioButton r1;
	private JRadioButton r2;
	private ButtonGroup bg;
	String taskName;
	public searchDia(){
		//setModal(true);
		this.setLayout(null);
		text=new JTextField(15);
		search=new JButton("搜索");
		search.addActionListener(this);
		setSize(347,100);
		setTitle("搜索");
		setResizable(false);
		setVisible(true);
		setBounds(721,156,356,138);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	
		this.getContentPane().add(text);
		text.setBounds(15, 20, 250, 25);
		this.getContentPane().add(search);
		search.setBounds(280,20,65,25);
		r1=new JRadioButton("当前箱子");
		r2=new JRadioButton("所有箱子");
		bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		
		this.add(r1);
		this.add(r2);
		r1.setBounds(15, 50, 100, 25);
		r2.setBounds(120, 50, 100, 25);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==search)
			 taskName=text.getText();
		
	}

}
