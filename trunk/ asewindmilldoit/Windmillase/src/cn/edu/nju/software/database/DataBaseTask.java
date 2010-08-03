package cn.edu.nju.software.database;

import java.sql.ResultSet;

public class DataBaseTask extends DataBase{
    private Task task;
    private User user;
    
    private String getDataBaseTask;
    
    public DataBaseTask(){
    	
    }
    
    public Task getDataBaseTask(String username){
    	return task;
    }
    
	@Override
	public void delete(Object x) {
		// TODO Auto-generated method stub
		task=(Task) x;
		getDataBaseTask="delete from task where taskname = '" + task.gettaskname()+ "'";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseTask)){
				System.out.print("删除成功");
				return;
			}else{
				System.out.print("删除失败");
				return;
			}
		}
	}

	@Override
	public boolean insert(Object x) {
		// TODO Auto-generated method stub
		task=(Task) x;
		getDataBaseTask="insert into task(taskname,username) " +
						"values('" + task.gettaskname()+ "','"+user.getusername()+"')";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseTask)){
				System.out.print("插入成功");
				return true;
			}else{
				System.out.print("插入失败");
		return false;
			}
		}else
			return false;
	}

	@Override
	public ResultSet query(Object x) {
		// TODO Auto-generated method stub
		task=(Task) x;
		getDataBaseTask="select * from task where taskname = '" + task.gettaskname()+ "' and username = '" + user.getusername()+ "'";
		if(DB.connectDB()){
			if(DB.query(getDataBaseTask)){
				return DB.rs;
			}else
				return null;
		}else
		return null;
	}

	@Override
	public void update(Object x) {
		// TODO Auto-generated method stub
		task=(Task) x;
		getDataBaseTask="update task set taskname = '" + task.gettaskname()
		+ "' and taskinfomation= '"+task.gettaskinformation()
		+"' and tasklabel= '"+task.gettasklabel()
		+"' and taskstarttime= '"+task.gettaskstarttime()
		+"' and taskendtime= '"+task.gettaskendtime()
		+"' and isdoing= '"+task.getisdoing()
		+"' and isdone= '"+task.getisdone()
		+"' and isdelete= '"+task.getisdelete()
		+"' and projectname= '"+task.getprojectname()
		+"' and scenename= '"+task.getscenename()
		+"' and taskpriority= '"+task.gettaskpriority()
		+"' and taskshared= '"+task.gettaskshared()
		+"' where username = '" + user.getusername()+ "' and id='"+task.getid()+"'";
		if(DB.connectDB()){
			if(DB.executeSQL(getDataBaseTask)){
				System.out.print("修改成功");
				return;
					}else{
						System.out.print("修改失败");
							return;
		}
}
	}

	@Override
	public void setobject(Object x) {
		// TODO Auto-generated method stub
		task=(Task) x;
	}

}
