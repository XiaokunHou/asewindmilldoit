package cn.edu.nju.software.database;

import java.io.Serializable;

public class Contact extends SqlData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String groupname;
	private String contactname;
	//private String group_isdefault;
	//-------------------------------------
	private String local_username;
	private String adder_username;
	//-------------------------------------
	
	public Contact(int id,String groupname,String contactname,String local_username,String adder_username){
		this.adder_username=adder_username;
		this.contactname=contactname;
		//this.group_isdefault=group_isdefault;
		this.id=id;
		this.groupname=groupname;
		this.local_username=local_username;
	}
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}


	public int getid(){
		return id;
	}
	
	public void setid(int id){
		this.id=id;
	}
	
	public String getlocal_username(){
		return local_username;
	}
	
	public void setlocal_username(String local_username){
		this.local_username=local_username;
	}
	
	public String getadder_username(){
		return adder_username;
	}
	
	public void setadder_username(String adder_username){
		this.adder_username=adder_username;
	}
	
	public String getgroupname(){
		return groupname;
	}
	
	public void setgroupname(String groupname){
		this.groupname=groupname;
	}
	
	public String getcontactname(){
		return contactname;
	}
	
	public void setcontactname(String contactname){
		this.contactname=contactname;
	}	
}
