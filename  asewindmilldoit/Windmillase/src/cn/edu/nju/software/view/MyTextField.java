package cn.edu.nju.software.view;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class MyTextField extends JTextField{
	
	private KeyListener myKeyListener;
	private FocusListener myFocusLister;
	
	private int pos;

	public MyTextField(int i){
		super();
		pos = i;
	}

	public void setMyKeyListener(KeyListener myKeyListener) {
		this.myKeyListener = myKeyListener;
	}

	public KeyListener getMyKeyListener() {
		return myKeyListener;
	}

	public void setMyFocusLister(FocusListener myFocusLister) {
		this.myFocusLister = myFocusLister;
	}

	public FocusListener getMyFocusLister() {
		return myFocusLister;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getPos() {
		return pos;
	}
}
