package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class ProjectBox extends CollectBox{
   ArrayList<Task> all;
   ArrayList<Project> projects=new ArrayList<Project>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
   //Ȼ����ñ����spiltIntoProject()������ֳɺܶ�Project���ӽ�����
   Project currentProject;//�ⲿGUI���һ������ģʽ��һ������󣬻��޸���һ���ԣ�����Project1
   
	public void setTaskList(ArrayList<Task> x){
		//ProjectMode���ã�����LocalDataControl��Task����
		//��������
		all=x;
	}
	public void setCurrentProject(Project x){
		currentProject=x;
	}
	public void spiltIntoProject(){
		//����ʱ�����
		//���ñ����spiltIntoProject()������Task�����ֳɺܶ�Project���ӽ�����
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
	}
	@Override
	public void completeTask(Task x) {
		// TODO Auto-generated method stub
		//currentProject.complete(x);
	}
	@Override
	public void deleteTask(Task x) {
		// TODO Auto-generated method stub
		//currentProject.delete(x);
	}
	@Override
	public void editTask(Task x) {
		// TODO Auto-generated method stub
		//�ⲿ�༭������������棬Ȼ�����ô˷���
		//currentProject.edit(x);
	}
	

}
