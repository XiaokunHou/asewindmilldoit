package cn.edu.nju.software.box;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.nju.software.control.LocalDataControl;
import cn.edu.nju.software.database.Contact;
import cn.edu.nju.software.database.Task;

public class ContactBox extends CollectBox{
  
	ArrayList<Contact> allcontact;
	LocalDataControl dc;
	ArrayList<String> Lun = new ArrayList<String>(); //�õ����û������е���ϵ������
	ArrayList<Group> groups = new ArrayList<Group>();
	
	Group currentGroup;
	
	public void setContactList(LocalDataControl x){
		dc=x;
		allcontact=x.getContact();
	}
	
	public void setCurrentGroup(Group x){
		currentGroup=x;
	}
	
	public void spiltIntoGroup(){
		for(Iterator<Contact> it = allcontact.iterator();it.hasNext();){
			Contact tk = it.next();
			String adder_username=tk.getadder_username();
			while(!Lun.contains(adder_username)){
				//pn.add(j, projectname);
				//j++;
				Lun.add(adder_username);
			}
		}
		for(int i=0;i<Lun.size();i++){
			Group g = new Group();
			//m.setProjectName(Lun.get(i));
			g.setContactName(Lun.get(i));
			for(Iterator<Contact> it = allcontact.iterator();it.hasNext();){
				Contact tk = it.next();
				if(tk.getadder_username().equals(g.getContactName())){
					g.addContactInGroup(tk);
				}
			}
			groups.add(g);
		}
		writeGroupInFile(); //д���ļ�
	}
	
	public ArrayList<Group> getAllGroup(){
		return groups;
	}
	
	public void writeGroupInFile(){
		//����addTask()�Ȳ��������
		//��projectsд���ļ�
	try{	
		File file=new File("LocalData/groups.txt");
		FileOutputStream output=new FileOutputStream(file);
		ObjectOutputStream  x=new ObjectOutputStream(output);
		x.writeObject(groups);
		x.close();
	 }catch(Exception x){
		 System.out.println("д���ļ�����");
	 }
	}
	public void readGroupFromFile(){
		//������ʱ����ôӱ����ļ�����ArrayList<Project>  ����projects
		try{	
			File file=new File("LocalData/groups.txt");
			FileInputStream output=new FileInputStream(file);
			ObjectInputStream  x=new ObjectInputStream(output);
			groups=(ArrayList<Group>)x.readObject();
			x.close();
		 }catch(Exception x){
			 System.out.println("�����ļ�����");
		 }
	}
	
	//�����Ǹ�GUI�ӿڣ���һ��ģʽ�ķ�������ɾ�Ȳ���
	public void addContact(Contact x){
		currentGroup.addContactInGroup(x);
		writeGroupInFile();
	}
	
	public void deleteContact(Contact x){
		currentGroup.deleteContactInGroup(x);
		writeGroupInFile();
	}
	
	public void editContact(Contact x){
		currentGroup.editContactInGroup(x);
		writeGroupInFile();
	}
	
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

	@Override
	public void emptyRubbish() {
		// TODO Auto-generated method stub
		
	}

	

}
