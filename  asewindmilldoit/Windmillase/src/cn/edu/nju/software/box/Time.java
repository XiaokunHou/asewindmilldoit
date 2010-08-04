package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class Time {
	ArrayList<Task> tasks=new ArrayList<Task>();
	String timename;//时间模式中的名字
	int index;
	public String getTimeName(){
		   return timename;
	   }
	   public void setTimeName(String x){
		   timename=x;
	   }
	   public void addTaskIntime(Task x){
		   //add的时候可以直接tasks.add()
		   tasks.add(x);
		   findTask(x);
		   tasks.get(index).setisdelete(true);
	   }
	   public void deleteTaskIntime(Task x){
		   //Task的delete属性为True，即塞入垃圾箱
		   //调用findTask()然后从tasks中删除
		   findTask(x);
		   tasks.get(index).setisdelete(true);
	   }
	   public void editTaskIntime(Task x){
		   //编辑Task，修改
		 //调用findTask()然后从tasks中修改
		   findTask(x);
		   tasks.get(index).setprojectname(x.getprojectname());
		   tasks.get(index).setscenename(x.getscenename());
		   tasks.get(index).settaskendtime(x.gettaskendtime());
		   tasks.get(index).settaskinformation(x.gettaskinformation());
		   tasks.get(index).settasklabel(x.gettasklabel());
		   tasks.get(index).settaskname(x.gettaskname());
		   tasks.get(index).settaskpriority(x.gettaskpriority());
		   tasks.get(index).settaskshared(x.gettaskshared());
		   tasks.get(index).settaskstarttime(x.gettaskstarttime());
	   }
	   public void completeTaskIntime(Task x){
		 //调用findTask()然后从tasks中找到然后设置他的hasdone为true
		   findTask(x);
		   tasks.get(index).setisdone(true);
	   }
	   public int findTask(Task x){
		   //根据Task的name找到tasks中的所在Task并return
		   index=0;
			while(!(tasks.get(index).gettaskname()).equals(x.gettaskname())){
				index++;
			}
	   
	   return index;
	   }
}
