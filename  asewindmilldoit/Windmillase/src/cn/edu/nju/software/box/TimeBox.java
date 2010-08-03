package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class TimeBox extends CollectBox{
    ArrayList<Task> all;
	ArrayList<Time> times=new ArrayList<Time>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
	   //然后调用本类的spiltIntoTime()方法拆分成很多Time，加进这里
    Time currentTime;//外部GUI点击一个时间模式下一个分类后，会修改这一属性，如点击今日待办
    
	public void setTaskList(ArrayList<Task> x){
			//TaskMode调用，传入LocalDataControl的Task数组
		   //联网调用
		all=x;
	}
	public void setCurrentTime(Time t){
			currentTime=t;
	}
   public void spiltIntoTime(){
		//联网调用
		//调用本类的spiltIntoTime()方法将Task链表拆分成很多Time，加进这里的times
	}
   public ArrayList<Time> getAllTime(){
		//GUI可以通过此方法得到所有Time
		  return times;
	}
   public void writeTimeInFile(){
		//当有addTask()等操作后调用
		//把times写入文件
	}
	public void readTimeFromFile(){
		//不联网时候调用从本地文件读入ArrayList<Time>  放入times
	}

@Override
public void addTask(Task x) {
	// TODO Auto-generated method stub
	
}
@Override
public void completeTask(Task x) {
	// TODO Auto-generated method stub
	
}
@Override
public void deleteTask(Task x) {
	// TODO Auto-generated method stub
	
}
@Override
public void editTask(Task x) {
	// TODO Auto-generated method stub
	
}
@Override
public void emptyRubbish() {
	// TODO Auto-generated method stub
	
}
	

	
}
