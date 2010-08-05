package cn.edu.nju.software.control;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import cn.edu.nju.software.database.User;
import cn.edu.nju.software.view.LoginGUI;

public class LoginControl extends Control implements Runnable{
	User us;
	public enum Login{
		SUCCESS,FAILED,WAITING
	}
	public Login logi=Login.WAITING;
	Object k;
	Socket soc;
	boolean online=false;//�ж��Ƿ�����
	public boolean getLogin(User x){
		us=x;
		getConnection();
		try {
			output.writeObject(us);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return online;
	}
	public boolean getLocalLogin(LocalDataControl x,User u){
		User user=x.getUser().get(0);
		if(u.getusername().equals(user.getusername())&&u.getpassword().equals(user.getpassword())){
			return true;
		}
		else{
			return false;
		}
	}
	//�ⲿGUI�ӿڣ������½�������һ����
	//loginΪtrue�Ļ���GUIҪ��ʾ��½�ɹ������뻺��������������LocalDataControl��updateLocalData()����
	/*public void  threadStart(){
		Incoming k=new Incoming();
		k.start();

	}*/
	 
	private void getConnection(){
		try{
			soc=new Socket("127.0.0.1",8888);//��������
			output=new ObjectOutputStream(soc.getOutputStream());
			input=new ObjectInputStream(soc.getInputStream());
			reader=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			writer=new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));
			online=true;
		}catch(Exception x){
			System.out.println("��������ʧ��");
			online=false;
		}
		
	}
	public boolean isOnline(){
		   return online;
		   //�ж��Ƿ�Ϊ����״̬
	   }
	/*public static ObjectOutputStream getOutput(){
		return output;
	}
	public static ObjectInputStream getInput(){
		return input;
	}*/
    
    	public void run(){
    		
    		try {
				while((k=input.readObject())!=null){
					String answer=(String) k;
					System.out.println(answer);
					if(answer.equals("loginfailed")){
						logi=Login.FAILED;
						//gui.loginFailed();
						soc.close();
						input.close();
						output.close();
						
						break;
					}
					if(answer.equals("loginsucceed")){
						logi=Login.SUCCESS;
						//gui.loginSuccess();
						//System.out.println("woca");
				      /*    soc.close();
						    input.close();
						    output.close();*/
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	}
    
	
	
}
