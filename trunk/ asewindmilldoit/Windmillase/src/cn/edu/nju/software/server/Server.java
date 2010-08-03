package cn.edu.nju.software.server;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.database.*;

public class Server {
	private ServerSocket server;
	private Socket client;
	ArrayList<DataOperation> request;
    public static void main(String args[]){
	   Server la=new Server();
	   la.initserver();
    }
    public void initserver(){
    	int i;
		int count=0;
    	try {
    		request=new ArrayList<DataOperation>();
			server=new ServerSocket(8888);//8888Ϊ��������������˿�
			while(true){
				client=server.accept();
				DataOperation k=new DataOperation(client);
				 k.start();
				request.add(k);
				count=request.size();
				/*for( i=0;i<count;i++){
					request.get(i).start();//�����û�����ת��Ϊ�߳����������д������ݿ�
				}*/
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
    }
    class DataOperation extends Thread{
    	Socket soc=null;
    	ObjectInputStream input;
    	ObjectOutputStream output;
    	Object ob=null;
    	public DataOperation(Socket j){
    		soc=j;
    		init();
    	}
    	public void init(){
    		try{
    		  input=new ObjectInputStream(soc.getInputStream());
    		  output=new ObjectOutputStream(soc.getOutputStream());
    		}catch(Exception x){
    			System.out.println("�����������������");
    		}
    	}
    	public void clientDeal(String username){
    		//int x=Integer.parseInt(uid);
    		User user=new User();
    		user.setusername(username);
    		DataBaseContact con=new DataBaseContact();
    		DataBaseTask task=new DataBaseTask();
    		DataBaseUser ufac=new DataBaseUser();
    		ResultSet us=ufac.query(user);
    	    ResultSet conta=con.query(user);
    	    ResultSet ta=task.query(user);  
    	  try{  
    		  ArrayList<Contact> listcon=new ArrayList<Contact>();
    		  ArrayList<Task> listtask=new ArrayList<Task>();
    		  ArrayList<User> listuser=new ArrayList<User>();
    		  while(conta.next()){
    	    	 Contact m=new Contact();//�����ݿ������������ϵ�˳�ʼ�������������ResultSet�е���ֵ
    	    	 listcon.add(m);
    	       }
    		  while(ta.next()){
     	    	 Task kp=new Task();//�����ݿ���������������ʼ�������������ResultSet�е���ֵ
     	    	 listtask.add(kp);
     	       }
    		  while(us.next()){
    			  User ll=new User();//�����ݿ���������������ʼ�������������ResultSet�е���ֵ
    			  listuser.add(ll);
    		  }
    		  int consize=listcon.size();
    		  for(int i=0;i<consize;i++){
    			  output.writeObject(listcon.get(i));
    		  }
    		  int tasksize=listtask.size();
    		  for(int i=0;i<tasksize;i++){
    			  output.writeObject(listtask.get(i));
    		  }
    		  int usersize=listuser.size();
    		  for(int i=0;i<usersize;i++){
    			  output.writeObject(listuser.get(i));
    		  }
    		  //����Ϊд��ͻ��˻���
    		  /*
    		   * 
    		   */
    	  }catch(Exception s){
    		  System.out.println("���ݿ����");
    	  }
    	}
    	public void doTheServe(Object k){
    		DataBaseFactory fac=new DataBaseFactory(k);
    	    DataBase x1=fac.creatDataBaseObject();
    	    User pp=(User) k;
    	   System.out.println(pp.getpassword());
    		fac.strategy();//����DataBase�������Լ�������
    		String mess=null;
    		boolean insert=fac.inser();
    		ResultSet set=fac.getSet();
    		if(k.getClass().getName().equals("cn.edu.nju.software.database.User")){
    			SqlData ji=(SqlData) k;
    			if(ji.getOperation()==Operation.QUERY){
    				
    				try{
    					if(!set.next()){
    					mess="loginfailed";
    				   }
    				else{
    					mess="loginsucceed";
    				  }
    				}catch(Exception x){
    					System.out.println("���ݿ����2");
    				}
    			}
    			else if(ji.getOperation()==Operation.INSERT){
    				if(insert){
    					mess="registersucceed";
    				}
    				else{
    					mess="registerfailed";
    				}
    			}
    		}
    		try {
    			if(mess!=null){
				output.writeObject(mess);
    			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("ע�᷵�ش���");
			}
    	}
    	//doTheServe�������ڷ���ע���½�ɹ������Ϣ����Ϊ�û��޸��˶������������޸������Գ��˳�ʼ������Object������ʱ���� 
    	public void run(){
    		
    		try {
				while((ob=input.readObject())!=null){
					//System.out.println(ob.getClass().getName());
					if(ob.getClass().getName().equals("java.lang.String")){
						String s=(String) ob;
					    String [] x=s.split("/");
						if(x[0].equals("init")){
							clientDeal(x[1]);//��Client�ı��ػ���
						}
					}
					else{
						
						doTheServe(ob);
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
}
