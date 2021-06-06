import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;


public class Borrow extends JFrame implements ActionListener
{
	private JLabel bookIdlabel,userIdlabel;
	private JTextField bookTF,userTF,newbookIdTF,borrowIdTF,bookIdTF;
	private JButton  borrowBtn,backBtn,logoutBtn;
	JPanel panel;
	String userId,bookId;
	
	public Borrow(String userId)
	{
		super("Borrow");
		
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.userId = userId;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		bookIdlabel= new JLabel("Book ID : ");
		bookIdlabel.setBounds(250, 100, 120, 30);
		panel.add(bookIdlabel);
		
		bookTF = new JTextField();
		bookTF.setBounds(400, 100, 120, 30);
		panel.add(bookTF);
		
		userIdlabel = new JLabel("User ID : ");
		userIdlabel.setBounds(250, 130, 120, 30);
		panel.add(userIdlabel);
		
		userTF = new JTextField();
		userTF.setBounds(400, 130, 120, 30);
		panel.add(userTF);
		
		borrowBtn = new JButton("Borrow");
		borrowBtn.setBounds(350, 170, 100, 30);
		borrowBtn.addActionListener(this);
		panel.add(borrowBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(350, 220, 100, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		logoutBtn = new JButton("Log out");
		logoutBtn.setBounds(350, 260, 120, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		this.add(panel);
		
	}
	
	public void loadFromDB(String bookId)
	{
		
		String loadId = bookId;
		String query = "SELECT *  FROM `book` WHERE `bookId`='"+loadId+"';";     
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
			String bName = null;
			String aName = null;
			int pYear = 0;
			int qtn = 0;			
			while(rs.next())
			{
                bName = rs.getString("bookTitle");
				aName = rs.getString("authorName");
				pYear = rs.getInt("publicationYear");
				qtn = rs.getInt("availableQuantity")-1;
				flag=true;
				updateInDB2(loadId,bName,aName,pYear,qtn);
				
				
				
			}
			if(!flag)
			{
				
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
	
	
	public void updateInQtn(String bookId)
	{
		    
			loadFromDB(bookId);
			
			
	}
	
	
	public void updateInDB2(String bookId,String bName,String aName,int pYear,int qtn)
	{
		String newId = bookId;
		String eName = bName;
		String anName = aName;
		int NewYear = pYear;
		int Newqtn=qtn;
		/*try
		{
			newYear = Integer.parseInt(yearTF.getText());
			qtn = Integer.parseInt(qtnTF.getText());
			
		}*/
		//catch(Exception e){}
		String query = "UPDATE book SET bookTitle='"+eName+"', authorName = '"+anName+"', publicationYear = '"+NewYear+"', availableQuantity = "+Newqtn+" WHERE bookId='"+newId+"'";	
        Connection con=null;//for connection
        Statement st = null;//for query execution
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			st = con.createStatement();//create statement
			st.executeUpdate(query);
			st.close();
			con.close();
			//JOptionPane.showMessageDialog(this, "Success !!!");
			
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Oops !!!");
		}
	}
	
	
	
	
	
	
	public void updateInDB()
	{
		String newbookIdTF = bookTF.getText();
		String newUser = userId;
		//String newborrowIdTF = borrowIdTF.getText();
		//String newborrowDatetf=borrowDateTF.getText();
		//String newreturnDatetf=returnDateTf.getText();
		
		String query = "INSERT INTO borrowinfo VALUES ('"+newbookIdTF+"','"+newUser+"');";	
        Connection con=null;//for connection
        Statement st = null;//for query execution
		System.out.println(query);
        try 
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			st = con.createStatement();//create statement
			st.executeUpdate(query);
			updateInQtn(newbookIdTF);
			st.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
			
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Oops !!!");
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(borrowBtn.getText()))
			
		{   
                        updateInDB();
                  		BorrowInfo bi = new BorrowInfo(userId);
						//EmployeeHome ch = new EmployeeHome(userId);
						bi.setVisible(true);
						//ch.setVisible(true);
						this.setVisible(false);
		
		}
		else if(text.equals(logoutBtn.getText()))
		{
			Home h = new Home();
						h.setVisible(true);
						this.setVisible(false);
		}
		else if(text.equals(backBtn.getText()))
		{
		EmployeeHome eh = new EmployeeHome(userId);
						eh.setVisible(true);
						this.setVisible(false);
		}
	}
}