package cn.edu.nju.software.box;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.SqlData;
import cn.edu.nju.software.database.Task;

public abstract class CollectBox {
	   String name;//收集箱名字
	   public abstract void add(SqlData x);
	   public abstract void delete(SqlData x);//这个是删除任务时候调用即修改一个属性
	   public abstract void edit(SqlData x);
	   public abstract void complete(SqlData x);
	   public abstract void setLocalDataControl(LocalDataControl x);
	  // public abstract void emptyRubbish();//这个是清空垃圾箱
	}
