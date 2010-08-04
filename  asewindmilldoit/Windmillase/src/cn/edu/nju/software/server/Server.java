package cn.edu.nju.software.server;

import java.io.*;
import java.net.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    	    	 //�����ݿ������������ϵ�˳�ʼ�������������ResultSet�е���ֵ
    			 Contact m=creatContact(conta);
    			 //System.out.println(m.getgroupname()+"contact");
    	    	 listcon.add(m);
    	       }
    		  while(ta.next()){
     	    	 //�����ݿ���������������ʼ�������������ResultSet�е���ֵ
     	    	 Task kp=creatTask(ta);
     	    	//System.out.println(kp.gettaskname()+"task111");
     	    	 listtask.add(kp);
     	       }
    		  while(us.next()){
    			  //�����ݿ���������������ʼ�������������ResultSet�е���ֵ
    			  User ll=creatUser(us);
    			  //System.out.println(m.getgroupname()+"contact");
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
    		  String respond="datacomplete";
    		  output.writeObject(respond);
    		  //����Ϊд��ͻ��˻���
    		  /*
    		   * 
    		   */
    	  }catch(Exception e){
    		  e.printStackTrace();
    	  }
    	}
    	private User creatUser(ResultSet us) {
			// TODO Auto-generated method stub
			User u=new User();
			try{
			String name=us.getString(2);
			String passd=us.getString(3);
			String nickname=us.getString(4);
			String email=us.getString(5);
			String sex=us.getString(6);
			String birthday=us.getString(7);
			String remark=us.getString(8);
			u.setusername(name);
			u.setpassword(passd);
			u.setunickname(nickname);
			u.setuemail(email);
			u.setubirthday(birthday);
			u.setusex(sex);
			u.seturemark(remark);
			}catch(Exception x){
				System.out.println("Server��ʼUserʧ��");
			}
			
			return u;
		}
		private Task creatTask(ResultSet ta) {
			// TODO Auto-generated method stub
			Task t=new Task();
		
			
			try {
				int id=ta.getInt(1);
				String  taskname = ta.getString(2);
				String info=ta.getString(3);
				String lable=ta.getString(4);
				Date starttime_temp=ta.getDate(5);
				Date endtime_temp=ta.getDate(6);
				String isdoing = ta.getString(7);
				boolean isdone=ta.getBoolean(8);
				boolean isdelete=ta.getBoolean(9);
				String username=ta.getString(10);
				String projectname=ta.getString(11);
				String scenename=ta.getString(12);
				String taskpriority=ta.getString(13);
				String taskshared=ta.getString(14);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
				if(starttime_temp!=null){
				String starttime = sdf.format(starttime_temp);
				t.settaskstarttime(starttime);
				}
				if(endtime_temp!=null){
				String endtime=sdf.format(endtime_temp);
				t.settaskendtime(endtime);
				}
				t.setid(id);
				t.settaskname(taskname);
				t.settaskinformation(info);
				t.settasklabel(lable);
				t.setisdoing(isdoing);
				t.setisdone(isdone);
				t.setisdelete(isdelete);
				t.setusername(username);
				t.setprojectname(projectname);
				t.setscenename(scenename);
				t.settaskpriority(taskpriority);
				t.settaskshared(taskshared);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return t;
		}
		private Contact creatContact(ResultSet conta) {
			// TODO Auto-generated method stub
			Contact con=new Contact();
			try{
			String groupname=conta.getString(2);
			String contactname=conta.getString(3);//��ע����.
			String local_username=conta.getString(4);
			String addr_username=conta.getString(5);
			con.setgroupname(groupname);
			con.setcontactname(contactname);
			con.setlocal_username(local_username);
			con.setadder_username(addr_username);
			}catch(Exception x){
				System.out.println("Server��ʼContactʧ��");
			}
			return con;
		}
		public void doTheServe(Object k){
    		DataBaseFactory fac=new DataBaseFactory(k);
    	    DataBase x1=fac.creatDataBaseObject();
    	    User pp=(User) k;
    	   // System.out.println(pp.getpassword());
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
