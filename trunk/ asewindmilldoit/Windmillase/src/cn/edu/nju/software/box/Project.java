package cn.edu.nju.software.box;

import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.nju.software.database.Task;

public class Project {
   ArrayList<Task> tasks=new ArrayList<Task>();
   String projectname;//��������
   int index;
   public String getProjectName(){
	   return projectname;
   }
   public ArrayList<Task> getTaskInpro(){
	   return tasks;
   }
   public void setProjectName(String x){
	   projectname=x;
   }
   public void addTaskInpro(Task x){ //ʹ�õ���ǳ����
	   //add��ʱ�����ֱ��tasks.add()
	   tasks.add(x);
   }
   public void deleteTaskInpro(Task x){ //ʹ�õ���ǳ����
	   //Task��delete����ΪTrue��������������
	   //����findTask()Ȼ���tasks��ɾ��
	  // x.setisdelete(true);
	   for(int i=0;i<tasks.size();i++){
 			if(tasks.get(i).getid()==x.getid()){
 				tasks.get(i).setisdelete(true);
 			}
 		}
   }
   public void editTaskInpro(Task x){ //ʹ�õ���ǳ����
	   //�༭Task���޸�
	 //����findTask()Ȼ���tasks���޸�
	   for(int i=0;i<tasks.size();i++){
			if(tasks.get(i).getid()==x.getid()){
				tasks.set(i, x);
			}
		}
	   
   }
   public void completeTaskInpro(Task x){ //ʹ�õ���ǳ����
	 //����findTask()Ȼ���tasks���ҵ�Ȼ����������hasdoneΪtrue
	   for(int i=0;i<tasks.size();i++){
			if(tasks.get(i).getid()==x.getid()){
				tasks.get(i).setisdone(true);
			}
		}
   }
   
   /*public void removeTaskFrompro(Task x){
	   for(int i=0;i<tasks.size();i++){
			if(tasks.get(i).getid()==x.getid()){
				tasks.set(i, x);
			}
		}
   }*/
   
   public int findTask(Task x){
	   //����Task��name�ҵ�tasks�е�����Task��return
	   index=0;
			while(!(tasks.get(index).gettaskname()).equals(x.gettaskname())){
				index++;
			}
	   
	   return index;
   }
}
