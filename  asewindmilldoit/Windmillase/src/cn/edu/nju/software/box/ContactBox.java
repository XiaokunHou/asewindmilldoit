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
import cn.edu.nju.software.database.SqlData;

public class ContactBox extends CollectBox{
  
	ArrayList<Contact> allcontact;
	LocalDataControl dc;
	ArrayList<String> Lun = new ArrayList<String>(); //�õ����û������е���ϵ������
	ArrayList<Group> groups = new ArrayList<Group>();
	
	Group currentGroup;
	
	public void setCurrentGroup(Group x){
		//currentGroup=x;
		for(int i=0;i<groups.size();i++){
			if(groups.get(i).getContactName().equals(x.getContactName())){
				currentGroup = groups.get(i);
			}
		}
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
	@Override
	public void add(SqlData x) {
		// TODO Auto-generated method stub
		Contact con = (Contact)x;
		currentGroup.addContactInGroup(con);
		writeGroupInFile();
	}

	@Override
	public void complete(SqlData x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SqlData x) {
		// TODO Auto-generated method stub
		Contact con = (Contact)x;
		currentGroup.deleteContactInGroup(con);
		writeGroupInFile();
	}

	@Override
	public void edit(SqlData x) {
		// TODO Auto-generated method stub
		Contact con = (Contact)x;
		currentGroup.editContactInGroup(con);
		writeGroupInFile();
	}

	@Override
	public void setLocalDataControl(LocalDataControl x) {
		// TODO Auto-generated method stub
		dc=x;
		allcontact=x.getContact();
	}
}
