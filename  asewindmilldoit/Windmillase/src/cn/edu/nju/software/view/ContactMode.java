package cn.edu.nju.software.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import cn.edu.nju.software.view.SceneMode.MyTimerTask;

public class ContactMode extends BaseMode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageIcon leftPanelIcon = new ImageIcon("src/res/left.png");
	private ImageIcon taskpanelIcon = new ImageIcon("src/res/mail4.jpg");

	private JPanel upPanel;

	private MyJPanel leftPanel1;// ��ϵ�˷������
	private MyJPanel leftPanel2;// ��ϵ�����

	JScrollPane scroll1;// ��ϵ�˷������Ĺ�����
	JScrollPane scroll2;// ��ϵ�����Ĺ�����

	private JPanel downPanel1;// ��ϵ�˷������Ĺ�����
	private JPanel downPanel2;// ��ϵ�����Ĺ�����

	private int choosed1;// ��ϵ�˷���ѡ�е�
	private int choosed2;// ��ϵ����屻ѡ�е����

	private JButton edit;// ���񹤾����༭
	private JButton delete;// ���񹤾���ɾ��
	private JButton over;// ���񹤾������

	private MyButton allContactButton;// ������ϵ�˰�ť

	private JButton add;// ��ϵ�˷��鹤�������
	private JButton pen;// ��ϵ�˷��鹤�����༭
	private JButton move;// ��ϵ�˷��鹤�����Ƴ�

	private JButton add2;// ��ϵ�˹��������
	private JButton pen2;// ��ϵ�˹������༭
	private JButton move2;// ��ϵ�˹������Ƴ�

	private ArrayList<MyButton> contactGroups = new ArrayList<MyButton>();
	private ArrayList<MyTextField> contactGroupsTexts = new ArrayList<MyTextField>();
	private ArrayList<MyButton> contacts = new ArrayList<MyButton>();

	private ContactDialog contactDialog;
	private AddContactDialog addContactDialog;

	private JTable taskTable; // ��ʾ�����JTable
	private MyTableModel tableModel; // ���ڴ���JTable��ģ��
	private ArrayList<JCheckBox> jcbs; // �����б��һ����JCheckBox

	private TimerTask timerTask; // $
	private Timer timer; // $
	private int i;

	private int selectedTable;

	public ContactMode() {
		super();

		choi.select(3);

		leftPanel.setVisible(false);
		taskPanel.setVisible(false);

		contactDialog = new ContactDialog(this);
		contactDialog.setVisible(false);
		addContactDialog = new AddContactDialog(this);
		addContactDialog.setVisible(false);

		upPanel = new JPanel();
		upPanel.setBounds(33, 122, 430, 30);
		upPanel.setOpaque(false);
		upPanel.setLayout(null);
		this.add(upPanel);

		taskPanel = new MyJPanel(taskpanelIcon.getImage());
		taskPanel.setBounds(343, 152, 433, 400);
		taskPanel.setLayout(null);
		this.add(taskPanel);

		scroll1 = new JScrollPane(leftPanel1);
		leftPanel1 = new MyJPanel(leftPanelIcon.getImage());
		leftPanel1.setPreferredSize(new Dimension(155, 2000));

		scroll1.setBounds(33, 152, 155, 400);

		scroll1.setViewportView(leftPanel1);
		scroll1
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll1.setSize(155, 400);
		leftPanel1.setLayout(null);
		this.add(scroll1);

		leftPanel2 = new MyJPanel(leftPanelIcon.getImage());
		scroll2 = new JScrollPane(leftPanel2);
		scroll2.setBounds(188, 152, 155, 400);
		scroll2.setViewportView(leftPanel2);
		scroll2
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll2.setSize(155, 400);

		leftPanel2.setLayout(null);
		leftPanel2.setPreferredSize(new Dimension(155, 2000));
		this.add(scroll2);

		downPanel1 = new JPanel();
		downPanel1.setLayout(new GridLayout(1, 2));
		downPanel1.setBounds(33, 552, 155, 30);
		downPanel1.setBackground(Color.darkGray);
		downPanel1.setOpaque(false);
		this.add(downPanel1);

		downPanel2 = new JPanel();
		downPanel2.setLayout(new GridLayout(1, 2));
		downPanel2.setBounds(188, 552, 155, 30);
		downPanel2.setBackground(Color.darkGray);
		downPanel2.setOpaque(false);
		this.add(downPanel2);

		add = new JButton("+");
		add.setOpaque(false);
		add.addMouseListener(new AddButtonListener1());
		downPanel1.add(add);
		pen = new JButton("/");
		pen.setOpaque(false);
		pen.addMouseListener(new EditButtonListener1());
		pen.setEnabled(false);
		downPanel1.add(pen);
		move = new JButton("-");
		move.setOpaque(false);
		move.setEnabled(false);
		move.addMouseListener(new MoveButtonListener1());
		downPanel1.add(move);

		add2 = new JButton("+");
		add2.setOpaque(false);
		add2.addMouseListener(new AddButtonListener2());
		downPanel2.add(add2);
		pen2 = new JButton("/");
		pen2.setOpaque(false);
		pen2.setEnabled(false);
		pen2.addMouseListener(new EditButtonListener2());
		downPanel2.add(pen2);
		move2 = new JButton("-");
		move2.setOpaque(false);
		move2.setEnabled(false);
		move2.addMouseListener(new MoveButtonListener2());
		downPanel2.add(move2);

		ImageIcon editIcon = new ImageIcon("src/res/edit.png");
		edit = new JButton(editIcon);
		upPanel.add(edit);
		edit.setBounds(155, 0, 30, 30);
		edit.setOpaque(false);
		edit.setEnabled(false);
		edit.addActionListener(new EditListener());
		ImageIcon deleteIcon = new ImageIcon("src/res/delete.png");
		delete = new JButton(deleteIcon);
		upPanel.add(delete);
		delete.setBounds(200, 0, 30, 30);
		delete.setOpaque(false);
		delete.setEnabled(false);
		delete.addActionListener(new DeleteListener());
		ImageIcon overIcon = new ImageIcon("src/res/over.png");
		over = new JButton(overIcon);
		upPanel.add(over);
		over.setBounds(250, 0, 30, 30);
		over.setOpaque(false);
		over.setEnabled(false);
		over.addActionListener(new OverListener());

		choi.setBounds(0, 5, 150, 30);
		upPanel.add(choi);

		allContactButton = new MyButton("������ϵ��");
		allContactButton.addMouseListener(new ContactGroupAdapter(
				allContactButton.getPos()));
		leftPanel1.add(allContactButton);

		getContactGroups();
		getAllContact();

		showTask();

	}

	// ��Control�л����ϵ�˷��������
	// ����ArrayList<MyButton> contactGroups��
	public void getContactGroups() {
		MyButton contactGroup = new MyButton("�·���", 0);
		contactGroups.add(contactGroup);
		ContactGroupAdapter ca = new ContactGroupAdapter(contactGroup.getPos());
		contactGroup.addMouseListener(ca);
		contactGroup.setMyMouseAdapter(ca);
		MyTextField contactGroupText = new MyTextField(0);
		contactGroupsTexts.add(contactGroupText);
		EditKeyAdapte eka = new EditKeyAdapte(contactGroupText.getPos());
		EditTextListener etl = new EditTextListener(contactGroupText.getPos());
		contactGroupText.addKeyListener(eka);
		contactGroupText.setMyKeyListener(eka);
		contactGroupText.addFocusListener(etl);
		contactGroupText.setMyFocusLister(etl);
		leftPanel1.setPreferredSize(new Dimension(155,
				(contactGroups.size() + 1) * 30));
		for (int i = 0; i < contactGroups.size(); i++) {
			leftPanel1.add(contactGroups.get(i));
			contactGroups.get(i).setLocation(0, 30 * (i + 1));
		}
	}

	// ��Control�л��������ϵ�������
	// ����ArrayList<MyButton> contacts��
	public void getAllContact() {
		MyButton contact = new MyButton("����ϵ��", 0);
		contacts.add(contact);
		ContactAdapter ca = new ContactAdapter(0);
		contact.addMouseListener(ca);
		contact.setMyMouseAdapter(ca);
		leftPanel2.setPreferredSize(new Dimension(155, contacts.size() * 30));
		for (int i = 0; i < contacts.size(); i++) {
			leftPanel2.add(contacts.get(i));
			contacts.get(i).setLocation(0, 30 * i);
		}
	}

	// ��ϵ�˷��鰴ť�ļ�������Ҫ����Control�����Ҫ�ķ�������ϵ��
	private class ContactGroupAdapter extends MouseAdapter {
		int pos = 0;

		public ContactGroupAdapter(int i) {
			pos = i;
		}

		// ˫���༭����
		public void mouseClicked(MouseEvent e) {
			int clickTime = e.getClickCount();
			if (clickTime == 2) {
				if (pos >= 0) {
					MyButton mb = contactGroups.get(pos);
					mb.setVisible(false);

					String s = mb.getText();
					MyTextField tf = contactGroupsTexts.get(pos);
					leftPanel1.add(tf);
					tf.setText(s);
					tf.setBounds(0, 30 * (pos + 1), 135, 30);
					tf.setVisible(true);

					tf.requestFocus();
					mb = null;
					tf = null;
				}
			}
		}

		// ��걻����ʱ���õ�Control��ø���ϵ�������ϵ�������
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < contactGroups.size(); i++) {
				contactGroups.get(i).setBorder(new LineBorder(Color.black, 0));
			}
			allContactButton.setBorder(new LineBorder(Color.black, 0));
			if (pos == -1) {
				allContactButton.setBorder(new LineBorder(Color.red, 1));
				pen.setEnabled(false);
				move.setEnabled(false);
			} else {
				MyButton mb = contactGroups.get(pos);
				mb.setBorder(new LineBorder(Color.red, 1));
				pen.setEnabled(true);
				move.setEnabled(true);
			}
			choosed1 = pos;
		}
	}

	// �༭��ϵ�˷����JTextField�ļ��̼���
	private class EditKeyAdapte extends KeyAdapter {
		int pos = 0;

		public EditKeyAdapte(int i) {
			pos = i;
		}

		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();
			if (k == KeyEvent.VK_ENTER) {
				MyButton mb = contactGroups.get(pos);
				mb.setVisible(true);

				MyTextField tf = contactGroupsTexts.get(pos);
				String s = tf.getText();
				mb.setText(s);
				mb.setBorder(new LineBorder(Color.red, 1));
				tf.setVisible(false);
				choosed1 = pos;
				mb = null;
				tf = null;
			}
		}

	}

	// �༭��ϵ�˷����JTextField�Ľ������
	private class EditTextListener extends FocusAdapter {
		int pos = 0;

		public EditTextListener(int i) {
			pos = i;
		}

		public void focusLost(FocusEvent e) {
			MyButton mb = contactGroups.get(pos);
			mb.setVisible(true);
			MyTextField tf = contactGroupsTexts.get(pos);
			String s = tf.getText();
			mb.setText(s);

			tf.setVisible(false);
			mb = null;
			tf = null;
		}
	}

	// �����µķ��飬����Controlִ���½��������
	private class AddButtonListener1 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {

			MyButton contactGroup = new MyButton("�·���", contactGroups.size());
			contactGroups.add(contactGroup);

			ContactGroupAdapter ca = new ContactGroupAdapter(contactGroup
					.getPos());
			contactGroup.addMouseListener(ca);
			contactGroup.setMyMouseAdapter(ca);
			contactGroup.setVisible(false);

			MyTextField contactGroupText = new MyTextField(contactGroupsTexts
					.size());
			contactGroupsTexts.add(contactGroupText);
			EditKeyAdapte eka = new EditKeyAdapte(contactGroupText.getPos());
			EditTextListener etl = new EditTextListener(contactGroupText
					.getPos());
			contactGroupText.addKeyListener(eka);
			contactGroupText.setMyKeyListener(eka);
			contactGroupText.addFocusListener(etl);
			contactGroupText.setMyFocusLister(etl);
			contactGroupText.setText("�·���");
			contactGroupText.setBounds(0, 30 * (contactGroup.getPos() + 1),
					135, 30);
			contactGroupText.setVisible(true);

			leftPanel1.add(contactGroupText);

			leftPanel1.setPreferredSize(new Dimension(155, (contactGroups
					.size() + 1) * 30));
			for (int i = 0; i < contactGroups.size(); i++) {
				leftPanel1.add(contactGroups.get(i));
				contactGroups.get(i).setLocation(0, 30 * (i + 1));
			}

			contactGroupText.requestFocus();
			contactGroupText.requestFocusInWindow();
			repaint();
			scroll1.getVerticalScrollBar().setValue(100);

			for (int i = 0; i < contactGroups.size(); i++) {
				contactGroups.get(i).setBorder(new LineBorder(Color.black, 0));
			}

			contactGroup.setBorder(new LineBorder(Color.red, 1));
			choosed1 = contactGroup.getPos();
			pen.setEnabled(true);
			move.setEnabled(true);
			repaint();
		}
	}

	// �༭������Ϣ�ļ���������Control,�ı������Ϣ
	private class EditButtonListener1 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (choosed1 != -1) {
				MyButton mb = contactGroups.get(choosed1);
				mb.setVisible(false);

				String s = mb.getText();
				MyTextField tf = contactGroupsTexts.get(choosed1);
				leftPanel1.add(tf);
				tf.setText(s);

				tf.setBounds(0, 30 * (choosed1 + 1), 135, 30);
				tf.setVisible(true);

				tf.requestFocus();
				mb = null;
				tf = null;
			}
		}
	}

	// ɾ������ļ���������Control��ɾ���÷����������ϵ�˺͸÷���

	private class MoveButtonListener1 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (choosed1 != -1) {
				int isDelete = JOptionPane.showConfirmDialog(null,
						"ȷ��Ҫɾ������ϵ�˷���͸���������ϵ����", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (isDelete == 0) {
					leftPanel1.remove(contactGroups.get(choosed1));
					contactGroups.remove(choosed1);
					contactGroupsTexts.remove(choosed1);
					for (int i = 0; i < contactGroups.size(); i++) {
						contactGroups.get(i).setPos(i);
						ContactGroupAdapter ca = new ContactGroupAdapter(
								contactGroups.get(i).getPos());
						contactGroups.get(i).removeMouseListener(
								contactGroups.get(i).getMyMouseAdapter());
						contactGroups.get(i).addMouseListener(ca);
						contactGroups.get(i).setMyMouseAdapter(ca);
					}
					for (int i = 0; i < contactGroupsTexts.size(); i++) {
						contactGroupsTexts.get(i).setPos(i);
						contactGroupsTexts.get(i).removeFocusListener(
								contactGroupsTexts.get(i).getMyFocusLister());
						contactGroupsTexts.get(i).removeKeyListener(
								contactGroupsTexts.get(i).getMyKeyListener());
						EditKeyAdapte eka = new EditKeyAdapte(
								contactGroupsTexts.get(i).getPos());
						EditTextListener etl = new EditTextListener(
								contactGroupsTexts.get(i).getPos());
						contactGroupsTexts.get(i).addKeyListener(eka);
						contactGroupsTexts.get(i).setMyKeyListener(eka);
						contactGroupsTexts.get(i).addFocusListener(etl);
						contactGroupsTexts.get(i).setMyFocusLister(etl);
					}
					leftPanel1.setPreferredSize(new Dimension(155,
							(contactGroups.size() + 1) * 30));
					for (int i = 0; i < contactGroups.size(); i++) {
						leftPanel1.add(contactGroups.get(i));
						contactGroups.get(i).setLocation(0, 30 * (i + 1));
					}
					choosed1 = -2;
					move.setEnabled(false);
					pen.setEnabled(false);
					repaint();
				}
			}
		}
	}

	private class ContactAdapter extends MouseAdapter {
		int pos = 0;

		public ContactAdapter(int i) {
			pos = i;
		}

		// ˫���༭����
		public void mouseClicked(MouseEvent e) {
			int clickTime = e.getClickCount();
			if (clickTime == 2) {
				// �༭��ϵ�˵ĵĶԻ���
				// �����ϵ�˵���Ϣ�����Ի���
				contactDialog.setVisible(true);
			}
		}

		// ��걻����ʱ���õ�Control��ø���ϵ��ϵ������
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < contacts.size(); i++) {
				contacts.get(i).setBorder(new LineBorder(Color.black, 0));
			}
			MyButton mb = contacts.get(pos);
			mb.setBorder(new LineBorder(Color.red, 1));
			pen2.setEnabled(true);
			move2.setEnabled(true);
			choosed2 = pos;
		}
	}

	private class AddButtonListener2 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			// ������ϵ�˴���
			addContactDialog.setVisible(true);
		}
	}

	private class EditButtonListener2 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			// ������ϵ�˴���
			contactDialog.setVisible(true);
		}
	}

	// ɾ����ϵ���أ�����Control��ɾ����ϵ��

	private class MoveButtonListener2 extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (choosed2 != -1) {
				int isDelete = JOptionPane.showConfirmDialog(null,
						"ȷ��Ҫɾ������ϵ����", "��ʾ", JOptionPane.YES_NO_OPTION);
				if (isDelete == 0) {
					leftPanel2.remove(contacts.get(choosed2));
					contacts.remove(choosed2);
					for (int i = 0; i < contacts.size(); i++) {
						contacts.get(i).setPos(i);
						ContactAdapter ca = new ContactAdapter(contacts.get(i)
								.getPos());
						contacts.get(i).removeMouseListener(
								contacts.get(i).getMyMouseAdapter());
						contacts.get(i).addMouseListener(ca);
						contacts.get(i).setMyMouseAdapter(ca);
					}
					leftPanel2.setPreferredSize(new Dimension(155,
							contactGroups.size() * 30));
					for (int i = 0; i < contacts.size(); i++) {
						leftPanel2.add(contactGroups.get(i));
						contactGroups.get(i).setLocation(0, 30 * i);
					}
					choosed2 = -1;
					move.setEnabled(false);
					pen.setEnabled(false);
					repaint();
				}
			}
		}
	}

	public void editContact(MyContact c) {
		MyButton mb = contacts.get(choosed2);
		mb.setText(c.getName());
		// ����Control��������ϵ�˵���Ϣ
	}

	public void addContact(MyContact c) {
		MyButton contact = new MyButton(c.getName(), contacts.size());
		contacts.add(contact);

		ContactAdapter ca = new ContactAdapter(contact.getPos());
		contact.addMouseListener(ca);
		contact.setMyMouseAdapter(ca);
		contact.setVisible(true);

		leftPanel2.add(contact);

		leftPanel2.setPreferredSize(new Dimension(155, contacts.size() * 30));
		for (int i = 0; i < contacts.size(); i++) {
			leftPanel2.add(contacts.get(i));
			contacts.get(i).setLocation(0, 30 * i);
		}

		scroll2.getVerticalScrollBar().setValue(100);

		for (int i = 0; i < contacts.size(); i++) {
			contacts.get(i).setBorder(new LineBorder(Color.black, 0));
		}

		contact.setBorder(new LineBorder(Color.red, 1));
		choosed2 = contact.getPos();
		pen2.setEnabled(true);
		move2.setEnabled(true);
		repaint();
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
			jcb.setBounds(13, 30 * i, 105, 30);
			jcb.setOpaque(false);
			jcbs.add(jcb);
			taskPanel.add(jcbs.get(i));
		}

		taskTable.setBounds(118, 0, 315, 400);
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

	public void itemStateChanged(ItemEvent e) {
		super.itemStateChanged(e);
		choi.select(3);
		Object obj = e.getItem();
		for (i = 0; i < jcbs.size(); i++) {
			if (obj.equals(jcbs.get(i))) {
				if (jcbs.get(i).isSelected()) {

					taskPanel.remove(jcbs.get(i)); // $
					jcbs.get(i).setSelected(true); // $
					taskPanel.add(jcbs.get(i)); // $
					taskPanel.repaint(); // $

					timer = new Timer(); // $
					timerTask = new MyTimerTask(); // $
					timer.schedule(timerTask, 500); // $

					edit.setEnabled(false);
					delete.setEnabled(false);
					over.setEnabled(false);
					selectedTable = -1;

					break;
				}
			}
		}
	}

	//		

	public class TableMouseListener extends MouseAdapter {
		JLabel label = new JLabel("��ק");

		public void mouseClicked(MouseEvent e) {
			int clickTime = e.getClickCount();
			if (clickTime == 2) {
				selectedTable = taskTable.getSelectedRow();
				new NewOreditpanel();
			}
		}

		// ��걻����ʱ���õ�Control��ø���ϵ�������ϵ�������
		public void mousePressed(MouseEvent e) {
			selectedTable = taskTable.getSelectedRow();
			edit.setEnabled(true);
			delete.setEnabled(true);
			over.setEnabled(true);
		}

		public void mouseDragged(MouseEvent e) {
			/*
			 * Point p = e.getPoint();
			 * System.out.println(p.getX()+" "+p.getY());
			 * label.setBounds((int)p.getX(), (int)p.getY(), 400, 10);
			 */
		}

		public void mouseReleased(MouseEvent e) {

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

	public class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// ��selectedTable�ĵ�ѡ�������һ�У��༭
			new NewOreditpanel();
		}
	}

	public class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// ��selectedTable�ĵ�ѡ�������һ�У�ɾ��

			if (selectedTable >= 0) {
				tableModel.removeRow(selectedTable);
				for (int i = 0; i < jcbs.size(); i++) {
					taskPanel.remove(jcbs.get(i));
				}
				jcbs.remove(selectedTable);
				for (int i = 0; i < jcbs.size(); i++) {
					jcbs.get(i).setBounds(15, 30 * i, 142, 30);
					taskPanel.add(jcbs.get(i));
				}
				taskPanel.repaint();
				edit.setEnabled(false);
				delete.setEnabled(false);
				over.setEnabled(false);
				selectedTable = -1;
			}
		}
	}

	private class OverListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// ��selectedTable�ĵ�ѡ�������һ�У�ɾ��
			if (selectedTable >= 0) {
				tableModel.removeRow(selectedTable);
				for (int i = 0; i < jcbs.size(); i++) {
					taskPanel.remove(jcbs.get(i));
				}
				jcbs.remove(selectedTable);
				for (int i = 0; i < jcbs.size(); i++) {
					jcbs.get(i).setBounds(15, 30 * i, 142, 30);
					taskPanel.add(jcbs.get(i));
				}
				taskPanel.repaint();
				edit.setEnabled(false);
				delete.setEnabled(false);
				over.setEnabled(false);
				selectedTable = -1;
			}
		}
	}
}
