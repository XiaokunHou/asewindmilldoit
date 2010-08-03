package cn.edu.nju.software.database;

import java.io.Serializable;


public class Task extends SqlData implements Serializable{
	private int id;
	private String taskname;
	private String taskinformation;
	private String tasklabel;
	private String taskstarttime;
	private String taskendtime;
	private boolean isdoing;
	private boolean isdone;
	private boolean isdelete;
	private String taskpriority;
	private String taskshared;
	//-------------------------------------
	private String username;
	private String projectname;
	private String scenename;
	//-------------------------------------

	/**
	 * Task类的构造函数
	 */
	public Task(int id,String taskname,String taskinformation,String tasklabel,
				String taskstarttime,String taskendtime,boolean isdoing,boolean isdone,
				boolean isdelete,String taskpriority,String taskshared,String username,
				String projectname,String scenename)
	{
		this.id=id;
		this.isdelete=isdelete;
		this.isdoing=isdone;
		this.isdoing=isdoing;
		this.projectname=projectname;
		this.scenename=scenename;
		this.taskendtime=taskendtime;
		this.taskinformation=taskinformation;
		this.tasklabel=tasklabel;
		this.taskname=taskname;
		this.taskpriority=taskpriority;
		this.taskshared=taskshared;
		this.taskstarttime=taskstarttime;
		this.username=username;
	}
	
	public Task(int id,String taskname,String username,String projectname,String scenename)
{
	this.id=id;
	this.isdoing=isdone;
	this.projectname=projectname;
	this.scenename=scenename;
	this.taskname=taskname;
	this.username=username;
}
	
	public Task() {
		// TODO Auto-generated constructor stub
	}

	public int getid(){
		return id;
	}
	
	public void setid(int id){
		this.id=id;
	}
	
	public String gettaskname(){
		return taskname;
	}
	
	public void settaskname(String taskname){
		this.taskname=taskname;
	}
	
	public String gettaskinformation(){
		return taskinformation;
	}
	
	public void settaskinformation(String taskinformation){
		this.taskinformation=taskinformation;
	}
	
	public String gettasklabel(){
		return tasklabel;
	}
	
	public void settasklabel(String tasklabel){
		this.tasklabel=tasklabel;
	}
	
	public String gettaskstarttime(){
		return taskstarttime;
	}
	
	public void settaskstarttime(String taskstarttime){
		this.taskstarttime=taskstarttime;
	}
	
	public String gettaskendtime(){
		return taskendtime;
	}
	
	public void settaskendtime(String taskendtime){
		this.taskendtime=taskendtime;
	}
	
	public String gettaskpriority(){
		return taskpriority;
	}
	
	public void settaskpriority(String taskpriority){
		this.taskpriority=taskpriority;
	}
	
	public String gettaskshared(){
		return taskshared;
	}
	
	public void settaskshared(String taskshared){
		this.taskshared=taskshared;
	}
	
	public String getusername(){
		return username;
	}
	
	public void setusername(String username){
		this.username=username;
	}
	
	public String getprojectname(){
		return projectname;
	}
	
	public void setprojectname(String projectname){
		this.projectname=projectname;
	}
	
	public String getscenename(){
		return scenename;
	}
	
	public void setscenename(String scenename){
		this.scenename=scenename;
	}
	
	public boolean getisdoing(){
		return isdoing;
	}
	
	public void setisdoing(boolean isdoing){
		this.isdoing=isdoing;
	}
	
	public boolean getisdone(){
		return isdone;
	}
	
	public void setisdone(boolean isdone){
		this.isdone=isdone;
	}
	
	public boolean getisdelete(){
		return isdelete;
	}
	
	public void setisdelete(boolean isdelete){
		this.isdelete=isdelete;
	}
}
