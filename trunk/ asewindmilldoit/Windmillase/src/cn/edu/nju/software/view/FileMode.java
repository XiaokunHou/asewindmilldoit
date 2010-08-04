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
	
	private JButton back;		//ǰһ�ܻ�ǰһ��
	private JButton forward;	//��һ�ܻ��һ��
	
	private static JButton period;		//��ʾ���ڵİ�ť
	private String dateOfPeriod;//��ʾ���ڰ�ť�ϵ������ַ���
	private String header;		//���ڰ�ť����ʾ���ַ���
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");	//������ʾ��ʽ
	private Calendar calendar = new GregorianCalendar();				//���Calendarʵ����ȡ�ý�������
 
	private JTable taskTable; 			// ��ʾ�����JTable
	private MyTableModel tableModel; 	// ���ڴ���JTable��ģ��
	private ArrayList<JCheckBox> jcbs; 	// �����б��һ����JCheckBox
	
	private CalendarFrame calendarFrame = new CalendarFrame(); // �������
	
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

		// ��������д��� ɾ�� ���İ�ť������ÿ��ģʽ�¶������ĸ���ť
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
		
		//ȡ�����ڰ�ť��ǰ��ʾ���ַ���
		Calendar cloneCalendar = (Calendar) calendar.clone();	//��ǰCalendar��һ����¡��
		cloneCalendar.add(Calendar.DATE, -3);					//���ڼ�ȥ����
		header = sdf.format(cloneCalendar.getTime());
		dateOfPeriod = header + "��";
		cloneCalendar.add(Calendar.DATE, 6);
		header = sdf.format(cloneCalendar.getTime());
		dateOfPeriod += header;
		
		//�������ڰ�ť
		period = new JButton();
		period.setBackground(Color.gray);
		period.setBounds(171, 5, 240, 25);
		period.setText(dateOfPeriod);
		taskPanel.add(period);
		
		// ��������ť����¼�����
	    delete.addActionListener(this);
	    
	    //�����ڰ�ť����¼�����
	    period.addActionListener(this);

		showTask();
	}

	public void showTask() {

		// �����б��ͷ�����ס��ֱ�Ϊ���״̬��Ĭ�϶�Ϊfalse��δ��ɣ����������������ѡ����ȼ�(�ޡ��͡��С��ߣ�
		String[] columnNames = { "TaskName", "Warning", "Priority" };

		ImageIcon wic = new ImageIcon("src/res/remind.png");
		ImageIcon nwic = new ImageIcon("src/res/noRemind.png");

		ImageIcon pic1 = new ImageIcon("src/res/lowPriority.png");
		ImageIcon pic2 = new ImageIcon("src/res/mediumPriority.png");
		ImageIcon pic3 = new ImageIcon("src/res/highPriority.png");

		Object[][] rowData = { { "�Է�", nwic, pic1 }, { "˯��", wic, pic2 },
				{ "����", nwic, pic3 }, { "����", wic, pic1 },
				{ "��K", nwic, pic2 }, { "����", wic, pic2 }, { "�ܲ�", wic, pic2 },
				{ "��Ӿ", nwic, pic2 }, { "����", wic, pic2 } };

		tableModel = new MyTableModel(rowData, columnNames);
		taskTable = new JTable(tableModel) { // �����ڲ���ķ�������JTable��prepareRenderer��������͸��
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

		// ����������е�һ�еĵ�Ԫ��ΪJCheckBox
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
		taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ֻ��ѡ��һ��
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
		JLabel label = new JLabel("��ק");
		
		public void mouseClicked(MouseEvent e) {
			int clickTime = e.getClickCount();
			if(clickTime==2){
				selectedTable = taskTable.getSelectedRow();
				new NewOreditpanel();
			}
		}

		//��걻����ʱ���õ�Control��ø���ϵ�������ϵ�������
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




