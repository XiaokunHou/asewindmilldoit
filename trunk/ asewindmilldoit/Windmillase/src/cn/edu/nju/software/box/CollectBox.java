package cn.edu.nju.software.box;

import cn.edu.nju.software.database.Task;

public abstract class CollectBox {
	   String name;//收集箱名字
	   public abstract void addTask(Task x);
	   public abstract void deleteTask(Task x);//这个是删除任务时候调用即修改一个属性
	   public abstract void editTask(Task x);
	   public abstract void completeTask(Task x);
	   public abstract void emptyRubbish();//这个是清空垃圾箱
	}
