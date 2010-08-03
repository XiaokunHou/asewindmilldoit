package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class Project {
   ArrayList<Task> tasks=new ArrayList<Task>();
   String projectname;//工程名字
   public String getProjectName(){
	   return projectname;
   }
   public void setProjectName(String x){
	   projectname=x;
   }
   public void addTaskInpro(Task x){
	   //add的时候可以直接tasks.add()
   }
   public void deleteTaskInpro(Task k){
	   //Task的delete属性为True，即塞入垃圾箱
	   //调用findTask()然后从tasks中删除
   }
   public void editTaskInpro(Task x){
	   //编辑Task，修改
	 //调用findTask()然后从tasks中修改
   }
   public void completeTaskInpro(Task x){
	 //调用findTask()然后从tasks中找到然后设置他的hasdone为true
   }
   public Task findTask(Task x){
	   //根据Task的name找到tasks中的所在Task并return
	   return null;
   }
}
