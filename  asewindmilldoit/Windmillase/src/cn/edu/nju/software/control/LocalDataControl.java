package cn.edu.nju.software.control;

import java.io.*;
import java.util.ArrayList;

import cn.edu.nju.software.database.*;

public class LocalDataControl extends Control{
	private int uid;
	private ArrayList<Contact> con;
	private ArrayList<User> users;
	private ArrayList<Task> tasks;
	File fileu;
	File filec;
	File filet;
	ObjectOutputStream ot;
	ObjectInputStream in;//�����ļ���
	//����������Ը�GUIֱ�ӵ��ã�עGUI�ĸ���BOX��ɷ���
   public LocalDataControl(LoginControl x,int ud){
	   uid=ud;
	   output=x.output;
	   input=x.input;
	   con=new ArrayList<Contact>();
	   users=new ArrayList<User>();
	   tasks=new ArrayList<Task>();
	   fileu=new File("LocalData/user.txt");
	   filec=new File("LocalData/contact.txt");
	   filet=new File("LocalData/task.txt");
   }
   
   public LocalDataControl() {
	// TODO Auto-generated constructor stub
	 //�����ù�����
	   con=new ArrayList<Contact>();
	   users=new ArrayList<User>();
	   tasks=new ArrayList<Task>();
	   fileu=new File("LocalData/user.txt");
	   filec=new File("LocalData/contact.txt");
	   filet=new File("LocalData/task.txt");
 }

public ArrayList<Contact> getContact(){
	   return con;
   }
   public ArrayList<User> getUser(){
	   return users;
   }
   public ArrayList<Task> getTask(){
	   return tasks;
   }
   //GUI����
   
   public void writeInFile(){
	   //����Ϣд���ļ�����ָ��ʼ���������ݺ��û������Ķ���
	   try {
		FileOutputStream ofu=new FileOutputStream(fileu);
		FileOutputStream oft=new FileOutputStream(filet);
		FileOutputStream ofc=new FileOutputStream(filec);
		try {
			ot=new ObjectOutputStream(ofu);
			writeUser();
            ot=new ObjectOutputStream(oft);
			writeTask();
			ot=new ObjectOutputStream(ofc);
			writeContact();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   private void writeContact() {
	// TODO Auto-generated method stub
	int count=con.size();
	try{
	for(int i=0;i<count;i++){
			ot.writeObject(con.get(i));
	}
		ot.close();
	}catch(Exception e){
		System.out.println("д�뱾���ļ�����");
	}
   }
   private void writeTask() {
	// TODO Auto-generated method stub
	   int count=tasks.size();
		try{
		for(int i=0;i<count;i++){
				ot.writeObject(tasks.get(i));
		}
			ot.close();
		}catch(Exception e){
			System.out.println("д�뱾���ļ�����");
		}
   }
   private void writeUser() {
	// TODO Auto-generated method stub
	   int count=users.size();
		try{
		for(int i=0;i<count;i++){
				ot.writeObject(users.get(i));
		}
			ot.close();
		}catch(Exception e){
			System.out.println("д�뱾���ļ�����");
		}
   }
public void uploadData(Object x){
	
	   //�����û�������ͬʱuploadData
   }
   public void setData(ArrayList<User> x){
	   //���Դ���
	   users=x;
   }
   public void loadFromLocal(){
	   //GUIͨ��Login��getOnline������ȡ����״̬,false�Ļ��͵��ñ��������������updateLocalData����
	   try {
		FileInputStream ifu=new FileInputStream(fileu);
		FileInputStream ift=new FileInputStream(filet);
		FileInputStream ifc=new FileInputStream(filec);
		in=new ObjectInputStream(ifu);
		readUser();
		in=new ObjectInputStream(ift);
		readTask();
		in=new ObjectInputStream(ifc);
		readContact();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   private void readUser() {
	// TODO Auto-generated method stub
	  Object k=null;
	  try{
	while((k=in.readObject())!=null){
		User ll=(User) k;
		users.add(ll);
	  }
	  }catch(Exception x){
		  System.out.println("���뱾���ļ�����");
	  }
  }

   private void readTask() {
	// TODO Auto-generated method stub
	   Object k=null;
		  try{
		while((k=in.readObject())!=null){
			Task ll=(Task) k;
			tasks.add(ll);
		  }
		  }catch(Exception x){
			  System.out.println("���뱾���ļ�����");
		  }
  }

   private void readContact() {
	// TODO Auto-generated method stub
	   Object k=null;
		  try{
		while((k=in.readObject())!=null){
			Contact ll=(Contact) k;
			con.add(ll);
		  }
		  }catch(Exception x){
			  System.out.println("���뱾���ļ�����");
		  }
  }

public void updateLocalData(){
	   //��һ��������½��ʱ�򣬴ӷ���������������ɱ��ػ����ļ��ĳ���,GUI����
	   requestSend();
	   Income ji=new Income();
	   ji.start();
   }
   public void requestSend(){
		 //�ᷢ��String��ʽ��������
		 String request="init/"+uid;
		 try {
			output.writeObject(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
   class Income extends Thread{
	   public void run(){
		   Object ko=null;
		   try {
			while((ko=input.readObject())!=null){
				   String s=ko.getClass().getName();
				   if(s=="cn.edu.nju.software.database.User"){
					   User o=(User) ko;
					   users.add(o);
				   }
				   if(s=="cn.edu.nju.software.database.Contact"){
					   Contact kl=(Contact) ko;
					   con.add(kl);
				   }
                   if(s=="cn.edu.nju.software.database.Task"){
					   Task pp=(Task) ko;
					   tasks.add(pp);
				   }
                 writeInFile();
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
}
