package cn.edu.nju.software.control;

import java.net.*;
import java.io.*;
public class Control {
	 public enum Operation{DELETE,QUERY,INSERT,UPDATE};//��������Ӧ������
	 private Socket socket;
	 protected  ObjectOutputStream output;
	 protected  ObjectInputStream input;
	 protected BufferedReader reader;
	 protected PrintWriter writer;
	 private String request;
	 private int passwd;
	 private String name;
	 public void requestSend(){
		 //�ᷢ��String��ʽ��������
	 }
	 private void getConnection(){
		 
	 }
	 public void answerReceive(){
		 //���������������Ϣ���磬��½�ɹ���ע��ɹ���
	 }
	 public void getRequestFromMode(){
		 
	 }//GUI�еĲ���ͨ���˽ӿڴ���Control
}
