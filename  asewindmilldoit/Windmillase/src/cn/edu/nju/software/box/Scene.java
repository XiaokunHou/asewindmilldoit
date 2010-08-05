package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class Scene {
	ArrayList<Task> tasks=new ArrayList<Task>();
	String scenename;//�龰����
	int index;
	public String getSceneName(){
		   return scenename;
	}
	public void setSceneName(String x){
		   scenename=x;
	}
	   public void addTaskInScene(Task x){
		  
		   //add��ʱ�����ֱ��tasks.add()
		   tasks.add(x);
	   }
	   public void deleteTaskInScene(Task x){
		   //Task��delete����ΪTrue��������������
		   //����findTask()Ȼ���tasks��ɾ��
		   for(int i=0;i<tasks.size();i++){
	 			if(tasks.get(i).getid()==x.getid()){
	 				tasks.get(i).setisdelete(true);
	 			}
	 		}
	   }
	   public void editTaskInScene(Task x){
		   //�༭Task���޸�
		 //����findTask()Ȼ���tasks���޸�
		   for(int i=0;i<tasks.size();i++){
				if(tasks.get(i).getid()==x.getid()){
					tasks.set(i, x);
				}
			}
	   }
	   public void completeTaskInScene(Task x){
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
