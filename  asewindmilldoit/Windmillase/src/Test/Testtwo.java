package Test;

import java.sql.Connection;

import cn.edu.nju.software.control.LoginControl;
import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.database.User;

public class Testtwo {
	User k=new User();
	  private Connection conn;
	   public static void main(String args[]){
		   Testtwo p=new Testtwo();
		   p.go();
	   }
	   public void go(){
		   k.setInfo("tianzhishen2","820417xcs");
		   k.setOpration(Operation.QUERY);
//		   System.out.println(k.getOperation()+"client");
		   LoginControl la=new LoginControl();
		   la.getLogin(k);
	   }
}
