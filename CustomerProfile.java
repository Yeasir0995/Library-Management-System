import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class CustomerProfile extends JFrame implements ActionListener
{
	JLabel userLabel, addressLabel, phoneLabel;
	JTextField userTF, phoneTF1, phoneTF2, addressTF;
	JButton loadBtn, delBtn, backBtn, logoutBtn,changepassBtn;
	JPanel panel;
	
	
	String userId;
	
	public CustomerProfile(String userId)
	{
		super("Customer Profile");
		
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.userId = userId;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 100, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		userLabel = new JLabel("User ID : ");
		userLabel.setBounds(250, 70, 120, 30);
		panel.add(userLabel);
		
	
		
		addressLabel = new JLabel("Addrss : ");
		addressLabel.setBounds(250, 120, 120, 30);
		panel.add(addressLabel);
		
		phoneLabel = new JLabel("Phone No. : ");
		phoneLabel.setBounds(250, 170, 120, 30);
		panel.add(phoneLabel);
		
		
		userTF = new JTextField(userId);
		userTF.setBounds(400, 70, 120, 30);
		userTF.setEnabled(false);
		panel.add(userTF);
		
		addressTF = new JTextField();
		addressTF.setBounds(400, 120, 120, 30);
		panel.add(addressTF);
	
		phoneTF1 = new JTextField();
		phoneTF1.setBounds(400, 170, 35, 30);
		phoneTF1.setEnabled(false);
		panel.add(phoneTF1);
		
		phoneTF2 = new JTextField();
		phoneTF2.setBounds(435, 170, 85, 30);
		panel.add(phoneTF2);

		
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(550, 150, 150, 30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		changepassBtn = new JButton("Change password");
		changepassBtn.setBounds(200, 400, 120, 30);
		//changepassBtn.setEnabled(false);
		changepassBtn.addActionListener(this);
		panel.add(changepassBtn);

		
		delBtn = new JButton("Delete");
		delBtn.setBounds(350, 400, 120, 30);
		delBtn.setEnabled(false);
		delBtn.addActionListener(this);
		panel.add(delBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(500, 400, 120, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(backBtn.getText()))
		{
			CustomerHome me = new CustomerHome(userId);
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
		
		else if(text.equals(changepassBtn.getText()))
		{
			ChangePassword lg = new ChangePassword(userId);
			lg.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(loadBtn.getText()))
		{
			loadFromDB();			
		}
		
		else if(text.equals(delBtn.getText()))
		{
			
			deleteFromDB();
			Home l = new Home();
			l.setVisible(true);
			this.setVisible(false);
			
			
			
		}
		else{}
	}
	
	public void loadFromDB()
	{
		String loadId = userId;
		String query = "SELECT `userId`, `address`, `phoneNumber` FROM `customer` WHERE `userId`='"+userId+"';";     
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
			String address = null;
			String phnNo = null;
			
						
			while(rs.next())
			{
                address = rs.getString("address");
				phnNo = rs.getString("phoneNumber");
				flag=true;
				
				addressTF.setText(address);
				phoneTF1.setText("+880");
				phoneTF2.setText(phnNo.substring(0,5));
				userTF.setEnabled(true);
				delBtn.setEnabled(true);
			}
			if(!flag)
			{
				addressTF.setText("");
				phoneTF1.setText("");
				phoneTF2.setText("");
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
	
	
	
	public void deleteFromDB()
	{
		String newId = userId;
		String query1 = "DELETE from customer WHERE userId='"+newId+"';";
		String query2 = "DELETE from login WHERE userId='"+newId+"';";
		System.out.println(query1);
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query1);
			stm.execute(query2);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
			
			/*updateBtn.setEnabled(false);
			delBtn.setEnabled(false);
			userTF.setEnabled(true);
			userTF.setText("");
			eNameTF.setText("");
			phoneTF1.setText("");
			phoneTF2.setText("");
			addressTF.setText("");*/
			
			

		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
	}
	
}