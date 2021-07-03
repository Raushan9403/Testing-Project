package com.object;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SwigandAwtTest extends JFrame implements ActionListener {
	static JButton b;
     SwigandAwtTest()
{
    	 this.setSize(600, 500);
    	 this.setTitle("this is my first frame");
    	 this.setLayout(null);
    	 b=new JButton("Click me");
    	 b.setBounds(100, 100, 100, 50);
    	 b.setFont(new Font("Candara",Font.BOLD, 25));
    	 b.addActionListener(this);
    	 this.add(b);
    	 
	this.setVisible(true);
}
     public void actionPerformed(ActionEvent ae) {
    	 System.out.println("button is clicked ");
     }
	public static void main(String[] args) {
		new SwigandAwtTest();
		

	}

}
