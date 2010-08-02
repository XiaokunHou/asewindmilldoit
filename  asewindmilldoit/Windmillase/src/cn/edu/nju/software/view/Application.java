package cn.edu.nju.software.view;

import javax.swing.UIManager;

public class Application {

	private static LoginGUI instance;

	public static void main(String[] args){

		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}

		instance =new LoginGUI();
	}
}
