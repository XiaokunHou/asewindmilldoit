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
	   }
	   public void deleteTaskIntime(Task x){
		   //Task��delete����ΪTrue��������������
		   //����findTask()Ȼ���tasks��ɾ��
		   for(int i=0;i<tasks.size();i++){
	 			if(tasks.get(i).getid()==x.getid()){
	 				tasks.get(i).setisdelete(true);
	 			}
	 		}
	   }
	   public void editTaskIntime(Task x){
		   //�༭Task���޸�
		 //����findTask()Ȼ���tasks���޸�
		   for(int i=0;i<tasks.size();i++){
				if(tasks.get(i).getid()==x.getid()){
					tasks.set(i, x);
				}
			}
	   }
	   public void completeTaskIntime(Task x){
		 //����findTask()Ȼ���tasks���ҵ�Ȼ����������hasdoneΪtrue
		   for(int i=0;i<tasks.size();i++){
				if(tasks.get(i).getid()==x.getid()){
					tasks.get(i).setisdone(true);
				}
			}
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
