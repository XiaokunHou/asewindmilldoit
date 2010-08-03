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
    ArrayList<String> tn = new ArrayList<String>(); //�õ����û������е�ʱ��ģʽ
	ArrayList<Time> times=new ArrayList<Time>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
	   //Ȼ����ñ����spiltIntoTime()������ֳɺܶ�Time���ӽ�����
    Time currentTime;//�ⲿGUI���һ��ʱ��ģʽ��һ������󣬻��޸���һ���ԣ��������մ���
    
	public void setTaskList(LocalDataControl x){
			//TaskMode���ã�����LocalDataControl��Task����
		   //��������
		dc=x;
		alltask=x.getTask();
	}
	public void setCurrentTime(Time t){
			currentTime=t;
	}
   public void spiltIntoTime(){
		//��������
		//���ñ����spiltIntoTime()������Task�����ֳɺܶ�Time���ӽ������times
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
		 writeTimeInFile(); //д���ļ�
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
