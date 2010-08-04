package cn.edu.nju.software.box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.SqlData;
import cn.edu.nju.software.database.Task;

public class TimeBox extends CollectBox{
    ArrayList<Task> alltask;
    LocalDataControl dc;
    ArrayList<Time> times=new ArrayList<Time>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
	   //然后调用本类的spiltIntoTime()方法拆分成很多Time，加进这里
    Time currentTime;//外部GUI点击一个时间模式下一个分类后，会修改这一属性，如点击今日待办
    public TimeBox(){
    	
    }
    
    Calendar c =Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DATE);
	
	public void setCurrentTime(Time t){
			for(int i=0;i<times.size();i++){
				if(times.get(i).getTimeName().equals(t.getTimeName())){
					currentTime = times.get(i);
				}
			}
	}
   public void spiltIntoTime(){
		//联网调用
		//调用本类的spiltIntoTime()方法将Task链表拆分成很多Time，加进这里的times

	   //已经不需要判断是否是哪个时间名称，因为它是固定的，即5个名称
	   	Time today=new Time();
   			today.setTimeName("今日待办");
   		Time tomorrow=new Time();
   	   		tomorrow.setTimeName("明日待办");
   	   	Time schedule=new Time();
   	   		schedule.setTimeName("日程");
   	   	Time option=new Time();
   	   		option.setTimeName("择日待办");
   	   	Time wait=new Time();
   	   		wait.setTimeName("等待");
   	   		times.add(today);
   	   		times.add(tomorrow);
   	   		times.add(schedule);
   	   		times.add(option);
   	   		times.add(wait);
   	for(Iterator<Task> it = alltask.iterator();it.hasNext();){
			Task tk = it.next();
   		String temp_year=tk.gettaskstarttime().substring(0, 3);
			String temp_month=tk.gettaskstarttime().substring(4, 5);
			String temp_day=tk.gettaskstarttime().substring(6, 7);
			String isdoing = tk.getisdoing();
			//收集箱中的taskstarttime()为
			if((Integer.parseInt(temp_year)==year)&&isdoing.equals("true")&&(Integer.parseInt(temp_month)==month)&&(Integer.parseInt(temp_day)== day)){
						today.addTaskIntime(tk);
					}
			if((Integer.parseInt(temp_year)==year)&&isdoing.equals("true")&&(Integer.parseInt(temp_month)==month)&&(Integer.parseInt(temp_day)== (day+1))){
						tomorrow.addTaskIntime(tk);
					}
			if((tk.gettaskstarttime().equals(null))&&isdoing.equals("true")){
						option.addTaskIntime(tk);
					}
			if(isdoing.equals("false")){
						wait.addTaskIntime(tk);
					}
			//------------------------------------------
			if((Integer.parseInt(temp_year)>year)&&isdoing.equals("true")){
				schedule.addTaskIntime(tk);
			}
				
			if((Integer.parseInt(temp_month)>month)&&isdoing.equals("true")){
				schedule.addTaskIntime(tk);
			}
			
			if((Integer.parseInt(temp_day)>(day+1))&&isdoing.equals("true")){
				schedule.addTaskIntime(tk);
			}
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
public void add(SqlData x) {
	// TODO Auto-generated method stub
	Task tk = (Task)x;
	currentTime.addTaskIntime(tk);
	writeTimeInFile();
}

@Override
public void complete(SqlData x) {
	// TODO Auto-generated method stub
	Task tk = (Task)x;
	currentTime.completeTaskIntime(tk);
	writeTimeInFile();
}

@Override
public void delete(SqlData x) {
	// TODO Auto-generated method stub
	Task tk = (Task)x;
	currentTime.deleteTaskIntime(tk);
	writeTimeInFile();
}

@Override
public void edit(SqlData x) {
	// TODO Auto-generated method stub
	Task tk = (Task)x;
	currentTime.editTaskIntime(tk);
	writeTimeInFile();
}

@Override
public void setLocalDataControl(LocalDataControl x) {
	// TODO Auto-generated method stub
	dc=x;
	alltask=x.getTask();
}
	
}
