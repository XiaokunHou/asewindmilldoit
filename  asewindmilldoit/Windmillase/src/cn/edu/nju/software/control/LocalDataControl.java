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
	boolean datacomplete=false;
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
public boolean getDataComplete(){
	return datacomplete;
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
	   try {
		output.writeObject(x);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//给服务器的数据
	   SqlData sqldata=(SqlData) x;
	   Operation operation=sqldata.getOperation();
	   switch(operation){
	   case DELETE:
		   box.delete(sqldata);
		   break;
	   case UPDATE:
		   box.edit(sqldata);//编辑和完成任务，本质都是修改相关属性然后UPDATE
		   break;
	   case INSERT:
		   box.add(sqldata);
	   }  
	   //2.以上是给BOX的动态数据，GUI点击一个模式下分类，会调用BOX的setcurrent***()方法，然后有操作完成后调用uploadData(**);
	   //1.每次模式切换时候，GUI会调用 BOX x=new Box(); x.setLocalDataControl(*);然后调用x.splitinto()，完成Box里面链表初始化
	   //本地用户操作的同时uploadData
	   //传给服务器Object
	   //调用Switch(Operation);调用CollectBox的相关操作
	   updateFiles(x);
   }
   public void updateFiles(Object x){
	   //当一个操作完成后，更新本地文件和自己的链表
	   if(x.getClass().getName().equals("cn.edu.nju.software.database.User")){
		   try {
			User newuser=(User)x;
			users.set(0, newuser);
			FileOutputStream outuser=new FileOutputStream(fileu);
			ObjectOutputStream o=new ObjectOutputStream(outuser);
			o.writeObject(x);
			o.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	   }
	   if(x.getClass().getName().equals("cn.edu.nju.software.database.Task")){
		   Task task=(Task) x;
		   switch(task.getOperation()){
		   case DELETE:
			   for(int i=0;i<tasks.size();i++){
				   if(tasks.get(i).gettaskname().equals(task.gettaskname())){
					   tasks.get(i).setisdelete(true);
				   }
			   }
			   break;
		   case UPDATE:
			   for(int i=0;i<tasks.size();i++){
				   if(tasks.get(i).gettaskname().equals(task.gettaskname())){
					  tasks.set(i,task);
				   }
			   }
			   break;
		   case INSERT:
			  tasks.add(task);
			  break;
		   }
	   }
	   if(x.getClass().getName().equals("cn.edu.nju.software.database.Contact")){
		   Contact contact=(Contact) x;
		   switch(contact.getOperation()){
		   case DELETE:
			   for(int i=0;i<con.size();i++){
				   if(con.get(i).getcontactname().equals(contact.getcontactname())){
					  con.remove(i);
				   }
			   }
			   break;
		   case UPDATE:
			   for(int i=0;i<con.size();i++){
				   if(con.get(i).getcontactname().equals(contact.getcontactname())){
					  con.set(i,contact);
				   }
			   }
			   break;
		   case INSERT:
			  con.add(contact);
			  break;
		   }
	   }
	   writeInFile();//写入文件中
   }
   public void emptyRubbish(){
	   for(int x=0;x<tasks.size();x++){
		   if(tasks.get(x).getisdelete()){
			   tasks.remove(x);
		   }
	   }
	   writeInFile();//更新文件
	   //GUI清空垃圾箱的时候直接调用
	   //当点击垃圾箱时，遍历Task，找isdelete为true的显示
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
				   if(s.equals("cn.edu.nju.software.database.User")){
					   User o=(User) ko;
					   users.add(o);
				   }
				   if(s.equals("cn.edu.nju.software.database.Contact")){
					   Contact kl=(Contact) ko;
					   con.add(kl);
				   }
                   if(s.equals("cn.edu.nju.software.database.Task")){
					   Task pp=(Task) ko;
					   //System.out.println(pp.gettaskname()+"localdatacontrol");
					   tasks.add(pp);
				   }
                   if(s.equals("java.lang.String")){
                	   String respond=(String) ko;
                	   if(respond.equals("datacomplete")){
                	      //System.out.println("接受数据完毕");
                	      datacomplete=true;
                	   }
                   }
                   if(datacomplete){
                	   break;
                	   }
                   else{
                   writeInFile();
                   }
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
