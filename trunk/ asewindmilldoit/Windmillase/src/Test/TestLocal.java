package Test;

import java.util.ArrayList;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.control.Control.Operation;
import cn.edu.nju.software.database.Contact;
import cn.edu.nju.software.database.*;

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
	 ArrayList<User> user=control.getUser();
	 ArrayList<Contact> contact=control.getContact();
	 ArrayList<Task> task=control.getTask();
	 for(int i=0;i<contact.size();i++){
		 System.out.print(contact.get(i).getcontactname()+"  ");
		 System.out.println(contact.get(i).getadder_username());
	 }
	for(int i=0;i<task.size();i++){
		 System.out.print(task.get(i).gettaskname()+"  ");
		 System.out.println(task.get(i).getscenename());
	}
	 //System.out.println(user.get(0).getunickname());
 }
}
