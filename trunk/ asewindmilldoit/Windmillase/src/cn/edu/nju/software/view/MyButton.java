package cn.edu.nju.software.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;

public class MyButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pos = 0;
	
	private MouseAdapter myMouseAdapter;
	
	public MyButton(int i){
		super();
		setPos(i);
		Dimension  dn = new Dimension(135,30);
		this.setPreferredSize(dn);
		this.setSize(dn);
		this.setMaximumSize(dn);
		this.setMinimumSize(dn);
		this.setOpaque(false);
	}
	public MyButton(String s,int i){
		super(s);
		setPos(i);
		Dimension  dn = new Dimension(135,30);
		this.setPreferredSize(dn);
		this.setSize(dn);
		this.setMaximumSize(dn);
		this.setMinimumSize(dn);
		this.setOpaque(false);
	}
	public MyButton(String s){
		super(s);
		setPos(-1);
		Dimension  dn = new Dimension(135,30);
		this.setPreferredSize(dn);
		this.setSize(dn);
		this.setMaximumSize(dn);
		this.setMinimumSize(dn);
		this.setOpaque(false);
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getPos() {
		return pos;
	}
	public void setMyMouseAdapter(MouseAdapter myMouseAdapter) {
		this.myMouseAdapter = myMouseAdapter;
	}
	public MouseAdapter getMyMouseAdapter() {
		return myMouseAdapter;
	}
}
