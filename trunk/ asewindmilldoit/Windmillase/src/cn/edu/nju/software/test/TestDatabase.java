package cn.edu.nju.software.test;

import cn.edu.nju.software.database.DataBaseUser;
import cn.edu.nju.software.database.User;

public class TestDatabase {
	DataBaseUser o=new DataBaseUser();
	  User k=new User();
	  public static void main(String arg[]){
		  TestDatabase l=new TestDatabase();
		  l.go();
	  }
	private void go() {
		// TODO Auto-generated method stub
		k.setpassword("820417");
		k.setusername("ase");
		o.insert(k);
	}

}
