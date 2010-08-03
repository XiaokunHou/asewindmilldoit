package cn.edu.nju.software.box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.Task;

public class TimeBox extends CollectBox{
    ArrayList<Task> alltask;
    LocalDataControl dc;
    ArrayList<String> tn = new ArrayList<String>(); //得到该用户的所有的时间模式
	ArrayList<Time> times=new ArrayList<Time>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
	   //然后调用本类的spiltIntoTime()方法拆分成很多Time，加进这里
    Time currentTime;//外部GUI点击一个时间模式下一个分类后，会修改这一属性，如点击今日待办
    
	public void setTaskList(LocalDataControl x){
			//TaskMode调用，传入LocalDataControl的Task数组
		   //联网调用
		dc=x;
		alltask=x.getTask();
	}
	public void setCurrentTime(Time t){
			currentTime=t;
	}
   public void spiltIntoTime(){
		//联网调用
		//调用本类的spiltIntoTime()方法将Task链表拆分成很多Time，加进这里的times
	   for(Iterator<Task> it = alltask.iterator();it.hasNext();){
			Task tk = it.next();
			String timestarttime=tk.gettaskstarttime();
			while(!tn.contains(timestarttime)){
				//pn.add(j, projectname);
				//j++;
				tn.add(timestarttime);
			}
		}
		for(int i=0;i<tn.size();i++){
			Time t = new Time();
			t.setTimeName(tn.get(i));
			for(Iterator<Task> it = alltask.iterator();it.hasNext();){
				Task tk = it.next();
				if(tk.gettaskstarttime().equals(t.getTimeName())){
					t.addTaskInpro(tk);
				}
			}
			times.add(t);
		}
		 writeTimeInFile(); //写入文件
	}
   public ArrayList<Time> getAllTime(){
		//GUI可以通过此方法得到所有Time
		  return times;
	}
   public void writeTimeInFile(){
		//当有addTask()等操作后调用
		//把times写入文件
	   try{	
			File file=new File("LocalData/projects.txt");
			FileOutputStream output=new FileOutputStream(file);
			ObjectOutputStream  x=new ObjectOutputStream(output);
			x.writeObject(times);
			x.close();
		 }catch(Exception x){
			 System.out.println("写入文件错误");
		 }
	}
	public void readTimeFromFile(){
		//不联网时候调用从本地文件读入ArrayList<Time>  放入times
		try{	
			File file=new File("LocalData/projects.txt");
			FileInputStream output=new FileInputStream(file);
			ObjectInputStream  x=new ObjectInputStream(output);
			times=(ArrayList<Time>)x.readObject();
			x.close();
		 }catch(Exception x){
			 System.out.println("读入文件错误");
		 }
	}

@Override
public void addTask(Task x) {
	// TODO Auto-generated method stub
	currentTime.addTaskInpro(x);
	writeTimeInFile();
}
@Override
public void completeTask(Task x) {
	// TODO Auto-generated method stub
	currentTime.completeTaskInpro(x);
	writeTimeInFile();
}
@Override
public void deleteTask(Task x) {
	// TODO Auto-generated method stub
	currentTime.deleteTaskInpro(x);
	writeTimeInFile();
}
@Override
public void editTask(Task x) {
	// TODO Auto-generated method stub
	currentTime.editTaskInpro(x);
	writeTimeInFile();
}
@Override
public void emptyRubbish() {
	// TODO Auto-generated method stub
	for(int i=0;i<alltask.size();i++){
		if(alltask.get(i).getisdelete()){
			//currentProject.removeTaskFrompro(alltask.get(i));
			alltask.remove(alltask.get(i));
		}
	}
	writeTimeInFile();
}
	

	
}
