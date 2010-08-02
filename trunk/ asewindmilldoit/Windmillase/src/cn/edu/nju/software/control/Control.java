package cn.edu.nju.software.control;

import java.net.*;
import java.io.*;
public class Control {
	 public enum Operation{DELETE,QUERY,INSERT,UPDATE};//这里是相应操作，
	 private Socket socket;
	 protected  ObjectOutputStream output;
	 protected  ObjectInputStream input;
	 protected BufferedReader reader;
	 protected PrintWriter writer;
	 private String request;
	 private int passwd;
	 private String name;
	 public void requestSend(){
		 //会发送String格式具体请求
	 }
	 private void getConnection(){
		 
	 }
	 public void answerReceive(){
		 //读入服务器返回信息，如，登陆成功，注册成功等
	 }
	 public void getRequestFromMode(){
		 
	 }//GUI中的操作通过此接口传入Control
}
