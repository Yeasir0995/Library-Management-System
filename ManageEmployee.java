import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class ManageEmployee extends JFrame implements ActionListener
{
	JButton addEmployeeBtn, viewEmployeeBtn, backBtn, logoutBtn,viewallEmployeeBtn;
	JPanel panel;
	String userId;
	
	public ManageEmployee(String userId)
	{
		super("Manage Employee ");
		
		this.userId = userId;
		this.setSize(800,650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(null);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		addEmployeeBtn = new JButton("Add Employee");
		addEmployeeBtn.setBounds(50, 120, 150, 30);
		addEmployeeBtn.addActionListener(this);
		panel.add(addEmployeeBtn);
		
		viewEmployeeBtn = new JButton("View Employee");
		viewEmployeeBtn.setBounds(220, 120, 150, 30);
		viewEmployeeBtn.addActionListener(this);
		panel.add(viewEmployeeBtn);
	
		
		viewallEmployeeBtn = new JButton("Employee List");
		viewallEmployeeBtn.setBounds(220, 180, 150, 30);
		viewallEmployeeBtn.addActionListener(this);
		panel.add(viewallEmployeeBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(600, 90, 150, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		/* Random rand = new Random();
		int  x = rand.nextInt(5);
		String y= ".jpg";
		String x1 =""+x+".jpg";
		img = new ImageIcon(x1);
		imgLabel = new JLabel(img);
		imgLabel.setBounds(0, 0, 800, 800);
		panel.add(imgLabel); */
			
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(backBtn.getText()))
		{
			EmployeeHome eh = new EmployeeHome(userId);
			eh.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(logoutBtn.getText()))
		{
			Home lg = new Home();
			lg.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(addEmployeeBtn.getText()))
		{
			AddEmployee a = new AddEmployee(userId);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(viewEmployeeBtn.getText()))
		{
			ViewEmployee ve = new ViewEmployee(userId);
			ve.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(viewallEmployeeBtn.getText()))
		{
			EmployeeList ve = new EmployeeList(userId);
			ve.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}

}