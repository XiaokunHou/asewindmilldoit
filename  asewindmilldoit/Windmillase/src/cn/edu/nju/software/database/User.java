package cn.edu.nju.software.database;

import java.io.Serializable;

import cn.edu.nju.software.control.Control.Operation;

public class User extends SqlData implements Serializable{
//����Ϊ���б�Ҫ���û����ԣ�����������Կ���ʹ�����ݿ����������user��صı�
    private int uid;
   // private Operation op;//����ͻ��˾������ʱ���޸���һ����
   public  String password;
   public String name;
   public String email;
    public User(int ux){
    	uid=ux;
    }
	public User() {
		// TODO Auto-generated constructor stub
	}
    public void setInfo(String na,String pas){
    	password=pas;
    	name=na;
    }
    
}
