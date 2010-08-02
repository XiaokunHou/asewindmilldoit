package cn.edu.nju.software.database;

import java.sql.ResultSet;

public class DataBaseContact extends DataBase{
    private Contact con;
	@Override
	public void delete(Object x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean insert(Object x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet query(Object x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setobject(Object x) {
		// TODO Auto-generated method stub
		con=(Contact)x;
	}

}
