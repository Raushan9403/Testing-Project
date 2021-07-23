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



public class GUIIrtcTab extends JFrame implements ActionListener,WindowListener {
	private static final String GET_IRTC_TAB_QUERY="SELECT COUNT(*) FROM IRTC_TAB WHERE UNAME=? AND PWD=?";
	private JLabel lUname,lPwd;
	private JTextField tUname,tPwd;
	private JButton bSummit;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
		//Constructor
	public GUIIrtcTab() {
		System.out.println("GUIIrtcTab::0-param constructor(start)");
		setTitle("GUIFrontEnd-IrtcTab");
		setSize(300,300);
		setLayout(new FlowLayout());
		
		//add components
		lUname=new JLabel("Uname");
		add(lUname);
		tUname=new JTextField(10);
		add(tUname);
		lPwd=new JLabel("Pwd");
		add(lPwd);
	   tPwd=new JTextField(10);
		add(tPwd);
		
		bSummit=new JButton("Summit");
			add(bSummit);	
		//register and activate ActionListner for all 1 buttons
		bSummit.addActionListener(this);

		//Add WindowListner to Frame
		this.addWindowListener(this);
		
		//disabling editing on text boxes
		tUname.setEditable(false);
		tPwd.setEditable(false);
		

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the close of frame window will terminates the Application flow
		initializeJDBC();
		System.out.println("GUIIrtcTab::0-param constructor(end)");
	}
	private void initializeJDBC() {
		System.out.println("GUIIrtcTab::initializeJDBC()");
		try {
			//Establish the Connection 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ed","root","system");
			//Create Prepared Statement obj
			ps=con.prepareStatement(GET_IRTC_TAB_QUERY);
					                                      
		ps.executeQuery();
		
			
	
			

		}//try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//initializeJDBC
		public static void main(String[] args){
			System.out.println("GUIIrtcTab::start of main method");
			new GUIIrtcTab();   //anonmous obj
			System.out.println("GUIIrtcTab::end of main method");
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			System.out.println("GUIIrtcTab.ActionPerformed");
			if(ae.getSource()==bSummit) {
				
			
			System.out.println("Summit Button is Clicked");
				
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
