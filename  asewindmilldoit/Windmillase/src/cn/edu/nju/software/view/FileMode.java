package cn.edu.nju.software.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class FileMode extends BaseMode {

	private JButton week;
	private JButton month;

	private JButton delete;
	
	private JButton back;		//前一周或前一月
	private JButton forward;	//后一周或后一月
	
	private static JButton period;		//显示日期的按钮
	private String dateOfPeriod;//显示日期按钮上的完整字符串
	private String header;		//日期按钮中显示的字符串
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");	//日期显示格式
	private Calendar calendar = new GregorianCalendar();				//获得Calendar实例以取得今天日期
 
	private JTable taskTable; 			// 显示任务的JTable
	private MyTableModel tableModel; 	// 用于创建JTable的模型
	private ArrayList<JCheckBox> jcbs; 	// 任务列表第一列是JCheckBox
	
	private CalendarFrame calendarFrame = new CalendarFrame(); // 日历框架
	
	private TimerTask timerTask;	//$
	private Timer timer;			//$
	private int i;
	
	private int selectedTable;

	public FileMode() {
		
		super();
		
		choi.select(4);
		
		ImageIcon weekIcon = new ImageIcon("src/res/weekfile.png");
		week = new JButton(weekIcon);
		week.setBounds(5, 45, 130, 30);
		leftPanel.add(week);

		ImageIcon monthIcon = new ImageIcon("src/res/monthfile.png");
		month = new JButton(monthIcon);
		month.setBounds(5, 85, 130, 30);
		leftPanel.add(month);

		// 对任务进行处理（ 删除 ）的按钮，基本每个模式下都有这四个按钮
		ImageIcon deleteIcon = new ImageIcon("src/res/delete.png");
		delete = new JButton(deleteIcon);
		delete.setBounds(208, 114, 25, 25);
		this.add(delete);
		
		ImageIcon backIcon = new ImageIcon("src/res/back.png");
		back = new JButton(backIcon);
		back.setBounds(121, 5, 25, 25);
		taskPanel.add(back);
		
		ImageIcon forwardIcon = new ImageIcon("src/res/forward.png");
		forward = new JButton(forwardIcon);
		forward.setBounds(426, 5, 25, 25);
		taskPanel.add(forward);
		
		//取得日期按钮当前显示的字符串
		Calendar cloneCalendar = (Calendar) calendar.clone();	//当前Calendar的一个克隆体
		cloneCalendar.add(Calendar.DATE, -3);					//日期减去三天
		header = sdf.format(cloneCalendar.getTime());
		dateOfPeriod = header + "—";
		cloneCalendar.add(Calendar.DATE, 6);
		header = sdf.format(cloneCalendar.getTime());
		dateOfPeriod += header;
		
		//绘制日期按钮
		period = new JButton();
		period.setBackground(Color.gray);
		period.setBounds(171, 5, 240, 25);
		period.setText(dateOfPeriod);
		taskPanel.add(period);
		
		// 对任务处理按钮添加事件监听
	    delete.addActionListener(this);
	    
	    //对日期按钮添加事件监听
	    period.addActionListener(this);

		showTask();
	}

	public void showTask() {

		// 任务列表表头，列首。分别为完成状态（默认都为false（未完成））、任务名、提醒、优先级(无、低、中、高）
		String[] columnNames = { "TaskName", "Warning", "Priority" };

		ImageIcon wic = new ImageIcon("src/res/remind.png");
		ImageIcon nwic = new ImageIcon("src/res/noRemind.png");

		ImageIcon pic1 = new ImageIcon("src/res/lowPriority.png");
		ImageIcon pic2 = new ImageIcon("src/res/mediumPriority.png");
		ImageIcon pic3 = new ImageIcon("src/res/highPriority.png");

		Object[][] rowData = { { "吃饭", nwic, pic1 }, { "睡觉", wic, pic2 },
				{ "锻炼", nwic, pic3 }, { "打字", wic, pic1 },
				{ "唱K", nwic, pic2 }, { "跳舞", wic, pic2 }, { "跑步", wic, pic2 },
				{ "游泳", nwic, pic2 }, { "竞技", wic, pic2 } };

		tableModel = new MyTableModel(rowData, columnNames);
		taskTable = new JTable(tableModel) { // 匿名内部类的方法重载JTable的prepareRenderer方法设置透明
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				// We want renderer component to be transparent so background
				// image is visible
				if (c instanceof JComponent)
					((JComponent) c).setOpaque(false);
				return c;
			}
		};

		// 设置任务表中第一列的单元格为JCheckBox
		jcbs = new ArrayList<JCheckBox>();
		for (int i = 0; i < rowData.length; i++) {
			JCheckBox jcb = new JCheckBox();
			jcb.setBounds(15, 30 * i+35, 142, 30);
			jcb.setOpaque(false);
			jcbs.add(jcb);
			taskPanel.add(jcbs.get(i));
		}

		taskTable.setBounds(157, 35, 426, 300);
		taskTable.setOpaque(false);
		taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 只能选择一行
		taskTable.setColumnSelectionAllowed(false);
		taskTable.setShowHorizontalLines(false);
		taskTable.setShowVerticalLines(false);
		taskTable.setRowHeight(30);
		taskPanel.add(taskTable, BorderLayout.WEST);
		taskTable.addMouseListener(new TableMouseListener());

		for (int i = 0; i < jcbs.size(); i++) {
			jcbs.get(i).addItemListener(this);
		}

	}

	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		 
		if(e.getSource()==period){
			CalendarFlag.setFlag(0);
			calendarFrame = new CalendarFrame();
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			calendarFrame.setLocation((screen.width - 898) / 2 + 378,
					(screen.height - 617) / 2 + 207);
			calendarFrame.setVisible(true);
		}
		
	}

	
	public void itemStateChanged(ItemEvent e) {
		super.itemStateChanged(e);
		choi.select(4);
		Object obj = e.getItem();
		for (i = 0; i < jcbs.size(); i++) {
			if (obj.equals(jcbs.get(i))) {
				if (jcbs.get(i).isSelected()) {
					
					taskPanel.remove(jcbs.get(i));	//$
					jcbs.get(i).setSelected(true);	//$
					taskPanel.add(jcbs.get(i));		//$
					taskPanel.repaint();			//$
					
					timer = new Timer();			//$
					timerTask = new MyTimerTask();	//$
					timer.schedule(timerTask, 500);	//$
					
					break;
				}
			}
		}
	}
	
	public class TableMouseListener extends MouseAdapter{
		JLabel label = new JLabel("拖拽");
		
		public void mouseClicked(MouseEvent e) {
			int clickTime = e.getClickCount();
			if(clickTime==2){
				selectedTable = taskTable.getSelectedRow();
				new NewOreditpanel();
			}
		}

		//鼠标被按下时调用的Control获得该联系分组的联系人情况。
		public void mousePressed(MouseEvent e){
			selectedTable = taskTable.getSelectedRow();
		}
	}
	
	class MyTimerTask extends TimerTask{	//$

		@Override
		public void run() {
			
			tableModel.removeRow(i);
			for (int j = 0; j < jcbs.size(); j++) {
				taskPanel.remove(jcbs.get(j));
			}
			jcbs.remove(i);
			for (int j = 0; j < jcbs.size(); j++) {
				jcbs.get(j).setBounds(15, 30 * j+35, 142, 30);
				taskPanel.add(jcbs.get(j));
			}
			taskPanel.repaint();
		}
		
	}
	
	public static JButton getPeriod(){
		
		return period;
		
	}

}




