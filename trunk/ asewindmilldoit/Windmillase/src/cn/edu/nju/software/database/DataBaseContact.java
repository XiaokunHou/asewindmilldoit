package cn.edu.nju.software.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseContact extends DataBase{
    private Contact con;
    //private User user;
    
    private String getDataBaseContact;
    
    public DataBaseContact(){
    	
    }
    
    public Contact getDataBaseContact(){
    	return con;
    }
    
	@Override
	public void delete(Object x) {
		// TODO Auto-generated method stub
		con=(Contact) x;
		getDataBaseContact="delete from contact where adder_username = '" + con.getadder_username()
							+ "' and local_username ='"+con.getlocal_username()+"'";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseContact)){
				System.out.print("删除成功");
				return;
			}else{
				System.out.print("删除失败");
				return;
			}
		}
	}

	@Override
	public boolean insert(Object x) {
		// TODO Auto-generated method stub
		con=(Contact) x;
		if(hastheContact()){
		getDataBaseContact="insert into contact(groupname,contactname,local_username,adder_username) " +
		"values('" +con.getgroupname()+"','"+con.getcontactname()+"','"+con.getlocal_username()+ "','"+con.getadder_username()+"')";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseContact)){
				System.out.print("插入成功");
				return true;
			}else{
				System.out.print("插入失败");
				return false;
			}
		}else
			return false;
	}else 
		return false;
	}
	@Override
	public ResultSet query(Object x) {
		// TODO Auto-generated method stub
		con=(Contact) x;
		getDataBaseContact="select * from contact where loacl_username = '" + con.getlocal_username()+ "'";
		if(DB.connectDB()){
			if(DB.query(getDataBaseContact)){
				return DB.rs;
			}else
				return null;
		}else
		return null;
	}

	@Override
	public void update(Object x) {
		// TODO Auto-generated method stub
		con=(Contact) x;
		getDataBaseContact="update contact set groupname = '" + con.getgroupname()
		+ "' and contactname= '"+con.getcontactname()
		+ "'where local_username='"+con.getlocal_username()
		+"' and adder_username= '"+con.getadder_username()+"'";
		//+"' and local_id= '"+con.getlocal_id()
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseContact)){
				System.out.print("修改成功");
				return;
			}else{
				System.out.print("修改失败");
				return;
}
}
	}

	@Override
	public void setobject(Object x) {
		// TODO Auto-generated method stub
		con=(Contact)x;
	}

	public boolean hastheContact(){
		boolean hasthecontact=false;
		getDataBaseContact="select * from contact where adder_username = '" + con.getadder_username()+ "'";
		if(DB.connectDB()){
			if(DB.query(getDataBaseContact)){
				try {
					if(DB.rs.next()){
						hasthecontact=true;
						return hasthecontact;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return hasthecontact;
	}
}
