package cn.edu.nju.software.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class ProjectMode extends BaseMode implements  TreeModelListener,TreeSelectionListener{
	//JPanel main;
	private JButton button1;
	//private JButton button2;
	private JTree tree; 
	DefaultTreeModel treeModel=null;
	String nodeName;
	
	private JButton create;
	private JButton edit;
	private JButton delete;
	private JButton over;
	
	private JButton add;
	private JButton pen;
	private JButton move;
	
	int eventCou=0;
	String mark;
	
	private TimerTask timerTask;	//$
	private Timer timer;			//$
	private int i;
	
	private int selectedTable;
	
	private JTable taskTable; // ��ʾ�����JTable
	private MyTableModel tableModel; // ���ڴ���JTable��ģ��
	private ArrayList<JCheckBox> jcbs; // �����б��һ����JCheckBox
	
	public ProjectMode(){
		
		super();
		
		choi.select(2);
		
		button1=new JButton("��Ŀ�ռ���");
		button1.setBorderPainted(false);
		leftPanel.add(button1);
		
		button1.setBounds(5, 45, 130, 30);
		
		//����
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("������Ŀ");
		treeModel=new DefaultTreeModel(root);
		tree=new JTree(treeModel);
		tree.setRowHeight(20);
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setViewportView(tree);
		leftPanel.add(scrollPane);
		scrollPane.setBounds(5, 85, 130, 320);
		tree.setEditable(true);
		tree.addTreeSelectionListener(this);
		tree.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					TreePath treepath=tree.getSelectionPath();
					TreeNode treenode=(TreeNode)treepath.getLastPathComponent();
					nodeName=treenode.toString();
				}catch(NullPointerException ne){}
			}
		});
		
		// ��������д����½����༭��ɾ������ɣ��İ�ť������ÿ��ģʽ�¶������ĸ���ť
		ImageIcon createIcon = new ImageIcon("src/res/new.png");
		create = new JButton(createIcon);
		create.setBounds(208, 114, 25, 25);
		this.add(create);
		ImageIcon editIcon = new ImageIcon("src/res/edit.png");
		edit = new JButton(editIcon);
		edit.setBounds(240, 114, 25, 25);
		this.add(edit);
		ImageIcon deleteIcon = new ImageIcon("src/res/delete.png");
		delete = new JButton(deleteIcon);
		delete.setBounds(272, 114, 25, 25);
		this.add(delete);
		ImageIcon overIcon = new ImageIcon("src/res/over.png");
		over = new JButton(overIcon);
		over.setBounds(304, 114, 25, 25);
		this.add(over);

		// ��������ť����¼�����
		create.addActionListener(this);
		over.addActionListener(this);
	    
	    add=new JButton("+");
	    add.setBounds(2, 415, 45, 25);
	    leftPanel.add(add);
	    pen=new JButton("/");
	    pen.setBounds(45, 415, 45, 25);
	    leftPanel.add(pen);
	    move=new JButton("-");
	    move.setBounds(88, 415, 45, 25);
	    leftPanel.add(move);
	    add.addActionListener(this);
	    pen.addActionListener(this);
	    move.addActionListener(this);
	    
	    showTask();
	    
	    DefaultTreeCellRenderer renderer=(DefaultTreeCellRenderer)tree.getCellRenderer();
	    renderer.setLeafIcon(new ImageIcon("src/res/node.png"));
	    renderer.setOpenIcon(new ImageIcon("src/res/node.png"));
	    renderer.setClosedIcon(new ImageIcon("src/res/node.png"));
	    renderer.setBorderSelectionColor(Color.RED);
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
	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
	//	super.actionPerformed(e);
		if (e.getSource() == over) {
			// System.out.println("Now removed selected row is: " +
			// taskTable.getSelectedRow());
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
				// System.out.println("CheckBox is left " + jcbs.size());
			}
		}
		else if(e.getSource()==create){
			new NewOreditpanel();
		}
		else if(e.getSource()==add){//�����½ڵ�
			TreePath parentPath=tree.getSelectionPath();
			if(parentPath!=null){
				DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new project");
				newNode.setAllowsChildren(true);
				DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());
				treeModel.insertNodeInto(newNode, parentNode, parentNode.getChildCount());
				tree.scrollPathToVisible(new TreePath(newNode.getPath()));
			}
		}
		else if(e.getSource()==pen){
			TreePath parentPath=tree.getSelectionPath();
			if(parentPath!=null){
				//DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new project");
				//newNode.setAllowsChildren(true);
				DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());
			//	((java.awt.Component) parentNode).enableEvents();
				//parentNode.getl
				}
		}
		else if(e.getSource()==move){
			TreePath treepath=tree.getSelectionPath();
			if(treepath!=null){
				DefaultMutableTreeNode selectionNode=(DefaultMutableTreeNode)treepath.getLastPathComponent();
				TreeNode parent=(TreeNode)selectionNode.getParent();
				if(parent!=null&&selectionNode!=null){
					treeModel.removeNodeFromParent(selectionNode);
				}
			}
		}
		else{
			DefaultMutableTreeNode rootNode=(DefaultMutableTreeNode)treeModel.getRoot();
			rootNode.removeAllChildren();
			treeModel.reload();
		}
	}
	
	
	public void itemStateChanged(ItemEvent e) {
		super.itemStateChanged(e);
		choi.select(2);
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
	
	@Override
	public void treeNodesChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
	}

}


