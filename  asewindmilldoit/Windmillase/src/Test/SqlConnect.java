package Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnect {
	public static Connection getConn(){
		  Connection conn=null;
		  String url="jdbc:mysql://localhost/test?user=root&password=820417xcs&useUnicode=true&characterEncoding=gbk";
		  try{
			  Class.forName("com.mysql.jdbc.Driver");
			  conn=DriverManager.getConnection(url);
			 
		  }catch(Exception x){
			  x.printStackTrace();
		  }
		  return conn;
	  }
}
