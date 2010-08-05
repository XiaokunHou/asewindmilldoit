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
   public ArrayList<Task> getTaskInpro(){
	   return tasks;
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
	   for(int i=0;i<tasks.size();i++){
 			if(tasks.get(i).getid()==x.getid()){
 				tasks.get(i).setisdelete(true);
 			}
 		}
   }
   public void editTaskInpro(Task x){ //使用的是浅拷贝
	   //编辑Task，修改
	 //调用findTask()然后从tasks中修改
	   for(int i=0;i<tasks.size();i++){
			if(tasks.get(i).getid()==x.getid()){
				tasks.set(i, x);
			}
		}
	   
   }
   public void completeTaskInpro(Task x){ //使用的是浅拷贝
	 //调用findTask()然后从tasks中找到然后设置他的hasdone为true
	   for(int i=0;i<tasks.size();i++){
			if(tasks.get(i).getid()==x.getid()){
				tasks.get(i).setisdone(true);
			}
		}
   }
   
   /*public void removeTaskFrompro(Task x){
	   for(int i=0;i<tasks.size();i++){
			if(tasks.get(i).getid()==x.getid()){
				tasks.set(i, x);
			}
		}
   }*/
   
   public int findTask(Task x){
	   //根据Task的name找到tasks中的所在Task并return
	   index=0;
			while(!(tasks.get(index).gettaskname()).equals(x.gettaskname())){
				index++;
			}
	   
	   return index;
   }
}
