package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class Scene {
	ArrayList<Task> tasks=new ArrayList<Task>();
	String name;//�龰����
	public String getName(){
		   return name;
	   }
	   public void setName(String x){
		   name=x;
	   }
	   public void addTaskInScene(Task x){
		  
		   //add��ʱ�����ֱ��tasks.add()
	   }
	   public void deleteTaskInScene(Task k){
		   //Task��delete����ΪTrue��������������
		   //����findTask()Ȼ���tasks��ɾ��
	   }
	   public void editTaskInScene(Task x){
		   //�༭Task���޸�
		 //����findTask()Ȼ���tasks���޸�
	   }
	   public void completeTaskInScene(Task x){
		 //����findTask()Ȼ���tasks���ҵ�Ȼ����������hasdoneΪtrue
	   }
	   public Task findTask(Task x){
		   //����Task��name�ҵ�tasks�е�����Task��return
		   return null;
	   }  
}
