package cn.edu.nju.software.box;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.SqlData;
import cn.edu.nju.software.database.Task;

public abstract class CollectBox {
	   String name;//�ռ�������
	   public abstract void add(SqlData x);
	   public abstract void delete(SqlData x);//�����ɾ������ʱ����ü��޸�һ������
	   public abstract void edit(SqlData x);
	   public abstract void complete(SqlData x);
	   public abstract void setLocalDataControl(LocalDataControl x);
	  // public abstract void emptyRubbish();//��������������
	}
