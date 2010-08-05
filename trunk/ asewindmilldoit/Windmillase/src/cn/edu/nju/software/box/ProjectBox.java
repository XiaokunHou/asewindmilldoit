package cn.edu.nju.software.box;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Iterator;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.SqlData;
import cn.edu.nju.software.database.Task;

public class ProjectBox extends CollectBox{

	public ArrayList<Task> alltask;
   LocalDataControl dc;
   ArrayList<String> pn = new ArrayList<String>(); //�õ����û������е���Ŀ����
   ArrayList<Project> projects=new ArrayList<Project>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
   //Ȼ����ñ����spiltIntoProject()������ֳɺܶ�Project���ӽ�����
   Project currentProject;//�ⲿGUI���һ������ģʽ��һ������󣬻��޸���һ���ԣ�����Project1
  // private String projectname;
   
	public void setCurrentProject(Project x){
		for(int i=0;i<projects.size();i++){
			if(projects.get(i).getProjectName().equals(x.getProjectName())){
				currentProject=projects.get(i);
			}
		}
		//currentProject=x;
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
	//	writeProjectInFile(); //д���ļ�
	}
	
	
	public ArrayList<Project> getAllProject(){
		//GUI����ͨ���˷����õ�����Project
		  return projects;
	}
	public void writeProjectInFile(){
		//����addTask()�Ȳ��������
		//��projectsд���ļ�
	try{	
		File file=new File("LocalData/projects.txt");
		FileOutputStream output=new FileOutputStream(file);
		ObjectOutputStream  x=new ObjectOutputStream(output);
		x.writeObject(projects);
		x.close();
	 }catch(Exception x){
		 System.out.println("д���ļ�����");
	 }
	}
	public void readProjectFromFile(){
		//������ʱ����ôӱ����ļ�����ArrayList<Project>  ����projects
		try{	
			File file=new File("LocalData/projects.txt");
			FileInputStream output=new FileInputStream(file);
			ObjectInputStream  x=new ObjectInputStream(output);
			projects=(ArrayList<Project>)x.readObject();
			x.close();
		 }catch(Exception x){
			 System.out.println("�����ļ�����");
		 }
	}

	//�����Ǹ�GUI�ӿڣ���һ��ģʽ�ķ�������ɾ�Ȳ���

	@Override
	public void add(SqlData x) {
		// TODO Auto-generated method stub
		Task tk = (Task)x;
		currentProject.addTaskInpro(tk);
		//writeProjectInFile();
		
	}
	@Override
	public void complete(SqlData x) {
		// TODO Auto-generated method stub
		Task tk = (Task)x;
		currentProject.completeTaskInpro(tk);
		//writeProjectInFile();
	}
	@Override
	public void delete(SqlData x) {
		// TODO Auto-generated method stub
		Task tk = (Task)x;
		currentProject.deleteTaskInpro(tk);
		//writeProjectInFile();
	}
	@Override
	public void edit(SqlData x) {
		// TODO Auto-generated method stub
		Task tk = (Task)x;
		currentProject.editTaskInpro(tk);
		//writeProjectInFile();
	}
	@Override
	public void setLocalDataControl(LocalDataControl x) {
		// TODO Auto-generated method stub
		dc=x;
		alltask=x.getTask();
	}
	

}
