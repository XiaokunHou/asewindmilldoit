package cn.edu.nju.software.database;

import java.sql.ResultSet;

public abstract class DataBase {
   public abstract void delete(Object x);
   public abstract void update(Object x);
   public abstract boolean insert(Object x);
   public abstract ResultSet query(Object x);
   //����Ϊ�ⲿ�������ݿ�����Ľӿ�
   public abstract void setobject(Object x);//��������������
   
}
