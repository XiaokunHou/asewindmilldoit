package cn.edu.nju.software.view;

import java.awt.Choice;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 * ����ģʽ��ʱ�䡢��Ŀ���龳����ϵ�ˡ��鵵�⣩�ĸ���
 */

public class BaseMode extends JPanel implements ActionListener, ItemListener, FocusListener {

	protected JPanel imagePanel; // logo���
	protected ImageIcon ic; // logoͼ��

	protected JPanel leftPanel; // ���һ�а�ť�������
	protected ImageIcon leftPanelIcon;
	protected Choice choi;

	protected JPanel inputPanel;
	protected JLabel penIm;
	protected JTextField field;

	protected JToolBar toolBar; // ���������������������ã���Ϣ���ǳ��ĸ�ѡ��
	protected ImageIcon toolbarIcon; // ����������ͼ��
	protected JButton search;
	protected JButton set;
	protected JButton mes;
	protected JButton logout;

	protected JPanel taskPanel; // �������

	protected TimerTask tt; // �������½�ʱ����ʾ��TimeTaskʵ��
	protected Timer timer; // �������½�ʱ����ʾ��Timerʵ��
	protected JLabel timeLabel; // ʱ���ǩ

	protected ImageIcon backIcon; // ����ģʽ����ϵ���ͼ

	public BaseMode() {

		this.setLayout(null);

		// �����������Ͻǵ�logo
		ic = new ImageIcon("src/res/logo.png");
		imagePanel = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(ic.getImage(), 0, 0, 170, 70, this);
			}
		};
		imagePanel.setBounds(25, 30, 175, 70);
		imagePanel.setLayout(null);
		this.add(imagePanel);

		// ����������
		leftPanelIcon = new ImageIcon("src/res/leftback.png");
		leftPanel = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(leftPanelIcon.getImage(), 0, 0, 155, 450, this);
			}
		};
		leftPanel.setBounds(33, 112, 155, 450);
//		leftPanel.setBackground(new Color(177, 139, 109));
		leftPanel.setLayout(null);
		this.add(leftPanel);

		// ÿ��ģʽ�µ�����嶼������ģʽ��ѡ��
		choi = new Choice();
		choi.add("ʱ��");
		choi.add("�龳");
		choi.add("��Ŀ");
		choi.add("��ϵ��");
		choi.add("�鵵��");

		choi.setBounds(5, 5, 130, 30);
		leftPanel.add(choi);

		inputPanel = new JPanel();
		inputPanel.setBounds(205, 66, 550, 30);
		inputPanel.setLayout(null);
		this.add(inputPanel);

		field = new JTextField("�����س�������ӵ����񽫽���ʱ�䡢�龳����Ŀ���ռ���");
		field.setBounds(30, 2, 520, 25);
		field.setBorder(null);
		inputPanel.add(field);

		penIm = new JLabel(new ImageIcon("src/res/pen.png"));
		penIm.setBounds(0, 0, 25, 30);
		inputPanel.add(penIm);
		// field.setBorder(new LineBorder(Color.red, 1));

		ImageIcon ser = new ImageIcon("src/res/search.png");
		search = new JButton(ser);
		search.setBounds(0, 0, 25, 25);

		ImageIcon se = new ImageIcon("src/res/set.png");
		set = new JButton(se);
		set.setBounds(50, 0, 25, 25);

		ImageIcon me = new ImageIcon("src/res/mes.png");
		mes = new JButton(me);
		mes.setBounds(100, 0, 25, 25);
		this.add(mes);

		ImageIcon lo = new ImageIcon("src/res/logout.png");
		logout = new JButton(lo);
		logout.setBounds(150, 0, 25, 25);
		this.add(logout);
		logout.addActionListener(this);

		// ��������������ĸ���ť
		toolBar = new JToolBar();
		toolBar.setOpaque(false);	//���ù���������͸��
		
		/*�������ַ�����Ϊ�����ڲ��࣬��дpaintComponent�շ���Ҳ��
		 {public void paintComponent(Graphics g) {}};*/
		toolBar.setBounds(700, 25, 175, 25);
		toolBar.setBorderPainted(false);
		toolBar.setLayout(null);
		toolBar.setFloatable(true); // ������������������ΪTrue,�����������������϶�����Ҫ��λ��
		toolBar.add(search);
		toolBar.add(set);
		toolBar.add(mes);
		toolBar.add(logout);
		this.add(toolBar);
		
		//Ϊ�����ı�����ӽ����¼��ͼ����¼�
		field.addFocusListener(this);
		field.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					System.out.println(field.getText().trim());
					field.setText("");
				}
			}
		});
		

		// Ϊ��������Ӽ���
		search.addActionListener(this);
		set.addActionListener(this);
		mes.addActionListener(this);
		logout.addActionListener(this);

		// ���ù�������ť�������ͣ������Ϣ
		search.setToolTipText("����");
		set.setToolTipText("����");
		mes.setToolTipText("��Ϣ");
		logout.setToolTipText("�˳�");

		// ��ʾ�����б��JPanel
		taskPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				ImageIcon taskpanelIcon = new ImageIcon("src/res/mail4.jpg");
				g.drawImage(taskpanelIcon.getImage(), 0, 0, 583, 360, null);
			}
		};
		taskPanel.setBounds(207, 152, 583, 360);
		taskPanel.setLayout(null);
		this.add(taskPanel);

		timeLabel = new JLabel();
		timeLabel.setBounds(702, 544, 146, 20);
		this.add(timeLabel);

		tt = new TimerTask() {
			public void run() {
				timeLabel.setText(new Date().toLocaleString());
			}
		};
		timer = new Timer();
		timer.schedule(tt, 0, 1000);

		// Ϊģʽ��ѡ������¼�����
		choi.addItemListener(this);

	}

	public void paintComponent(Graphics g) {
		backIcon = new ImageIcon("src/res/background3.png");
		g.drawImage(backIcon.getImage(), 0, 0, 898, 617, null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logout)
			System.exit(0);
		else if (e.getSource() == search) {
			new searchDia();
		} else if (e.getSource() == set) {
			new setDia();
		}
	}

	public void itemStateChanged(ItemEvent ie) {
		if (ie.getItemSelectable() == choi) {
			if (choi.getSelectedItem().equals("ʱ��")) {
				MainFrame.crd.show(MainFrame.mainPanel, "0");
			}
			else if (choi.getSelectedItem()=="�龳") {
				MainFrame.crd.show(MainFrame.mainPanel, "1");
			}
			else if (choi.getSelectedItem()=="��Ŀ") {
				MainFrame.crd.show(MainFrame.mainPanel, "2");
			}
			else if (choi.getSelectedItem()=="��ϵ��") {
				MainFrame.crd.show(MainFrame.mainPanel, "3");
			}
			else if(choi.getSelectedItem()=="�鵵��"){
				MainFrame.crd.show(MainFrame.mainPanel, "4");
			}
			else{
				
			}
		}
		
	}

	public void focusGained(FocusEvent fe) {
		if(fe.getSource()==field){
			field.setText("");
		}
	}

	public void focusLost(FocusEvent fe) {
		if(fe.getSource()==field){
			field.setText("�����س�������ӵ����񽫽���ʱ�䡢�龳����Ŀ���ռ���");
		}
	}

}
