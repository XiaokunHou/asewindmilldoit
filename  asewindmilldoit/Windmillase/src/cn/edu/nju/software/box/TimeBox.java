package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class TimeBox extends CollectBox{
    ArrayList<Task> all;
	ArrayList<Time> times=new ArrayList<Time>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
	   //Ȼ����ñ����spiltIntoTime()������ֳɺܶ�Time���ӽ�����
    Time currentTime;//�ⲿGUI���һ��ʱ��ģʽ��һ������󣬻��޸���һ���ԣ��������մ���
    
	public void setTaskList(ArrayList<Task> x){
			//TaskMode���ã�����LocalDataControl��Task����
		   //��������
		all=x;
	}
	public void setCurrentTime(Time t){
			currentTime=t;
	}
   public void spiltIntoTime(){
		//��������
		//���ñ����spiltIntoTime()������Task�����ֳɺܶ�Time���ӽ������times
	}
   public ArrayList<Time> getAllTime(){
		//GUI����ͨ���˷����õ�����Time
		  return times;
	}
   public void writeTimeInFile(){
		//����addTask()�Ȳ��������
		//��timesд���ļ�
	}
	public void readTimeFromFile(){
		//������ʱ����ôӱ����ļ�����ArrayList<Time>  ����times
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
