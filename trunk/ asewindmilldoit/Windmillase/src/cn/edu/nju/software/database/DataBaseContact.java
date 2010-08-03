package cn.edu.nju.software.database;

import java.sql.ResultSet;

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
		getDataBaseContact="delete from contact where adder_id = '" + con.getadder_id()
							+ "' and local_id ='"+con.getlocal_id()+"'";
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
		getDataBaseContact="insert into contact(groupname,contactname,local_id,adder_id) " +
		"values('" +con.getgroupname()+"','"+con.getcontactname()+"','"+con.getlocal_id()+ "','"+con.getadder_id()+"')";
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
	}

	@Override
	public ResultSet query(Object x) {
		// TODO Auto-generated method stub
		con=(Contact) x;
		getDataBaseContact="select * from contact where loacl_id = '" + con.getlocal_id()+ "'";
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
		+ "'where local_id='"+con.getlocal_id()
		+"' and adder_id= '"+con.getadder_id()+"'";
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

}
