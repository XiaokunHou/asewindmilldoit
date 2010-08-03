package cn.edu.nju.software.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB {
	public static Connection cn;
	 public static Statement st;
	 public static ResultSet rs;
	 
	 public static boolean connectDB(){
		 boolean connectFlag;
		 try {
	            connectFlag = true;
	           
	            Class.forName("com.mysql.jdbc.Driver");
	            
	            cn = DriverManager.getConnection("jdbc:mysql://"+"127.0.0.1"+":3306"+"/gtdt","root","820417xcs");
	            
	            cn.setCatalog("gtdt");
	            System.out.println("数据库连接成功");
	            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
	                                    ResultSet.CONCUR_READ_ONLY);
	            return connectFlag;

	        } catch (SQLException sqlEx) {
	            System.out.println(sqlEx.getMessage());
	            JOptionPane.showMessageDialog(null,sqlEx.getMessage() );
	            connectFlag = false;
	            return connectFlag;

	        } catch (ClassNotFoundException notfoundEX) {
	            System.out.println(notfoundEX.getMessage());
	            JOptionPane.showMessageDialog(null,notfoundEX.getMessage() );
	            connectFlag = false;
	            return connectFlag;
	        }
	 }
	 
	 public static boolean executeSQL(String sqlString) {
	        boolean executeFlag;
	        try {
	            st.executeUpdate(sqlString);
	            executeFlag = true;
	        } catch (Exception e) {
	            executeFlag = false;
	            System.out.println("sql exception:" + e.getMessage());
	        }
	        return executeFlag;
	 }
	 
	 
	 
	 public static boolean query(String sqlString) {

	        try {
	            rs = null;
	            
	            rs = st.executeQuery(sqlString);
	        } catch (Exception Ex) {
	            System.out.println("sql exception:" + Ex);
	            return false;
	        }
	        return true;
	 }
}
