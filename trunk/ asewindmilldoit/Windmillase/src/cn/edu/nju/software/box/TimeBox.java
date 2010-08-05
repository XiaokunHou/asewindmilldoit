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
    ArrayList<Time> times=new ArrayList<Time>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
	   //Ȼ����ñ����spiltIntoTime()������ֳɺܶ�Time���ӽ�����
    Time currentTime;//�ⲿGUI���һ��ʱ��ģʽ��һ������󣬻��޸���һ���ԣ��������մ���
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
		//��������
		//���ñ����spiltIntoTime()������Task�����ֳɺܶ�Time���ӽ������times

	   //�Ѿ�����Ҫ�ж��Ƿ����ĸ�ʱ�����ƣ���Ϊ���ǹ̶��ģ���5������
	   	Time today=new Time();
   			today.setTimeName("���մ���");
   		Time tomorrow=new Time();
   	   		tomorrow.setTimeName("���մ���");
   	   	Time schedule=new Time();
   	   		schedule.setTimeName("�ճ�");
   	   	Time option=new Time();
   	   		option.setTimeName("���մ���");
   	   	Time wait=new Time();
   	   		wait.setTimeName("�ȴ�");
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
			//�ռ����е�taskstarttime()
			if(temp_year==year && "true".equals(isdoing)&&(temp_month==month)&& temp_day==day){

						today.addTaskIntime(tk);
						//System.out.println("��ӳɹ���");
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
   	 	//writeTimeInFile(); //д���ļ�
	}
   public ArrayList<Time> getAllTime(){
		//GUI����ͨ���˷����õ�����Time
		  return times;
	}
   public void writeTimeInFile(){
		//����addTask()�Ȳ��������
		//��timesд���ļ�
	   try{	
			File file=new File("LocalData/projects.txt");
			FileOutputStream output=new FileOutputStream(file);
			ObjectOutputStream  x=new ObjectOutputStream(output);
			x.writeObject(times);
			x.close();
		 }catch(Exception x){
			 System.out.println("д���ļ�����");
		 }
	}
	public void readTimeFromFile(){
		//������ʱ����ôӱ����ļ�����ArrayList<Time>  ����times
		try{	
			File file=new File("LocalData/projects.txt");
			FileInputStream output=new FileInputStream(file);
			ObjectInputStream  x=new ObjectInputStream(output);
			times=(ArrayList<Time>)x.readObject();
			x.close();
		 }catch(Exception x){
			 System.out.println("�����ļ�����");
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
