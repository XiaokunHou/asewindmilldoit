package cn.edu.nju.software.database;

import java.sql.ResultSet;

public class DataBaseUser extends DataBase{
    private User user;
    //ResultSet rs;
    
    private String getDataBaseUser;
    
    public DataBaseUser(){
    	
    }
    
    public User getUser(String username,String password){
    	return user;
    }
    
	@Override
	public void delete(Object x) {
		// TODO Auto-generated method stub
		user=(User) x;
		getDataBaseUser="delete from user where username = '" + user.getusername()+ "'";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseUser)){
				System.out.print("ɾ���ɹ�");
				return;
			}else{
				System.out.print("ɾ��ʧ��");
				return;
			}
		}
	}

	@Override
	public boolean insert(Object x) {
		// TODO Auto-generated method stub
		user=(User) x;
		getDataBaseUser="insert into user(username,password,uemail) " +
				"values('" + user.getusername()+ "','"+user.getpassword()+"','"+user.getuemail()+"')";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseUser)){
				System.out.print("����ɹ�");
				return true;
			}else{
				System.out.print("����ʧ��");
				return false;
			}
		}else
		return false;
	}

	@Override
	public ResultSet query(Object x) {
		// TODO Auto-generated method stub
		user=(User) x;
		getDataBaseUser="select * from user where username = '" + user.getusername()+ "' and password = '" + user.getpassword()+ "'";
		if(DB.connectDB()){
			if(DB.query(getDataBaseUser)){
				return DB.rs;
			}else
				return null;
		}else
		return null;
		
	}

	@Override
	public void update(Object x) {
		// TODO Auto-generated method stub
		user=(User) x;
		getDataBaseUser="update user set usernickname = '" + user.getunickname()
						+ "' and password= '"+user.getpassword()
						+"' and uemail= '"+user.getuemail()
						+"' and usex= '"+user.getusex()
						+"' and ubirthday= '"+user.getubirthday()
						+"' and uremark= '"+user.geturemark()
						+"' where username = '" + user.getusername()+ "'";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseUser)){
				System.out.print("�޸ĳɹ�");
				return;
			}else{
				System.out.print("�޸�ʧ��");
				return;
			}
		}
	}

	@Override
	public void setobject(Object x) {
		// TODO Auto-generated method stub
		user=(User) x;
	}

}
