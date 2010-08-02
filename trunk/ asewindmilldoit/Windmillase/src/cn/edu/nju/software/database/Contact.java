package cn.edu.nju.software.database;

import java.io.Serializable;

public class Contact extends SqlData implements Serializable{
	private int id;
	private String contactname;
	private String group_isdefault;
	//-------------------------------------
	private int local_id;
	private int adder_id;
	//-------------------------------------
	
	public Contact(int id,String contactname,String group_isdefault,int local_id,int adder_id){
		this.adder_id=adder_id;
		this.contactname=contactname;
		this.group_isdefault=group_isdefault;
		this.id=id;
		this.local_id=local_id;
	}
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	public void set(int id){
		
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
	
	public String getcontactname(){
		return contactname;
	}
	
	public void setcontactname(String contactname){
		this.contactname=contactname;
	}
	
	public String getgroup_isdefault(){
		return group_isdefault;
	}
	
	public void setgroup_isdefault(String group_isdefault){
		this.group_isdefault=group_isdefault;
	}
}
