package cn.edu.nju.software.test;

import java.util.ArrayList;

import cn.edu.nju.software.box.Project;
import cn.edu.nju.software.box.ProjectBox;
import cn.edu.nju.software.database.Task;

public class Test_box {

	/**
	 * ÓÃÓÚ²âÊÔbox°ü
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProjectBox pb = new ProjectBox();
		Task tk1 = new Task();
		Task tk2 = new Task();
		tk1.setid(0);
		tk1.settaskname("ss");
		tk1.settaskinformation(null);
		tk1.settasklabel(null);
		tk1.settaskstarttime(null);
		tk1.settaskendtime(null);
		tk1.setisdoing(null);
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
		tk2.settaskstarttime(null);
		tk2.settaskendtime(null);
		tk2.setisdoing(null);
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
		
		pb.alltask = new ArrayList<Task>();
		
		pb.alltask.add(tk1);
		pb.alltask.add(tk2);
		
		pb.spiltIntoProject();
		ArrayList<Project> projects=pb.getAllProject();
		for(int i=0;i<projects.size();i++){
			System.out.println(projects.get(i).getProjectName());
		}
		//System.out.print(b)
		
	}

}
