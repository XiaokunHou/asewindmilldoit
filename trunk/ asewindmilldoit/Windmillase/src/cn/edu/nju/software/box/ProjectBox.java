package cn.edu.nju.software.box;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Iterator;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.Task;

public class ProjectBox extends CollectBox{

   ArrayList<Task> alltask;
   LocalDataControl dc;
   ArrayList<String> pn = new ArrayList<String>(); //�õ����û������е���Ŀ����
   ArrayList<Project> projects=new ArrayList<Project>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
   //Ȼ����ñ����spiltIntoProject()������ֳɺܶ�Project���ӽ�����
   Project currentProject;//�ⲿGUI���һ������ģʽ��һ������󣬻��޸���һ���ԣ�����Project1
  // private String projectname;
   
	public void setTaskList(LocalDataControl x){
		dc=x;
	}
	public void setCurrentProject(Project x){
		currentProject=x;
	}
	public void spiltIntoProject(){
		//����ʱ�����
		//���ñ����spiltIntoProject()������Task�����ֳɺܶ�Project���ӽ�����
		//int j=0;
		for(Iterator<Task> it = alltask.iterator();it.hasNext();){
			Task tk = it.next();
			String projectname=tk.getprojectname();
			while(!pn.contains(projectname)){
				//pn.add(j, projectname);
				//j++;
				pn.add(projectname);
			}
		}
		for(int i=0;i<pn.size();i++){
			Project m = new Project();
			m.setProjectName(pn.get(i));
			for(Iterator<Task> it = alltask.iterator();it.hasNext();){
				Task tk = it.next();
				if(tk.getprojectname().equals(m.getProjectName())){
					m.addTaskInpro(tk);
				}
			}
			projects.add(m);
		}

	}
	
	
	public ArrayList<Project> getAllProject(){
		//GUI����ͨ���˷����õ�����Project
		  return projects;
	}
	public void writeProjectInFile(){
		//����addTask()�Ȳ��������
		//��projectsд���ļ�
		
	}
	public void readProjectFromFile(){
		//������ʱ����ôӱ����ļ�����ArrayList<Project>  ����projects
	}

	//�����Ǹ�GUI�ӿڣ���һ��ģʽ�ķ�������ɾ�Ȳ���
	@Override
	public void addTask(Task x) {
		// TODO Auto-generated method stub
		//currentProject.add(x);
		currentProject.addTaskInpro(x);
		writeProjectInFile();
	}
	@Override
	public void completeTask(Task x) {
		// TODO Auto-generated method stub
		currentProject.completeTaskInpro(x);
		writeProjectInFile();
	}
	@Override
	public void deleteTask(Task x) {
		// TODO Auto-generated method stub
		currentProject.deleteTaskInpro(x);
		writeProjectInFile();
	}
	@Override
	public void editTask(Task x) {
		// TODO Auto-generated method stub
		//�ⲿ�༭������������棬Ȼ�����ô˷���
		currentProject.editTaskInpro(x);
		writeProjectInFile();
	}
	@Override
	public void emptyRubbish() {
		// TODO Auto-generated method stub
		for(int i=0;i<alltask.size();i++){
			if(alltask.get(i).getisdelete()){
				currentProject.removeTaskFrompro(alltask.get(i));
				writeProjectInFile();
			}
		}
		
	}
	

}
