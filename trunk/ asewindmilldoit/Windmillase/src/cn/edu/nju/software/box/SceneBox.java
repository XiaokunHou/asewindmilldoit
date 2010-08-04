package cn.edu.nju.software.box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.SqlData;
import cn.edu.nju.software.database.Task;

public class SceneBox extends CollectBox{
	 ArrayList<Task> alltask;
	 LocalDataControl dc;
	 ArrayList<String> sn = new ArrayList<String>(); //�õ��û��������龰����
	 ArrayList<Scene> scenes=new ArrayList<Scene>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
	   //Ȼ����ñ����spiltIntoScene()������ֳɺܶ�Scene���ӽ�����
	 Scene currentScene;//�ⲿGUI���һ���龰ģʽ��һ������󣬻��޸���һ���ԣ������칫��
	   
		public void setCurrentScene(Scene x){
			for(int i=0;i<scenes.size();i++){
				if(scenes.get(i).getSceneName().equals(x.getSceneName())){
					currentScene=scenes.get(i);
				}
			}
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
		public void add(SqlData x) {
			// TODO Auto-generated method stub
			Task tk = (Task)x;
			currentScene.addTaskInScene(tk);
			writeSceneInFile();
			
		}
		@Override
		public void complete(SqlData x) {
			// TODO Auto-generated method stub
			Task tk = (Task)x;
			currentScene.completeTaskInScene(tk);
			writeSceneInFile();
		}
		@Override
		public void delete(SqlData x) {
			// TODO Auto-generated method stub
			Task tk = (Task)x;
			currentScene.deleteTaskInScene(tk);
			writeSceneInFile();
		}
		@Override
		public void edit(SqlData x) {
			// TODO Auto-generated method stub
			Task tk = (Task)x;
			currentScene.editTaskInScene(tk);
			writeSceneInFile();
		}
		@Override
		public void setLocalDataControl(LocalDataControl x) {
			// TODO Auto-generated method stub
			//SceneMode���ã�����LocalDataControl��Task����
			//����������ʱ�����
			dc=x;
			alltask=x.getTask();
		}
	
	
}
