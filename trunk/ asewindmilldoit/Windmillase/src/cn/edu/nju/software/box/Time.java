package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class Time {
	ArrayList<Task> tasks=new ArrayList<Task>();
	String name;//ʱ��ģʽ�е�����
	public String getName(){
		   return name;
	   }
	   public void setName(String x){
		   name=x;
	   }
	   public void addTaskInpro(Task x){
		   //add��ʱ�����ֱ��tasks.add()
	   }
	   public void deleteTaskInpro(Task k){
		   //Task��delete����ΪTrue��������������
		   //����findTask()Ȼ���tasks��ɾ��
	   }
	   public void editTaskInpro(Task x){
		   //�༭Task���޸�
		 //����findTask()Ȼ���tasks���޸�
	   }
	   public void completeTaskInpro(Task x){
		 //����findTask()Ȼ���tasks���ҵ�Ȼ����������hasdoneΪtrue
	   }
	   public Task findTask(Task x){
		   //����Task��name�ҵ�tasks�е�����Task��return
		   return null;
	   }
}
