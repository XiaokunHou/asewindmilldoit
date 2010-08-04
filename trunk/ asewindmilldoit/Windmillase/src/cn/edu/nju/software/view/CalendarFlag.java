package cn.edu.nju.software.view;

/*
 * 该类主要负责和FileMode类、其他模式下的任务编辑面板以及日历面板交互
 * 其主要功能是标记当前正在与日历面板交互的是哪个模块
 * 其只包含一个静态属性和两个静态方法，调用时直接以类名调用即可（免去了初始化）
 */

public class CalendarFlag {
	
	/*日历标记量，flag为0时，对应的是归档模式下的日历，为1时是任务编辑面板的start按钮按下后显示的日历，
	 * 为2时是任务编辑面板的end按钮按下后显示的日历
	*/
	private static int flag = 0;	//静态变量，对所有模式的面板只有一份（即共享一份）
	
	public static void setFlag(int f){
		flag = f;
	}
	
	public static int getFlag(){
		return flag;
	}

}
