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
	private int local_id;
	private int adder_id;
	//-------------------------------------
	
	public Contact(int id,String groupname,String contactname,int local_id,int adder_id){
		this.adder_id=adder_id;
		this.contactname=contactname;
		//this.group_isdefault=group_isdefault;
		this.id=id;
		this.groupname=groupname;
		this.local_id=local_id;
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
	
	public int getlocal_id(){
		return local_id;
	}
	
	public void setlocal_id(int local_id){
		this.local_id=local_id;
	}
	
	public int getadder_id(){
		return adder_id;
	}
	
	public void setadder_id(int adder_id){
		this.adder_id=adder_id;
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
