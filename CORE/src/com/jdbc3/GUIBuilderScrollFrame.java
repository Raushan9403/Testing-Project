package com.jdbc3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIBuilderScrollFrame {
private static final String SELECT_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVGNO FROM STUDENTS";
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBuilderScrollFrame window = new GUIBuilderScrollFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIBuilderScrollFrame() {
		initialize();
		initializeJDBC();
	}
	private void initializeJDBC() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "system");
			ps=con.prepareStatement(SELECT_STUDENTS_QUERY,
					                                           ResultSet.TYPE_SCROLL_SENSITIVE,
					                                           ResultSet.CONCUR_READ_ONLY);
			rs=ps.executeQuery();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//initializeJDBC();

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("GUIBuilderScrollFrame.initialise()");
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("GUIBuilderScrollFrame.initialise()..new Window Adapter()...{} window Closing()...{}");
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
		});
		frame.setBounds(100, 100, 425, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SNO");
		lblNewLabel.setBounds(10, 25, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(85, 22, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("SNAME");
		lblNewLabel_1.setBounds(10, 64, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(85, 61, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SADD");
		lblNewLabel_2.setBounds(10, 98, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(85, 95, 96, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("AVGNO");
		lblNewLabel_3.setBounds(10, 140, 45, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(85, 137, 96, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("FIRST");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}
				catch (SQLException se) {
				se.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 198, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NEXT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isLast()) {
						rs.next();
						textField.setText(rs.getString(1));
						textField_1.setText(rs.getString(2));
						textField_2.setText(rs.getString(3));
						textField_3.setText(rs.getString(4));
						
					}//if
				}//try
				catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(122, 198, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("PREVIOUS");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isFirst()) {
						rs.previous();
						textField.setText(rs.getString(1));
						textField_1.setText(rs.getString(2));
						textField_2.setText(rs.getString(3));
						textField_3.setText(rs.getString(4));
     			}//if
				}//try
				catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(227, 198, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("LAST");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				
				}
				catch (SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(322, 198, 85, 21);
		frame.getContentPane().add(btnNewButton_3);
	}

}
