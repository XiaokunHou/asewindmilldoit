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

   ArrayList<Project> projects=new ArrayList<Project>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
   //然后调用本类的spiltIntoProject()方法拆分成很多Project，加进这里
   Project currentProject;//外部GUI点击一个工程模式下一个分类后，会修改这一属性，如点击Project1
   private String projectname;
   
	public void setTaskList(LocalDataControl x){
		dc=x;
	}
   private HashSet pn = new HashSet(); //得到该用户的所有的项目名称
   
	
	public void setCurrentProject(Project x){
		currentProject=x;
	}
	public void spiltIntoProject(){
		//联网时候调用
		//调用本类的spiltIntoProject()方法将Task链表拆分成很多Project，加进这里
		for(Iterator<Task> it = alltask.iterator();it.hasNext();){
			
		}

	}
	
	
	public ArrayList<Project> getAllProject(){
		//GUI可以通过此方法得到所有Project
		for(Iterator<Task> it = alltask.iterator();it.hasNext();){
			Task tk = it.next();
			projectname=tk.getprojectname();
			
		}
		  return projects;
	}
	public void writeProjectInFile(){
		//当有addTask()等操作后调用
		//把projects写入文件
	}
	public void readProjectFromFile(){
		//不联网时候调用从本地文件读入ArrayList<Project>  放入projects
	}

	//下面是给GUI接口，在一个模式的分类下增删等操作
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
		//外部编辑完任务后点击保存，然后会调用此方法
		//currentProject.edit(x);
	}
	

}
