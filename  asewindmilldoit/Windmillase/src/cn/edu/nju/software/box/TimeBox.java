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
    public ArrayList<Task> alltask = new ArrayList<Task>();
    LocalDataControl dc;
    ArrayList<Time> times=new ArrayList<Time>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
	   //然后调用本类的spiltIntoTime()方法拆分成很多Time，加进这里
    Time currentTime;//外部GUI点击一个时间模式下一个分类后，会修改这一属性，如点击今日待办
    public TimeBox(){
    	
    }
    
    Calendar c =Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH)+1;
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
   	   	   	//	System.out.println("add sucess");

   	for(Iterator<Task> it = alltask.iterator();it.hasNext();){
			Task tk = it.next();
			//System.out.print(tk.gettaskstarttime());
			if((tk.gettaskstarttime()!=null)){
			int temp_year=Integer.parseInt(tk.gettaskstarttime().substring(0,4));
			int temp_month=Integer.parseInt(tk.gettaskstarttime().substring(4,6));
			int temp_day=Integer.parseInt(tk.gettaskstarttime().substring(6,8));
			String isdoing = tk.getisdoing();
		//	System.out.println(temp_year);
		//	System.out.println(temp_month);
		//	System.out.println(temp_day);
			//收集箱中的taskstarttime()
			if(temp_year==year && "true".equals(isdoing)&&(temp_month==month)&& temp_day==day){

						today.addTaskIntime(tk);
						//System.out.println("添加成功了");
					}
			if(temp_year==year && "true".equals(isdoing)&&(temp_month==month)&&(temp_day==(day+1))){
						
						tomorrow.addTaskIntime(tk);
						//System.out.println("qs");
					}
			if((tk.gettaskstarttime().equals(null))&&"true".equals(isdoing)){
						option.addTaskIntime(tk);
					}
			
			if("false".equals(isdoing)){
						wait.addTaskIntime(tk);
					}
			//------------------------------------------
			if(temp_year>year && "true".equals(isdoing)){
				//System.out.println("ds");
				schedule.addTaskIntime(tk);
			}
				
			if(temp_month>month && "true".equals(isdoing)){
				//System.out.println("ds");
				schedule.addTaskIntime(tk);
			}
			
			if(temp_day>(day+1) && "true".equals(isdoing)){
				//System.out.println("ds");
				schedule.addTaskIntime(tk);
			}
			}
   		}
   	 	//writeTimeInFile(); //写入文件
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
	//writeTimeInFile();
}

@Override
public void complete(SqlData x) {
	// TODO Auto-generated method stub
	Task tk = (Task)x;
	currentTime.completeTaskIntime(tk);
	//writeTimeInFile();
}

@Override
public void delete(SqlData x) {
	// TODO Auto-generated method stub
	Task tk = (Task)x;
	currentTime.deleteTaskIntime(tk);
	//writeTimeInFile();
}

@Override
public void edit(SqlData x) {
	// TODO Auto-generated method stub
	Task tk = (Task)x;
	currentTime.editTaskIntime(tk);
	//writeTimeInFile();
}

@Override
public void setLocalDataControl(LocalDataControl x) {
	// TODO Auto-generated method stub
	dc=x;
	alltask=x.getTask();
}
	
}
