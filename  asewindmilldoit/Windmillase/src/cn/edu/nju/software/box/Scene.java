package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class Scene {
	ArrayList<Task> tasks=new ArrayList<Task>();
	String scenename;//情景名字
	int index;
	public String getSceneName(){
		   return scenename;
	}
	public void setSceneName(String x){
		   scenename=x;
	}
	   public void addTaskInScene(Task x){
		  
		   //add的时候可以直接tasks.add()
		   tasks.add(x);
	   }
	   public void deleteTaskInScene(Task x){
		   //Task的delete属性为True，即塞入垃圾箱
		   //调用findTask()然后从tasks中删除
		   for(int i=0;i<tasks.size();i++){
	 			if(tasks.get(i).getid()==x.getid()){
	 				tasks.get(i).setisdelete(true);
	 			}
	 		}
	   }
	   public void editTaskInScene(Task x){
		   //编辑Task，修改
		 //调用findTask()然后从tasks中修改
		   for(int i=0;i<tasks.size();i++){
				if(tasks.get(i).getid()==x.getid()){
					tasks.set(i, x);
				}
			}
	   }
	   public void completeTaskInScene(Task x){
		 //调用findTask()然后从tasks中找到然后设置他的hasdone为true
		   for(int i=0;i<tasks.size();i++){
				if(tasks.get(i).getid()==x.getid()){
					tasks.get(i).setisdone(true);
				}
			}
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
