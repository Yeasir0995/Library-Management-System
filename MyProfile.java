import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class MyProfile extends JFrame implements ActionListener
{
	JLabel userLabel, eNameLabel, phoneLabel, roleLabel,salaryLabel;
	JTextField userTF, phoneTF1, phoneTF2, eNameTF, roleTF,salaryTF;
	JButton  loadBtn,  backBtn, logoutBtn,employeepassBtn;
	JPanel panel;
	
	String userId;
	
	public MyProfile(String userId)
	{
		super("MyProfile");
		
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.userId = userId;
		
		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setLayout(null);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		/*refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(250, 100, 275, 30);
		refreshBtn.addActionListener(this);
		panel.add(refreshBtn);*/
		
		userLabel = new JLabel("User ID : ");
		userLabel.setBounds(250, 70, 120, 30);
		panel.add(userLabel);
		
		eNameLabel = new JLabel("Customer Name : ");
		eNameLabel.setBounds(250, 120, 120, 30);
		panel.add(eNameLabel);
		
		phoneLabel = new JLabel("Phone No. : ");
		phoneLabel.setBounds(250, 170, 120, 30);
		panel.add(phoneLabel);
		
		roleLabel = new JLabel("Role : ");
		roleLabel.setBounds(250, 220, 120, 30);
		panel.add(roleLabel);
		
		salaryLabel = new JLabel("Salary : ");
		salaryLabel.setBounds(250, 270, 120, 30);
		panel.add(salaryLabel);
		
		userTF = new JTextField(userId);
		userTF.setBounds(400, 70, 120, 30);
		userTF.setEnabled(false);
		panel.add(userTF);
		
		eNameTF = new JTextField();
		eNameTF.setBounds(400, 120, 120, 30);
		panel.add(eNameTF);
	
		phoneTF1 = new JTextField();
		phoneTF1.setBounds(400, 170, 35, 30);
		phoneTF1.setEnabled(false);
		panel.add(phoneTF1);
		
		phoneTF2 = new JTextField();
		phoneTF2.setBounds(435, 170, 85, 30);
		panel.add(phoneTF2);

		roleTF = new JTextField();
		roleTF.setBounds(400, 220, 120, 30);
		panel.add(roleTF);
	
		salaryTF = new JTextField();
		salaryTF.setBounds(400, 270, 120, 30);
		panel.add(salaryTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(550, 150, 150, 30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		
		backBtn = new JButton("Back");
		backBtn.setBounds(500, 400, 120, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		employeepassBtn = new JButton("Change password");
		employeepassBtn.setBounds(200, 400, 180, 30);
		employeepassBtn.addActionListener(this);
		panel.add(employeepassBtn);
		
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
			EmployeeHome me = new EmployeeHome(userId);
			me.setVisible(true);
			this.setVisible(false);
		}
		/*if(text.equals(refreshBtn.getText()))
		{
			updateBtn.setEnabled(false);
			delBtn.setEnabled(false);
			userTF.setEnabled(true);
			userTF.setText("");
			eNameTF.setText("");
			phoneTF1.setText("");
			phoneTF2.setText("");
			addressTF.setText("");
			
		}*/
		else if(text.equals(logoutBtn.getText()))
		{
			Home lg = new Home();
			lg.setVisible(true);
			this.setVisible(false);
		}
		
		else if(text.equals(employeepassBtn.getText()))
		{
			EmpPass lg = new EmpPass(userId);
			lg.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(loadBtn.getText()))
		{
			loadFromDB();			
		}
		
			
			
	
		else{}
	}
	
	public void loadFromDB()
	{
		//String loadId = userId;
		String query = "SELECT `employeeName`, `phoneNumber`, `role`, `salary` FROM `employee` WHERE `userId`='"+userId+"';";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			
			boolean flag = false;
			String eName = null;
			String phnNo = null;
			String role =null;
			double sal = 0.0;
			String salary = Double.toString(sal);
						
			while(rs.next())
			{
                eName = rs.getString("employeeName");
				phnNo = rs.getString("phoneNumber");
				role = rs.getString("role");
				salary = rs.getString("salary");
				flag=true;
				
				eNameTF.setText(eName);
				phoneTF1.setText("+880");
				phoneTF2.setText(phnNo.substring(4,14));
				roleTF.setText(role);
				salaryTF.setText(salary);
				//userTF.setEnabled(false);
				
			}
			if(!flag)
			{
				eNameTF.setText("");
				phoneTF1.setText("");
				phoneTF2.setText("");
				roleTF.setText("");
				salaryTF.setText("");
				JOptionPane.showMessageDialog(this,"Invalid ID"); 
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}
	
}
	
	