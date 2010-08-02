package cn.edu.nju.software.database;

import java.io.Serializable;

import cn.edu.nju.software.control.Control.Operation;

public class User extends SqlData implements Serializable{
//这里为所有必要的用户属性，即这里的属性可以使得数据库操作各个与user相关的表
    private int uid;
   // private Operation op;//这里客户端具体操作时候，修改这一属性
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
