package cn.edu.nju.software.box;

import java.util.ArrayList;

import cn.edu.nju.software.database.Task;

public class SceneBox extends CollectBox{
	 ArrayList<Task> all;
	 ArrayList<Scene> scenes=new ArrayList<Scene>();//����BaseMode���LocalDataControlȡ��ArrayList<Task>
	   //Ȼ����ñ����spiltIntoScene()������ֳɺܶ�Scene���ӽ�����
	 Scene currentScene;//�ⲿGUI���һ���龰ģʽ��һ������󣬻��޸���һ���ԣ������칫��
	   
		public void setTaskList(ArrayList<Task> x){
			//SceneMode���ã�����LocalDataControl��Task����
			//����������ʱ�����
			all=x;
		}
		public void setCurrentScene(Scene x){
			currentScene=x;
		}
		public void spiltIntoScene(){
			//����ʱ�����
			//���ñ����spiltIntoTime()������Task�����ֳɺܶ�Scene���ӽ�����scenes
			//���writeSceneInFile();
		}
		public ArrayList<Scene> getAllScene(){
			//GUI����ͨ���˷����õ�����Scene
			  return scenes;
		}
		public void writeSceneInFile(){
			//����addTask()�Ȳ��������
			//��scenesд���ļ�
		}
		public void readSceneFromFile(){
			//������ʱ����ôӱ����ļ�����ArrayList<Scene> ����scenes
		}
		//�����Ǹ�GUI�ӿڣ���һ��ģʽ�ķ�������ɾ�Ȳ���
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
