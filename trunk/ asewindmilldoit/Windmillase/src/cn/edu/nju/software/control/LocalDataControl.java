package cn.edu.nju.software.control;

import java.io.*;
import java.util.ArrayList;

import cn.edu.nju.software.box.CollectBox;
import cn.edu.nju.software.database.*;

public class LocalDataControl extends Control{
	User user;
	private int uid;
	private ArrayList<Contact> con;
	private ArrayList<User> users;
	private ArrayList<Task> tasks;
	File fileu;
	File filec;
	File filet;
	ObjectOutputStream ot;
	ObjectInputStream in;//本地文件流
	//三个数组可以给GUI直接调用，注GUI的各个BOX完成分类
   public LocalDataControl(LoginControl x){
	   
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
	 //测试用构造器
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
   //GUI调用
   public void setUser(User x){
	   user=x;
   }
   public void writeInFile(){
	   //将信息写入文件，特指初始化本地数据后，用户操作的东西
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
	/*int count=con.size();
	try{
	for(int i=0;i<count;i++){
			ot.writeObject(con.get(i));
	}
		ot.close();
	}catch(Exception e){
		System.out.println("写入本地文件错误");
	}*/
	   try {
		ot.writeObject(con);
		ot.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("写入本地文件错误");
	}
   }
   private void writeTask() {
	// TODO Auto-generated method stub
	   try {
			ot.writeObject(tasks);
			ot.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("写入本地文件错误");
		}
   }
   private void writeUser() {
	// TODO Auto-generated method stub
	   try {
			ot.writeObject(users);
			ot.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("写入本地文件错误");
		}
   }
public void uploadData(Object x,CollectBox box){
	
	   //本地用户操作的同时uploadData
	   //传给服务器Object
	   //调用Switch(Operation);调用CollectBox的相关操作
   }
   public void setData(ArrayList<User> x){
	   //测试代码
	   users=x;
   }
   public void loadFromLocal(){
	   //GUI通过Login的getOnline方法获取连网状态,false的话就调用本方法，否则调用updateLocalData方法
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
	  /*Object k=null;
	  try{
	while((k=in.readObject())!=null){
		User ll=(User) k;
		users.add(ll);
	  }
	  }catch(Exception x){
		  System.out.println("读入本地文件错误");
	  }*/
	  Object k;
	try {
		k = in.readObject();
	    users=(ArrayList<User>) k;
	    in.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("读入本地文件错误");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
  }

   private void readTask() {
	// TODO Auto-generated method stub
	   Object k=null;
	   try {
			k = in.readObject();
		    tasks=(ArrayList<Task>) k;
		    in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("读入本地文件错误");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

   private void readContact() {
	// TODO Auto-generated method stub
	   Object k=null;
	   try {
			k = in.readObject();
		    con=(ArrayList<Contact>) k;
		    in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("读入本地文件错误");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

public void updateLocalData(){
	   //第一次连网登陆的时候，从服务器读入数据完成本地缓存文件的出入,GUI调用
	   requestSend();
	   Income ji=new Income();
	   ji.start();
   }
   public void requestSend(){
		 //会发送String格式具体请求
		 String request="init/"+user.getusername();
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
					   //System.out.println(pp.gettaskname()+"localdatacontrol");
					   tasks.add(pp);
				   }
                   writeInFile();
			   }
			// writeInFile();
			// input.close();
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
