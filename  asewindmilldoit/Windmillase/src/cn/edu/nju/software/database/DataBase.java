package cn.edu.nju.software.database;

import java.sql.ResultSet;

public abstract class DataBase {
   public abstract void delete(Object x);
   public abstract void update(Object x);
   public abstract boolean insert(Object x);
   public abstract ResultSet query(Object x);
   //以上为外部调用数据库操作的接口
   public abstract void setobject(Object x);//传入具体操作对象
   
}
