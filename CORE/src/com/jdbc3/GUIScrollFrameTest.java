package com.jdbc3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class GUIScrollFrameTest extends JFrame implements ActionListener,WindowListener {
	private static final String GET_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVGNO FROM STUDENTS";
	private JLabel lsno,lsname,lsadd,lavgno;
	private JTextField tsno,tsname,tsadd,tavgno;
	private JButton bFirst,bNext,bPrevious,bLast;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
		//Constructor
	public GUIScrollFrameTest() {
		System.out.println("GUIScrollFrameTest::0-param constructor(start)");
		setTitle("GUIFrontEnd-ScrollFrame");
		setSize(300,300);
		setLayout(new FlowLayout());
		
		//add components
		lsno=new JLabel("sno");
		add(lsno);
		tsno=new JTextField(10);
		add(tsno);
		lsname=new JLabel("sname");
		add(lsname);
		tsname=new JTextField(10);
		add(tsname);
		lsadd=new JLabel("sadd");
		add(lsadd);
		tsadd=new JTextField(10);
		add(tsadd);
		lavgno=new JLabel("avgno");
		add(lavgno);
		tavgno=new JTextField(10);
		add(tavgno);
		
		bFirst=new JButton("First");
		bNext=new JButton("Next");
		bPrevious=new JButton("Previous");
		bLast=new JButton("Last");
		add(bFirst);add(bNext);add(bPrevious);add(bLast);
		
		//register and activate ActionListner for all 4 buttons
		bFirst.addActionListener(this);
		bNext.addActionListener(this);
		bPrevious.addActionListener(this);
		bLast.addActionListener(this);
		
		//Add WindowListner to Frame
		this.addWindowListener(this);
		
		//disabling editing on text boxes
		tsno.setEditable(false);
		tsname.setEditable(false);
		tsadd.setEditable(false);
		tavgno.setEditable(false);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the close of frame window will terminates the Application flow
		initializeJDBC();
		System.out.println("GUIScrollFrameTest::0-param constructor(end)");
	}
	private void initializeJDBC() {
		System.out.println("GUIScrollFrameTest::initializeJDBC()");
		try {
			//Establish the Connection 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","system");
			//Create Prepared Statement obj
			ps=con.prepareStatement(GET_STUDENTS_QUERY,
					                                          ResultSet.TYPE_SCROLL_INSENSITIVE,
					                                          ResultSet.CONCUR_UPDATABLE);
			//prepare Scrollable RS obj
			rs=ps.executeQuery();
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//initializeJDBC
		public static void main(String[] args){
			System.out.println("GUIScrollFrameTest::start of main method");
			new GUIScrollFrameTest();   //anonmous obj
			System.out.println("GUIScrollFrameTest::end of main method");
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("GUIScrollFrameTest.ActionPerformed");
			boolean flag=false;
			if(ae.getSource()==bFirst) {
				try {
					rs.first();
					flag=true;
					System.out.println("First Button is Clicked");
				}//try
				catch (SQLException se) {
				se.printStackTrace();
				}
			}
			else if(ae.getSource()==bNext) {
				System.out.println("Next Button is Cliscked");
				try {
					if(!rs.isLast()) {
						rs.next();
						flag=true;
					}
				}//try
				catch (SQLException se) {
				    se.printStackTrace();
				}
			}
			if(ae.getSource()==bPrevious) {
				System.out.println("Previous Button is Clicked");
				try {
					if(!rs.isFirst()) {
						rs.previous();
						flag=true;
					}
				}//try
				catch (SQLException se) {
					se.printStackTrace();
				}
			}
			else if(ae.getSource()==bLast) {
				System.out.println("Last Button is Cliscked");
				try {
					rs.last();
					flag=true;
				}
				catch (SQLException se) {
					se.printStackTrace();
				}
			}//else
			if(flag==true) {
			try {
				tsno.setText(rs.getString(1));
				tsname.setText(rs.getString(2));
				tsadd.setText(rs.getString(3));
				tavgno.setText(rs.getString(4));
			}//try
			catch (SQLException se) {
	              se.printStackTrace();
			}
			}//if
		}//actionPerformed
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("GUIScrollFrameTest.window is closing()");
			//close jdbc obj
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch (SQLException se) {
	               se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}//try
			catch (SQLException se) {
	               se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}//try
			catch (SQLException se) {
	               se.printStackTrace();
			}
		}
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
}//class
