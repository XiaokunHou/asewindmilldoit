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
	ArrayList<String> Lun = new ArrayList<String>(); //得到该用户的所有的联系人名称
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
		writeGroupInFile(); //写入文件
	}
	
	public ArrayList<Group> getAllGroup(){
		return groups;
	}
	
	public void writeGroupInFile(){
		//当有addTask()等操作后调用
		//把projects写入文件
	try{	
		File file=new File("LocalData/groups.txt");
		FileOutputStream output=new FileOutputStream(file);
		ObjectOutputStream  x=new ObjectOutputStream(output);
		x.writeObject(groups);
		x.close();
	 }catch(Exception x){
		 System.out.println("写入文件错误");
	 }
	}
	public void readGroupFromFile(){
		//不联网时候调用从本地文件读入ArrayList<Project>  放入projects
		try{	
			File file=new File("LocalData/groups.txt");
			FileInputStream output=new FileInputStream(file);
			ObjectInputStream  x=new ObjectInputStream(output);
			groups=(ArrayList<Group>)x.readObject();
			x.close();
		 }catch(Exception x){
			 System.out.println("读入文件错误");
		 }
	}
	
	//下面是给GUI接口，在一个模式的分类下增删等操作
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
