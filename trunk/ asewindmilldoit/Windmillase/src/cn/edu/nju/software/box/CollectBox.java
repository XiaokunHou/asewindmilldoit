package cn.edu.nju.software.box;

import cn.edu.nju.software.database.Task;

public abstract class CollectBox {
	   String name;//ÊÕ¼¯ÏäÃû×Ö
	   public abstract void addTask(Task x);
	   public abstract void deleteTask(Task x);
	   public abstract void editTask(Task x);
	   public abstract void completeTask(Task x);
	}
