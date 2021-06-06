import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home extends JFrame implements ActionListener
{
	JLabel title, userLabel, passLabel,imgLabel;
	JTextField userTF;
	JPasswordField passPF;
	JButton loginBtn, exitBtn,SignUpBtn;
	ImageIcon img;
	JPanel panel;
	String userId;
	
	public Home()
	{
		super("HOME");
		
		this.setSize(950, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(new Color(241-222-132));
		panel.setLayout(null);
		
		Font f1 = new Font("Cambria", Font.BOLD, 50);
		
		title = new JLabel("Library Management System");
		title.setBounds(50, 150, 380, 30);
		title.setFont(new Font("Harrington",Font.BOLD,27));
		panel.add(title);
		
		img = new ImageIcon("library.jpg");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(200, 0, 900, 750);
		panel.add(imgLabel);
		
		userLabel = new JLabel("User ID : ");
		
		userLabel.setBounds(50, 200, 60, 30);
		panel.add(userLabel);
		
		userTF = new JTextField();
		userTF.setBounds(110, 200, 100, 30);
		panel.add(userTF);
		
		passLabel = new JLabel("Password : ");
		passLabel.setBounds(50, 250, 70, 30);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(120, 250, 100, 30);
		panel.add(passPF);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(50, 300, 80, 30);
		loginBtn.setBackground(Color.GREEN);
		loginBtn.addActionListener(this);
		panel.add(loginBtn);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(150, 300, 80, 30);
		exitBtn.setBackground(Color.RED);
		exitBtn.addActionListener(this);
		panel.add(exitBtn);
		
		
		SignUpBtn = new JButton("SignUP");
		SignUpBtn.setBounds(80, 360, 80, 30);
		SignUpBtn.setBackground(Color.WHITE);
		SignUpBtn.addActionListener(this);
		panel.add(SignUpBtn);
		
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(loginBtn.getText()))
		{
			checkLogin();
			
		}
		 else if(text.equals(SignUpBtn.getText()))
		{
			Signup su = new Signup(userId);
			su.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(exitBtn.getText()))
		{
			System.exit(0);
		}
		else{}
	}
	public void checkLogin()
	{
		String query = "SELECT `userId`, `password`,`status` FROM `login`;";     
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
			
			boolean flag = false;
			while(rs.next())
			{
                String userId = rs.getString("userId");
                String Password = rs.getString("password");
				int status = rs.getInt("status");
				
				if(userId.equals(userTF.getText()) && Password.equals(passPF.getText()))
				{
					flag=true;
					if(status==1)
					{
						EmployeeHome eh = new EmployeeHome(userId);
						eh.setVisible(true);
						this.setVisible(false);
					}
					else if(status==0)
					{
						CustomerHome ch = new CustomerHome(userId);
						ch.setVisible(true);
						this.setVisible(false);
					}
					else{}
				}
			}
			if(!flag)
			{
				JOptionPane.showMessageDialog(this,"Invalid ID or Password"); 
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
	
	