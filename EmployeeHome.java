import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class EmployeeHome extends JFrame implements ActionListener
{
	JLabel welcomeLabel;
	JButton manageEmployeeBtn,customerBtn,borrowBtn, viewDetailsBtn,updateBtn,bookBtn,logoutBtn;
	JPanel panel;
	String userId,role;
	
	public EmployeeHome(String userId)
	{
		super("Employee Home Window");
		
		this.userId = userId;
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
	   panel.setBackground(Color.CYAN);
		panel.setLayout(null);
		
		welcomeLabel = new JLabel("Welcome, "+userId);
		welcomeLabel.setBounds(350, 50, 130, 50);
		panel.add(welcomeLabel);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		customerBtn = new JButton("Customer info");
		customerBtn.setBounds(50, 120, 150, 30);
	    customerBtn.addActionListener(this);
		panel.add(customerBtn);
		
		manageEmployeeBtn = new JButton("Manage Employee");
		manageEmployeeBtn.setBounds(220, 120, 150, 30);
		manageEmployeeBtn.addActionListener(this);
		panel.add(manageEmployeeBtn);
		
		viewDetailsBtn = new JButton("My Information");
		viewDetailsBtn.setBounds(400, 120, 150, 30);
		viewDetailsBtn.addActionListener(this);
		panel.add(viewDetailsBtn);
		
	     		borrowBtn = new JButton("borrow");
		borrowBtn.setBounds(400, 170, 150, 30);
		borrowBtn.addActionListener(this);
		panel.add(borrowBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(220, 170, 150, 30);
		updateBtn.addActionListener(this);
		panel.add(updateBtn);
		
		bookBtn = new JButton("Book List");
		bookBtn.setBounds(50, 170, 150, 30);
		bookBtn.addActionListener(this);
		panel.add(bookBtn);
		
		
	
		
		this.add(panel);
	}
	
	public void Checkrole()
	{
		    String query = "SELECT `role` FROM `employee` WHERE `userId`='"+userId+"';"; 
            Connection con=null;//for connection
            Statement st = null;//for query execution
		    ResultSet rs = null;//to get row by row result from DB
		    System.out.println(query);
            try
		    {
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			while(rs.next())
			{
			System.out.println("age");
			role = rs.getString("role");
			System.out.println("Role : "+role);
			}
			
			if(role.equals("Manager"))
			{
				ManageEmployee me = new ManageEmployee(userId);
				me.setVisible(true);
				this.setVisible(false);
			}
			
			}
			
			
			catch(Exception ex)
		    {
			System.out.println("Exception : " +ex.getMessage());
            }
			
	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(logoutBtn.getText()))
		{
			Home hg = new Home();
			hg.setVisible(true);
			this.setVisible(false);
		}
		
		else if(text.equals(customerBtn.getText()))
		{
			 CustomerInfo cp = new CustomerInfo(userId);
			cp.setVisible(true);
			this.setVisible(false);
		}
		
		else if(text.equals(borrowBtn.getText()))
		{
			Borrow cp = new Borrow(userId);
			cp.setVisible(true);
			this.setVisible(false);
		}
		
		else if(text.equals(manageEmployeeBtn.getText()))
		{
			Checkrole();
			
			
			}
				
			
		else if(text.equals(viewDetailsBtn.getText()))
		{
			
				MyProfile me = new MyProfile(userId);
				me.setVisible(true);
				this.setVisible(false);
			
		}
		
		else if(text.equals(updateBtn.getText()))
		{
			
				Addbook e = new Addbook(userId);
				e.setVisible(true);
				this.setVisible(false);
			
		}
		else if(text.equals(bookBtn.getText()))
		{
			
				Book m = new Book(userId);
				m.setVisible(true);
				this.setVisible(false);
			
		}
		else{}
	}
	
}