package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Contact;
import cn.edu.nju.software.database.Task;

public class Group {
  	ArrayList<Contact> cons = new ArrayList<Contact>();
  	String adder_username; //��ϵ������
  	int index;
  	
  	public String getContactName(){
  		return adder_username;
  	}
  	
  	public void setContactName(String x){
  		adder_username=x;
  	}
  	
  	public void addContactInGroup(Contact x){
  		cons.add(x);
  	}
  	
  	public void deleteContactInGroup(Contact x){
  		//findContact(x);
  		cons.remove(x);
  	}
  	
  	public void editContactInGroup(Contact x){
  		findContact(x);
  		cons.get(index).setadder_username(x.getadder_username());
  		cons.get(index).setcontactname(x.getcontactname());
  		cons.get(index).setgroupname(x.getgroupname());
  		cons.get(index).setlocal_username(x.getlocal_username()); //Ҳ���Բ���Ҫ
  	}
  	
  	 public int findContact(Contact x){
  	   //����Task��name�ҵ�tasks�е�����Task��return
  	   index=0;
  			while(!(cons.get(index).getadder_username()).equals(x.getadder_username())){
  				index++;
  			}
  	   
  	   return index;
     }
}
