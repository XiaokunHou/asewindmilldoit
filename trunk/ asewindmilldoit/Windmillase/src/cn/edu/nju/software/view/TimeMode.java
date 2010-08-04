package  cn.edu.nju.software.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

public class TimeMode extends BaseMode {

	private JButton collectBoxButton;
	private JButton dotodayButton;
	private JButton dotomorButton;
	private JButton schedButton;
	private JButton selectdayButton;
	private JButton waitButton;
	private JButton hasdoneButton;
	private JButton dumpButton;

	private JButton create;
	private JButton edit;
	private JButton delete;
	private JButton over;

	private JTable taskTable; // ��ʾ�����JTable
	private MyTableModel tableModel; // ���ڴ���JTable��ģ��
	private ArrayList<JCheckBox> jcbs; // �����б��һ����JCheckBox
	
	private TimerTask timerTask;	//$
	private Timer timer;			//$
	
	private int i;
	private int selectedTable;

	public TimeMode() {

		super();
		
		choi.select(0);

		ImageIcon collectBoxIcon = new ImageIcon("src/res/collectBox.png");
		collectBoxButton = new JButton(collectBoxIcon);
		collectBoxButton.setBounds(5, 45, 130, 30);
		leftPanel.add(collectBoxButton);

		ImageIcon todoTodayIcon = new ImageIcon("src/res/todoToday.png");
		dotodayButton = new JButton(todoTodayIcon);
		dotodayButton.setBounds(5, 85, 130, 30);
		leftPanel.add(dotodayButton);

		ImageIcon todoTommorIcon = new ImageIcon("src/res/todoTommor.png");
		dotomorButton = new JButton(todoTommorIcon);
		dotomorButton.setBounds(5, 125, 130, 30);
		leftPanel.add(dotomorButton);

		ImageIcon scheduleIcon = new ImageIcon("src/res/schedule.png");
		schedButton = new JButton(scheduleIcon);
		schedButton.setBounds(5, 165, 130, 30);
		leftPanel.add(schedButton);

		ImageIcon selectDayIcon = new ImageIcon("src/res/selectDay.png");
		selectdayButton = new JButton(selectDayIcon);
		selectdayButton.setBounds(5, 205, 130, 30);
		leftPanel.add(selectdayButton);

		ImageIcon waitIcon = new ImageIcon("src/res/wait.png");
		waitButton = new JButton(waitIcon);
		waitButton.setBounds(5, 245, 130, 30);
		leftPanel.add(waitButton);

		ImageIcon hasCompletedIcon = new ImageIcon("src/res/hasCompleted.png");
		hasdoneButton = new JButton(hasCompletedIcon);
		hasdoneButton.setBounds(5, 325, 80, 30);
		leftPanel.add(hasdoneButton);

		ImageIcon dumpIcon = new ImageIcon("src/res/dump.png");
		dumpButton = new JButton(dumpIcon);
		dumpButton.setBounds(5, 365, 80, 30);
		leftPanel.add(dumpButton);

		// ��������д����½����༭��ɾ������ɣ��İ�ť������ÿ��ģʽ�¶������ĸ���ť
		ImageIcon createIcon = new ImageIcon("src/res/new.png");
		create = new JButton(createIcon);
		create.setBounds(208, 114, 25, 25);
		this.add(create);
		create.setToolTipText("�½�");
		ImageIcon editIcon = new ImageIcon("src/res/edit.png");
		edit = new JButton(editIcon);
		edit.setBounds(240, 114, 25, 25);
		edit.setToolTipText("�༭");
		this.add(edit);
		ImageIcon deleteIcon = new ImageIcon("src/res/delete.png");
		delete = new JButton(deleteIcon);
		delete.setBounds(272, 114, 25, 25);
		delete.setToolTipText("ɾ��");
		this.add(delete);
		ImageIcon overIcon = new ImageIcon("src/res/over.png");
		over = new JButton(overIcon);
		over.setBounds(304, 114, 25, 25);
		over.setToolTipText("���");
		this.add(over);

		// ��������ť����¼�����
		over.addActionListener(this);
		create.addActionListener(this);
	    edit.addActionListener(this);
	    delete.addActionListener(this);
	    
	    //���������һϵ�а�ťʵ�ּ���
	    collectBoxButton.addActionListener(this);
	    dotodayButton.addActionListener(this);
	    dotomorButton.addActionListener(this);
	    schedButton.addActionListener(this);
	    selectdayButton.addActionListener(this);
	    waitButton.addActionListener(this);
	    hasdoneButton.addActionListener(this);
	    dumpButton.addActionListener(this);

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
			jcb.setBounds(15, 30 * i, 142, 30);
			jcb.setOpaque(false);
			jcbs.add(jcb);
			taskPanel.add(jcbs.get(i));
		}

		taskTable.setBounds(157, 0, 426, 300);
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
		if (e.getSource() == over) {
			int temp = taskTable.getSelectedRow();
			if (taskTable.getSelectedRow() >= 0) {
				tableModel.removeRow(taskTable.getSelectedRow());
				for (int i = 0; i < jcbs.size(); i++) {
					taskPanel.remove(jcbs.get(i));
				}
				jcbs.remove(temp);
				for (int i = 0; i < jcbs.size(); i++) {
					jcbs.get(i).setBounds(15, 30 * i, 142, 30);
					taskPanel.add(jcbs.get(i));
				}
				taskPanel.repaint();
			}
		}
		else if(e.getSource()==create || e.getSource()==edit){
			new NewOreditpanel();
		}
		else if(e.getSource()==collectBoxButton){
			
		}
		
	}

	public void itemStateChanged(ItemEvent e) {
		super.itemStateChanged(e);
		choi.select(0);
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
				jcbs.get(j).setBounds(15, 30 * j, 142, 30);
				taskPanel.add(jcbs.get(j));
			}
			taskPanel.repaint();
		}
		
	}
	

}




