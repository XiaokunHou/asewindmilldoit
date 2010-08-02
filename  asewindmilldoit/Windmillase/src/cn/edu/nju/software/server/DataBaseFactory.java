package cn.edu.nju.software.server;

import java.sql.ResultSet;

import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.database.*;

public class DataBaseFactory {
   private	Object clientdata;
   private DataBase product;
   private boolean in=false;
   private ResultSet set;
  public DataBaseFactory(Object x){
	  clientdata=x;
  }
  public DataBase creatDataBaseObject(){
	  if(clientdata.getClass().getName().equals("cn.edu.nju.software.database.User")){
		  product=new DataBaseUser();
	  }
	  if(clientdata.getClass().getName().equals("cn.edu.nju.software.database.Task")){
		  product=new DataBaseTask();
	  }
	  if(clientdata.getClass().getName().equals("cn.edu.nju.software.database.Contact")){
		  product=new DataBaseContact();
	  }
	  return product;
  }
  public void strategy(){
	  User oo=(User) clientdata;//debug
	 //System.out.println(oo.password);//debug
	 //SqlData k=(SqlData)clientdata;
	 Operation ss=oo.getOperation();
	 //System.out.println(ss);
	  switch(ss){
	  case DELETE:
		  product.delete(clientdata);
		  break;
	  case UPDATE:
		  product.update(clientdata);
		  break;
	  case QUERY:
		  set= product.query(clientdata);
		  break;
	  case INSERT:
		  in=product.insert(clientdata);
		  break;
	  }
  }
  public ResultSet getSet(){
	  return set;
  }
  public boolean inser(){
	  return in;
  }
}
