package cn.edu.nju.software.database;

import java.io.Serializable;

import cn.edu.nju.software.control.Control.Operation;

public class SqlData implements Serializable{
  private Operation op;//����ͻ��˾������ʱ���޸���һ����
	public Operation getOperation(){
    	return op;
    }
	public void setOpration(Operation x){
		op=x;
	}
}
