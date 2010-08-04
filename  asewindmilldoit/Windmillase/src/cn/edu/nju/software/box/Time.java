package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class Time {
	ArrayList<Task> tasks=new ArrayList<Task>();
	String timename;//ʱ��ģʽ�е�����
	int index;
	public String getTimeName(){
		   return timename;
	   }
	   public void setTimeName(String x){
		   timename=x;
	   }
	   public void addTaskIntime(Task x){
		   //add��ʱ�����ֱ��tasks.add()
		   tasks.add(x);
		   findTask(x);
		   tasks.get(index).setisdelete(true);
	   }
	   public void deleteTaskIntime(Task x){
		   //Task��delete����ΪTrue��������������
		   //����findTask()Ȼ���tasks��ɾ��
		   findTask(x);
		   tasks.get(index).setisdelete(true);
	   }
	   public void editTaskIntime(Task x){
		   //�༭Task���޸�
		 //����findTask()Ȼ���tasks���޸�
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
	   public void completeTaskIntime(Task x){
		 //����findTask()Ȼ���tasks���ҵ�Ȼ����������hasdoneΪtrue
		   findTask(x);
		   tasks.get(index).setisdone(true);
	   }
	   public int findTask(Task x){
		   //����Task��name�ҵ�tasks�е�����Task��return
		   index=0;
			while(!(tasks.get(index).gettaskname()).equals(x.gettaskname())){
				index++;
			}
	   
	   return index;
	   }
}
