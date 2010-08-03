package cn.edu.nju.software.box;

import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.nju.software.database.Task;

public class Project {
   ArrayList<Task> tasks=new ArrayList<Task>();
   String projectname;//工程名字
   int index;
   public String getProjectName(){
	   return projectname;
   }
   public void setProjectName(String x){
	   projectname=x;
   }
   public void addTaskInpro(Task x){ //使用的是浅拷贝
	   //add的时候可以直接tasks.add()
	   tasks.add(x);
   }
   public void deleteTaskInpro(Task x){ //使用的是浅拷贝
	   //Task的delete属性为True，即塞入垃圾箱
	   //调用findTask()然后从tasks中删除
	  // x.setisdelete(true);
	   findTask(x);
	   tasks.get(index).setisdelete(true);
	   //tasks.remove(x);
   }
   public void editTaskInpro(Task x){ //使用的是浅拷贝
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
   public void completeTaskInpro(Task x){ //使用的是浅拷贝
	 //调用findTask()然后从tasks中找到然后设置他的hasdone为true
	   findTask(x);
	   tasks.get(index).setisdone(true);
   }
   
   public void removeTaskFrompro(Task x){
	   //findTask(x);
	   tasks.remove(x);
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
