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
   ArrayList<String> pn = new ArrayList<String>(); //得到该用户的所有的项目名称
   ArrayList<Project> projects=new ArrayList<Project>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
   //然后调用本类的spiltIntoProject()方法拆分成很多Project，加进这里
   Project currentProject;//外部GUI点击一个工程模式下一个分类后，会修改这一属性，如点击Project1
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
		//联网时候调用
		//调用本类的spiltIntoProject()方法将Task链表拆分成很多Project，加进这里
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
	//	writeProjectInFile(); //写入文件
	}
	
	
	public ArrayList<Project> getAllProject(){
		//GUI可以通过此方法得到所有Project
		  return projects;
	}
	public void writeProjectInFile(){
		//当有addTask()等操作后调用
		//把projects写入文件
	try{	
		File file=new File("LocalData/projects.txt");
		FileOutputStream output=new FileOutputStream(file);
		ObjectOutputStream  x=new ObjectOutputStream(output);
		x.writeObject(projects);
		x.close();
	 }catch(Exception x){
		 System.out.println("写入文件错误");
	 }
	}
	public void readProjectFromFile(){
		//不联网时候调用从本地文件读入ArrayList<Project>  放入projects
		try{	
			File file=new File("LocalData/projects.txt");
			FileInputStream output=new FileInputStream(file);
			ObjectInputStream  x=new ObjectInputStream(output);
			projects=(ArrayList<Project>)x.readObject();
			x.close();
		 }catch(Exception x){
			 System.out.println("读入文件错误");
		 }
	}

	//下面是给GUI接口，在一个模式的分类下增删等操作

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
