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
		   findTask(x);
		   tasks.get(index).setisdelete(true);
	   }
	   public void editTaskInScene(Task x){
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
	   public void completeTaskInScene(Task x){
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
