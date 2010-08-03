package cn.edu.nju.software.box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.Task;

public class SceneBox extends CollectBox{
	 ArrayList<Task> alltask;
	 LocalDataControl dc;
	 ArrayList<String> sn = new ArrayList<String>(); //得到用户的所有情景名称
	 ArrayList<Scene> scenes=new ArrayList<Scene>();//根据BaseMode里的LocalDataControl取得ArrayList<Task>
	   //然后调用本类的spiltIntoScene()方法拆分成很多Scene，加进这里
	 Scene currentScene;//外部GUI点击一个情景模式下一个分类后，会修改这一属性，如点击办公室
	   
		public void setTaskList(LocalDataControl x){
			//SceneMode调用，传入LocalDataControl的Task数组
			//本方法联网时候调用
			dc=x;
			alltask=x.getTask();
		}
		public void setCurrentScene(Scene x){
			currentScene=x;
		}
		public void spiltIntoScene(){
			//联网时候调用
			//调用本类的spiltIntoTime()方法将Task链表拆分成很多Scene，加进这里scenes
			//最后writeSceneInFile();
			for(Iterator<Task> it = alltask.iterator();it.hasNext();){
				Task tk = it.next();
				String scenename=tk.getscenename();
				while(!sn.contains(scenename)){
					//pn.add(j, projectname);
					//j++;
					sn.add(scenename);
				}
			}
			for(int i=0;i<sn.size();i++){
				Scene s = new Scene();
				s.setSceneName(sn.get(i));
				for(Iterator<Task> it = alltask.iterator();it.hasNext();){
					Task tk = it.next();
					if(tk.getscenename().equals(s.getSceneName())){
						s.addTaskInScene(tk);
					}
				}
				scenes.add(s);
			}
			writeSceneInFile(); //写入文件
		}
		public ArrayList<Scene> getAllScene(){
			//GUI可以通过此方法得到所有Scene
			  return scenes;
		}
		public void writeSceneInFile(){
			//当有addTask()等操作后调用
			//把scenes写入文件
			try{	
				File file=new File("LocalData/scenes.txt");
				FileOutputStream output=new FileOutputStream(file);
				ObjectOutputStream  x=new ObjectOutputStream(output);
				x.writeObject(scenes);
				x.close();
			 }catch(Exception x){
				 System.out.println("写入文件错误");
			 }
			}
		
		public void readSceneFromFile(){
			//不联网时候调用从本地文件读入ArrayList<Scene> 放入scenes
			try{	
				File file=new File("LocalData/scenes.txt");
				FileInputStream output=new FileInputStream(file);
				ObjectInputStream  x=new ObjectInputStream(output);
				scenes=(ArrayList<Scene>)x.readObject();
				x.close();
			 }catch(Exception x){
				 System.out.println("读入文件错误");
			 }
		}
		//下面是给GUI接口，在一个模式的分类下增删等操作
		@Override
		public void addTask(Task x) {
			// TODO Auto-generated method stub
			currentScene.addTaskInScene(x);
			writeSceneInFile();
		}
		@Override
		public void completeTask(Task x) {
			// TODO Auto-generated method stub
			currentScene.completeTaskInScene(x);
			writeSceneInFile();
		}
		@Override
		public void deleteTask(Task x) {
			// TODO Auto-generated method stub
			currentScene.deleteTaskInScene(x);
			writeSceneInFile();
		}
		@Override
		public void editTask(Task x) {
			// TODO Auto-generated method stub
			currentScene.editTaskInScene(x);
			writeSceneInFile();
		}
		@Override
		public void emptyRubbish() {
			// TODO Auto-generated method stub
			for(int i=0;i<alltask.size();i++){
				if(alltask.get(i).getisdelete()){
					//currentProject.removeTaskFrompro(alltask.get(i));
					alltask.remove(alltask.get(i));
				}
			}
			writeSceneInFile();
		}
	
	
}
