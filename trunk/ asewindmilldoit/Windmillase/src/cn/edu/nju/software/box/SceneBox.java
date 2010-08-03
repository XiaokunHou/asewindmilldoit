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
	 ArrayList<String> sn = new ArrayList<String>(); //�õ��û��������龰����
	 ArrayList<Scene> scenes=new ArrayList<Scene>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
	   //Ȼ����ñ����spiltIntoScene()������ֳɺܶ�Scene���ӽ�����
	 Scene currentScene;//�ⲿGUI���һ���龰ģʽ��һ������󣬻��޸���һ���ԣ������칫��
	   
		public void setTaskList(LocalDataControl x){
			//SceneMode���ã�����LocalDataControl��Task����
			//����������ʱ�����
			dc=x;
			alltask=x.getTask();
		}
		public void setCurrentScene(Scene x){
			currentScene=x;
		}
		public void spiltIntoScene(){
			//����ʱ�����
			//���ñ����spiltIntoTime()������Task�����ֳɺܶ�Scene���ӽ�����scenes
			//���writeSceneInFile();
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
			writeSceneInFile(); //д���ļ�
		}
		public ArrayList<Scene> getAllScene(){
			//GUI����ͨ���˷����õ�����Scene
			  return scenes;
		}
		public void writeSceneInFile(){
			//����addTask()�Ȳ��������
			//��scenesд���ļ�
			try{	
				File file=new File("LocalData/scenes.txt");
				FileOutputStream output=new FileOutputStream(file);
				ObjectOutputStream  x=new ObjectOutputStream(output);
				x.writeObject(scenes);
				x.close();
			 }catch(Exception x){
				 System.out.println("д���ļ�����");
			 }
			}
		
		public void readSceneFromFile(){
			//������ʱ����ôӱ����ļ�����ArrayList<Scene> ����scenes
			try{	
				File file=new File("LocalData/scenes.txt");
				FileInputStream output=new FileInputStream(file);
				ObjectInputStream  x=new ObjectInputStream(output);
				scenes=(ArrayList<Scene>)x.readObject();
				x.close();
			 }catch(Exception x){
				 System.out.println("�����ļ�����");
			 }
		}
		//�����Ǹ�GUI�ӿڣ���һ��ģʽ�ķ�������ɾ�Ȳ���
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
