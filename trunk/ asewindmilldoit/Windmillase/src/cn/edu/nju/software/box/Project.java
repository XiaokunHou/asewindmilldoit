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
	   findTask(x);
	   tasks.get(index).setisdelete(true);
	   //tasks.remove(x);
   }
   public void editTaskInpro(Task x){ //ʹ�õ���ǳ����
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
   public void completeTaskInpro(Task x){ //ʹ�õ���ǳ����
	 //����findTask()Ȼ���tasks���ҵ�Ȼ����������hasdoneΪtrue
	   findTask(x);
	   tasks.get(index).setisdone(true);
   }
   
   public void removeTaskFrompro(Task x){
	   //findTask(x);
	   tasks.remove(x);
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
