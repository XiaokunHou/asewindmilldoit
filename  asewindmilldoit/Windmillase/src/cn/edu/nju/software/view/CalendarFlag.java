package cn.edu.nju.software.view;

/*
 * ������Ҫ�����FileMode�ࡢ����ģʽ�µ�����༭����Լ�������彻��
 * ����Ҫ�����Ǳ�ǵ�ǰ������������彻�������ĸ�ģ��
 * ��ֻ����һ����̬���Ժ�������̬����������ʱֱ�����������ü��ɣ���ȥ�˳�ʼ����
 */

public class CalendarFlag {
	
	/*�����������flagΪ0ʱ����Ӧ���ǹ鵵ģʽ�µ�������Ϊ1ʱ������༭����start��ť���º���ʾ��������
	 * Ϊ2ʱ������༭����end��ť���º���ʾ������
	*/
	private static int flag = 0;	//��̬������������ģʽ�����ֻ��һ�ݣ�������һ�ݣ�
	
	public static void setFlag(int f){
		flag = f;
	}
	
	public static int getFlag(){
		return flag;
	}

}
