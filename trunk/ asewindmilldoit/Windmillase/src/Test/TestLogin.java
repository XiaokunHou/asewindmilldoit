package Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import cn.edu.nju.software.control.LoginControl;
import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.database.*;


public class TestLogin {
  User k=new User();
  private Connection conn;
   public static void main(String args[]){
	   TestLogin p=new TestLogin();
	   p.go();
   }
   public void go(){
	   k.setInfo("hanzhenjie23","820417xcs");
	   k.setOpration(Operation.QUERY);
//	   System.out.println(k.getOperation()+"client");
	   LoginControl la=new LoginControl();
	   la.getLogin(k);
   }
   
}
