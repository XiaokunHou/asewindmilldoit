package cn.edu.nju.software.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyJPanel extends JPanel{
    private Image image;
    public MyJPanel(Image image){ 
	this.image=image;
	Dimension size = new Dimension(image.getWidth(null),
			image.getHeight(null));
	}
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null); //用G 把Image画出来 
    }
}