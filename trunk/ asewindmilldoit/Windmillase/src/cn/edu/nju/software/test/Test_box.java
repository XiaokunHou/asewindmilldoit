package cn.edu.nju.software.test;

import java.util.ArrayList;

import cn.edu.nju.software.box.ContactBox;
import cn.edu.nju.software.box.Group;
import cn.edu.nju.software.box.Project;
import cn.edu.nju.software.box.ProjectBox;
import cn.edu.nju.software.box.Scene;
import cn.edu.nju.software.box.SceneBox;
import cn.edu.nju.software.box.Time;
import cn.edu.nju.software.box.TimeBox;
import cn.edu.nju.software.database.Contact;
import cn.edu.nju.software.database.Task;

public class Test_box {

	/**
	 * 用于测试box包
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ProjectBox pb = new ProjectBox();
		//SceneBox sb = new SceneBox();
		TimeBox sb = new TimeBox();
		/*
		ContactBox cb = new ContactBox();
		Contact ct1 = new Contact();
		Contact ct2 = new Contact();
		Contact ct3 = new Contact();
		
		ct1.setid(0);
		ct1.setgroupname("mygroup");
		ct1.setcontactname("秋水飞鹰");
		ct1.setadder_username("cqiang");
		ct1.setlocal_username("cyun");
		
		ct2.setid(1);
		ct2.setgroupname("group two");
		ct2.setcontactname("a sir");
		ct2.setadder_username("henzhenjie");
		ct2.setlocal_username("cyun");
		
		ct3.setid(2);
		ct3.setgroupname("group two");
		ct3.setcontactname("清风浪");
		ct3.setadder_username("cyun");
		ct3.setlocal_username("henzhenjie");
		
		cb.allcontact = new ArrayList<Contact>();
		cb.allcontact.add(ct1);
		cb.allcontact.add(ct2);
		cb.allcontact.add(ct3);
		
		cb.spiltIntoGroup();
		ArrayList<Group> groups =cb.getAllGroup();
		
		for(int i=0;i<groups.size();i++){
			ArrayList<Contact> x=groups.get(i).getContactInGroup();
			System.out.println(groups.get(i).getGroupName());
			System.out.println("x的大小："+x.size());
			for(int i1=0;i1<x.size();i1++){
				System.out.println(x.get(i1).getgroupname());
				System.out.println(x.get(i1).getcontactname());
				System.out.println(x.get(i1).getlocal_username());
				System.out.println("--------------");
			}
		}
		*/
		
		Task tk1 = new Task();
		Task tk2 = new Task();
		Task tk3 = new Task();
		Task tk4 = new Task();
		Task tk5 = new Task();
		//Task tk6 = new Task();
		
		tk1.setid(0);
		tk1.settaskname("ss");
		tk1.settaskinformation(null);
		tk1.settasklabel(null);
		tk1.settaskstarttime("20100805");
		tk1.settaskendtime(null);
		tk1.setisdoing("true");
		tk1.setisdone(true);
		tk1.setisdelete(false);
		tk1.setusername("cyun");
		tk1.setprojectname("project one");
		tk1.setscenename("scene one");
		tk1.settaskpriority(null);
		tk1.settaskshared(null);
		
		tk1.gettaskname();
		tk1.gettaskinformation();
		tk1.gettasklabel();
		tk1.gettaskstarttime();
		tk1.gettaskendtime();
		tk1.getisdoing();
		tk1.getisdone();
		tk1.getisdelete();
		tk1.getusername();
		tk1.getprojectname();
		tk1.getscenename();
		tk1.gettaskpriority();
		tk1.gettaskshared();
		
		tk2.setid(1);
		tk2.settaskname("aa");
		tk2.settaskinformation(null);
		tk2.settasklabel(null);
		tk2.settaskstarttime("20100805");
		tk2.settaskendtime(null);
		tk2.setisdoing("true");
		tk2.setisdone(false);
		tk2.setisdelete(true);
		tk2.setusername("cqian");
		tk2.setprojectname("project two");
		tk2.setscenename("scene two");
		tk2.settaskpriority(null);
		tk2.settaskshared(null);
		
		tk2.gettaskname();
		tk2.gettaskinformation();
		tk2.gettasklabel();
		tk2.gettaskstarttime();
		tk2.gettaskendtime();
		tk2.getisdoing();
		tk2.getisdone();
		tk2.getisdelete();
		tk2.getusername();
		tk2.getprojectname();
		tk2.getscenename();
		tk2.gettaskpriority();
		tk2.gettaskshared();
		
		
		tk3.setid(2);
		tk3.settaskname("se");
		tk3.settaskinformation(null);
		tk3.settasklabel(null);
		tk3.settaskstarttime("20100806");
		tk3.settaskendtime(null);
		tk3.setisdoing("true");
		tk3.setisdone(false);
		tk3.setisdelete(true);
		tk3.setusername("cyun");
		tk3.setprojectname("project one");
		tk3.setscenename("scene two");
		tk3.settaskpriority(null);
		tk3.settaskshared(null);
		
		tk3.gettaskname();
		tk3.gettaskinformation();
		tk3.gettasklabel();
		tk3.gettaskstarttime();
		tk3.gettaskendtime();
		tk3.getisdoing();
		tk3.getisdone();
		tk3.getisdelete();
		tk3.getusername();
		tk3.getprojectname();
		tk3.getscenename();
		tk3.gettaskpriority();
		tk3.gettaskshared();
		
		tk4.setid(3);
		tk4.settaskname("tingke");
		tk4.settaskinformation(null);
		tk4.settasklabel(null);
		tk4.settaskstarttime("20100806");
		tk4.settaskendtime(null);
		tk4.setisdoing("false");
		tk4.setisdone(false);
		tk4.setisdelete(true);
		tk4.setusername("cqian");
		tk4.setprojectname("project two");
		tk4.setscenename("scene one");
		tk4.settaskpriority(null);
		tk4.settaskshared(null);
		
		tk4.gettaskname();
		tk4.gettaskinformation();
		tk4.gettasklabel();
		tk4.gettaskstarttime();
		tk4.gettaskendtime();
		tk4.getisdoing();
		tk4.getisdone();
		tk4.getisdelete();
		tk4.getusername();
		tk4.getprojectname();
		tk4.getscenename();
		tk4.gettaskpriority();
		tk4.gettaskshared();
		
		
		tk5.setid(4);
		tk5.settaskname("fuckyou");
		tk5.settaskinformation(null);
		tk5.settasklabel(null);
		tk5.settaskstarttime("20100821");
		tk5.settaskendtime(null);
		tk5.setisdoing("true");
		tk5.setisdone(false);
		tk5.setisdelete(true);
		tk5.setusername("cyun");
		tk5.setprojectname("project three");
		tk5.setscenename("scene two");
		tk5.settaskpriority(null);
		tk5.settaskshared(null);
		
		tk5.gettaskname();
		tk5.gettaskinformation();
		tk5.gettasklabel();
		tk5.gettaskstarttime();
		tk5.gettaskendtime();
		tk5.getisdoing();
		tk5.getisdone();
		tk5.getisdelete();
		tk5.getusername();
		tk5.getprojectname();
		tk5.getscenename();
		tk5.gettaskpriority();
		tk5.gettaskshared();
		
		//pb.alltask = new ArrayList<Task>();
		sb.alltask = new ArrayList<Task>();
		
		sb.alltask.add(tk1);
		sb.alltask.add(tk2);
		sb.alltask.add(tk3);
		sb.alltask.add(tk4);
		sb.alltask.add(tk5);
		
		sb.spiltIntoTime();
		//ArrayList<Project> projects=pb.getAllProject();
		ArrayList<Time> times= sb.getAllTime();
		
		for(int i=0;i<times.size();i++){
			ArrayList<Task> x=times.get(i).getTaskIntime();
			System.out.println(times.get(i).getTimeName());
			System.out.println("x的大小："+x.size());
			for(int i1=0;i1<x.size();i1++){
				System.out.println(x.get(i1).gettaskname());
				System.out.println(x.get(i1).gettaskinformation());
				System.out.println(x.get(i1).getscenename());
				System.out.println("--------------");
			}
		}
		//System.out.print(b)
	
		
	}

}
