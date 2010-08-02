package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class SceneBox extends CollectBox{
	 ArrayList<Task> all;
	 ArrayList<Scene> scenes=new ArrayList<Scene>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
	   //然后调用本类的spiltIntoScene()方法拆分成很多Scene，加进这里
	 Scene currentScene;//外部GUI点击一个情景模式下一个分类后，会修改这一属性，如点击办公室
	   
		public void setTaskList(ArrayList<Task> x){
			//SceneMode调用，传入LocalDataControl的Task数组
			//本方法联网时候调用
			all=x;
		}
		public void setCurrentScene(Scene x){
			currentScene=x;
		}
		public void spiltIntoScene(){
			//联网时候调用
			//调用本类的spiltIntoTime()方法将Task链表拆分成很多Scene，加进这里scenes
			//最后writeSceneInFile();
		}
		public ArrayList<Scene> getAllScene(){
			//GUI可以通过此方法得到所有Scene
			  return scenes;
		}
		public void writeSceneInFile(){
			//当有addTask()等操作后调用
			//把scenes写入文件
		}
		public void readSceneFromFile(){
			//不联网时候调用从本地文件读入ArrayList<Scene> 放入scenes
		}
		//下面是给GUI接口，在一个模式的分类下增删等操作
		@Override
		public void addTask(Task x) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void completeTask(Task x) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void deleteTask(Task x) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void editTask(Task x) {
			// TODO Auto-generated method stub
			
		}
	
	
}
