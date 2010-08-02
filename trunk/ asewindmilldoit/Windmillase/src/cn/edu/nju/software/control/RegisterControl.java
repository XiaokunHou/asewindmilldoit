package cn.edu.nju.software.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import cn.edu.nju.software.control.LoginControl.Login;
import cn.edu.nju.software.database.User;

public class RegisterControl extends Control implements Runnable{
	User us;
	public enum Register{
		SUCCESS,FAILED,WAITING
	}
	public Register reg=Register.WAITING;
	Object k;
	Socket soc;
	boolean online=false;//判断是否连网
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while((k=input.readObject())!=null){
				String answer=(String) k;
				System.out.println(answer);
				if(answer.equals("registerfailed")){
					reg=Register.FAILED;
					//gui.loginFailed();
					soc.close();
					input.close();
					output.close();
					
					break;
				}
				if(answer.equals("registersucceed")){
					reg=Register.SUCCESS;
					//gui.loginSuccess();
					//System.out.println("woca");
					soc.close();
					input.close();
					output.close();
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
	public void getRegister(User x){
		us=x;
		getConnection();
		try {
			output.writeObject(us);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//外部调用,传入User建立连接，然后调start();
		}
		
	}
	public boolean isOnline(){
		   return online;
		   //判断是否为连网状态
	   }
	private void getConnection(){
		try{
			soc=new Socket("127.0.0.1",8888);//本机测试
			output=new ObjectOutputStream(soc.getOutputStream());
			input=new ObjectInputStream(soc.getInputStream());
			reader=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			writer=new PrintWriter(new OutputStreamWriter(soc.getOutputStream()));
			online=true;
		}catch(Exception x){
			System.out.println("连接主机失败");
			online=false;
		}
		
	}
	
}
