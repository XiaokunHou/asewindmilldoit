package cn.edu.nju.software.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Test.SqlConnect;

public class DataBaseUser extends DataBase{
    private User user;
    private Connection conn;
	@Override
	public void delete(Object x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean insert(Object x) {
		// TODO Auto-generated method stub
		boolean success=false;
		user=(User) x;
		String url="insert into user (name,passd,email) values ('"+user.name+"','"+user.password+"','"+user.email+"')";
		try{  
			  conn=SqlConnect.getConn();
			  Statement ste=conn.createStatement();
			  ste.executeUpdate(url);
			  success=true;
		  }catch(Exception t){
			  System.out.println("数据库插入User失败");
		  }
		  
		return success;
	}

	@Override
	public ResultSet query(Object x) {
		// TODO Auto-generated method stub
		user=(User)x;
		ResultSet res=null;
		String url="select * from user where name='"+user.name+"' and passd='"+user.password+"'";
		try{  
			  conn=SqlConnect.getConn();
			  Statement ste=conn.createStatement();
			  res=ste.executeQuery(url);
			  
		  }catch(Exception t){
			  System.out.println("数据库查User失败");
		  }
		  
		return res;
	}

	@Override
	public void update(Object x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setobject(Object x) {
		// TODO Auto-generated method stub
		user=(User) x;
	}
	
}
