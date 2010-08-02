package Test;

import java.util.ArrayList;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.database.User;

public class TestLocal {
	ArrayList<User> pp;
   public static void main(String args[]){
	   TestLocal m=new TestLocal();
	  // m.go();
	   m.go2();
   }

private void go() {
	// TODO Auto-generated method stub
	pp=new ArrayList<User>();
	User k=new User();
	  k.setInfo("hanzhenjie23","820417xcs");
	   k.setOpration(Operation.QUERY);
	  pp.add(k);
	  k=new User();
	  k.setInfo("tianzhishen2","820417");
	   k.setOpration(Operation.INSERT);
	   pp.add(k);
	  LocalDataControl control=new LocalDataControl();
	  control.setData(pp);
	  control.writeInFile();
}
 public void go2(){
	 LocalDataControl control=new LocalDataControl();
	 control.loadFromLocal();
	 ArrayList<User> x=control.getUser();
	 System.out.println(x.get(0).getOperation());
	 System.out.println(x.get(1).getOperation());
 }
}
