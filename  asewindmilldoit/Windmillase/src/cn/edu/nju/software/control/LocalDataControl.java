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
	ObjectInputStream in;//�����ļ���
	//����������Ը�GUIֱ�ӵ��ã�עGUI�ĸ���BOX��ɷ���
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
	 //�����ù�����
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
   //GUI����
   public void setUser(User x){
	   user=x;
   }
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
	   try {
		ot.writeObject(con);
		ot.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("д�뱾���ļ�����");
	}
   }
   private void writeTask() {
	// TODO Auto-generated method stub
	   try {
			ot.writeObject(tasks);
			ot.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("д�뱾���ļ�����");
		}
   }
   private void writeUser() {
	// TODO Auto-generated method stub
	   try {
			ot.writeObject(users);
			ot.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("д�뱾���ļ�����");
		}
   }
public void uploadData(Object x,CollectBox box){
	   try {
		output.writeObject(x);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//��������������
	   SqlData sqldata=(SqlData) x;
	   Operation operation=sqldata.getOperation();
	   switch(operation){
	   case DELETE:
		   box.delete(sqldata);
		   break;
	   case UPDATE:
		   box.edit(sqldata);//�༭��������񣬱��ʶ����޸��������Ȼ��UPDATE
		   break;
	   case INSERT:
		   box.add(sqldata);
	   }  
	   //2.�����Ǹ�BOX�Ķ�̬���ݣ�GUI���һ��ģʽ�·��࣬�����BOX��setcurrent***()������Ȼ���в�����ɺ����uploadData(**);
	   //1.ÿ��ģʽ�л�ʱ��GUI����� BOX x=new Box(); x.setLocalDataControl(*);Ȼ�����x.splitinto()�����Box���������ʼ��
	   //�����û�������ͬʱuploadData
	   //����������Object
	   //����Switch(Operation);����CollectBox����ز���
	   updateFiles(x);
   }
   public void updateFiles(Object x){
	   //��һ��������ɺ󣬸��±����ļ����Լ�������
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
	   writeInFile();//д���ļ���
   }
   public void emptyRubbish(){
	   for(int x=0;x<tasks.size();x++){
		   if(tasks.get(x).getisdelete()){
			   tasks.remove(x);
		   }
	   }
	   writeInFile();//�����ļ�
	   //GUI����������ʱ��ֱ�ӵ���
	   //�����������ʱ������Task����isdeleteΪtrue����ʾ
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
	  Object k;
	try {
		k = in.readObject();
	    users=(ArrayList<User>) k;
	    in.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("���뱾���ļ�����");
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
			System.out.println("���뱾���ļ�����");
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
			System.out.println("���뱾���ļ�����");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
                	      //System.out.println("�����������");
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
