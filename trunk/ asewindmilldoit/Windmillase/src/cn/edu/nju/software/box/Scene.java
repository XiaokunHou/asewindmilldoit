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
		   findTask(x);
		   tasks.get(index).setisdelete(true);
	   }
	   public void editTaskInScene(Task x){
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
	   public void completeTaskInScene(Task x){
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
