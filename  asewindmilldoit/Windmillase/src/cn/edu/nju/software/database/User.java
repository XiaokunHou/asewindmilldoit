package cn.edu.nju.software.database;

import java.io.Serializable;

import cn.edu.nju.software.control.Control.Operation;

public class User extends SqlData implements Serializable{
//����Ϊ���б�Ҫ���û����ԣ�����������Կ���ʹ�����ݿ����������user��صı�
    private int id;
    private String username;
    private String password;
    
   // private int infoid;
    private String unickname;
    private String uemail;
    private String usex;
    private String ubirthday;
    private String uremark;
    
    private Operation op;//����ͻ��˾������ʱ���޸���һ����
    
    /**
    public User(int ux,String username,String password,int infoid,String unickname,
    			String uemail,String usex,String ubirthday,String uremark)
    {
    	this.id=ux;
    }
    */
    
    public User(int id,String username,String password)
{
	this.id=id;
	this.username=username;
	this.password=password;
}
    
    public User(int id) {
		// TODO Auto-generated constructor stub
    	this.id=id;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}

	/**
     * �����ֵ
     * @return
     */
    //-------------------------------------------------------------------
    public int getid(){
    	return id;
    }
    
    public void setid(int id){
    	this.id=id;
    }
    
    public String getusername(){
    	return username;
    }
    
    public void setusername(String username){
    	this.username = username;
    }
    
    public String getpassword(){
    	return password;
    }
    
    public void setpassword(String password){
    	this.password = password;
    }
  /**  
    public int getinfoid(){
    	return infoid;
    }
    
    public void setinfoid(int infoid){
    	this.infoid = infoid;
    }
    */
    public String getunickname(){
    	return unickname;
    }
    
    public void setunickname(String unickname){
    	this.unickname = unickname;
    }
    
    public String getuemail(){
    	return uemail;
    }
    
    public void setuemail(String uemail){
    	this.uemail = uemail;
    }
    
    public String getusex(){
    	return usex;
    }
    
    public void setusex(String usex){
    	this.usex = usex;
    }
    
    public String getubirthday(){
    	return ubirthday;
    }
    
    public void setubirthday(String ubirthday){
    	this.ubirthday = ubirthday;
    }
    
    public String geturemark(){
    	return uremark;
    }
    
    public void seturemark(String uremark){
    	this.uremark = uremark;
    }

	public void setInfo(String name, String password2) {
		// TODO Auto-generated method stub
		this.username=name;
		this.password=password2;
	}
    
    //------------------------------------------------------------
}
