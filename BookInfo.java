import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class BookInfo extends JFrame implements ActionListener
{
	JLabel bookIdlabel,bookTitlelabel,authorNamelabel,publicationYearlabel,availableQuantity;
	JTextField searchTF,idTF,titleTF,authorNameTF,publicationYearTF,availableQuantityTF;
	JButton  search,logoutBtn, backBtn;
	JPanel panel;
	String userId;
	
	public BookInfo()
	{
		super("BookInfo");
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
				panel.setBackground(Color.CYAN);
		panel.setLayout(null);
		
		search = new JButton("Search ");
		search.setBounds(300, 20, 100, 30);
		search.addActionListener(this);
		panel.add(search);
		
		searchTF= new JTextField();
		searchTF.setBounds(400, 20, 120, 30);
		panel.add(searchTF);
		
		
		
		bookIdlabel= new JLabel("Book ID : ");
		bookIdlabel.setBounds(250, 100, 100, 30);
		panel.add(bookIdlabel);
		
		idTF = new JTextField();
		idTF.setBounds(400, 100, 100, 30);
		panel.add(idTF);
		
		bookTitlelabel = new JLabel("Book Title: ");
		bookTitlelabel.setBounds(250, 130, 100, 30);
		panel.add(bookTitlelabel);
		
		titleTF = new JTextField();
		titleTF.setBounds(400, 130, 100, 30);
		panel.add(titleTF);
		
		authorNamelabel = new JLabel("Author : ");
		authorNamelabel.setBounds(240, 160, 120, 30);
		panel.add(authorNamelabel);
		
		authorNameTF = new JTextField();
		authorNameTF.setBounds(400, 160, 100, 30);
		panel.add(authorNameTF);
		
		publicationYearlabel = new JLabel("publication Year: ");
		publicationYearlabel.setBounds(250, 190, 100, 30);
		panel.add(publicationYearlabel);
		
		publicationYearTF = new JTextField();
		publicationYearTF.setBounds(400, 190, 100, 30);
		panel.add(publicationYearTF);
		
		availableQuantity = new JLabel(" available Quantity");
		authorNamelabel.setBounds(250, 220, 100, 30);
		panel.add(availableQuantity);
		
		availableQuantityTF = new JTextField();
		availableQuantityTF.setBounds(400, 220, 100, 30);
		panel.add(availableQuantityTF);
		
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 280, 80, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		backBtn = new JButton("BACK");
		backBtn.setBounds(700,280,80,30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		this.add(panel);	
		
	}
	
		public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(backBtn.getText()))
		{
			CustomerHome ch = new CustomerHome(userId);
			ch.setVisible(true);
			this.setVisible(false);
			
		}
		
		else if(text.equals(search.getText()))
		{
	
		fromDB();
		}
			else if(text.equals(logoutBtn.getText()))
		{
	
			Home hg = new Home();
			hg.setVisible(true);
			this.setVisible(false);
			
		}
			
	}
	public void fromDB()
	{
		 
		/* /* String bookId = idTF.getText();
		String bookTitle = titleTF.getText();
		String authorName = authorNameTF.getText();
		String publicationYear = publicationYearTF.getText();
		String availableQuantity = availableQuantityTF.getText();
		String search = searchTF.getText(); */
		//String query1 = "SELECT `bId`,`newTitle`,`newauthor`,`newpublicationyear`,`newquantity` FROM `booklist` WHERE `newTitle`='"+search+"';";
		
		
		String sf=searchTF.getText();
		String query1="SELECT * FROM `book` WHERE `bookId` Like'%"+sf+"%'or `bookTitle` Like'%"+searchTF+"%'or `authorName` Like'%"+searchTF+"%'or publicationYear Like'%"+searchTF+"%'or availableQuantity Like'%"+searchTF+"%'";
		Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB 
		System.out.println(query1); 
		

        try
		{
			
			//String query1 = "SELECT `bookId`,`bookTitle`,`authorName`,`publicationYear`,`availableQuantity` FROM `BookList` WHERE `bookTitle=?`;";
		//System.out.println(query1);
			/* Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			Statement stm = con.createStatement();
			ResultSet rs=stm.executeQuery("SELECT * FROM `Book`"); */
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query1);//getting result
			System.out.println("results received");
			
			boolean flag = false;
			 String bookId = null;
			 String bookTitle = null;
			 String authorName =null;
			String publicationYear=null;
			 String availableQuantity=null;
			
			while(rs.next()){
				
				 bookId = rs.getString("bookId");
				 bookTitle = rs.getString("bookTitle");
				  authorName = rs.getString("authorName");
				  publicationYear = rs.getString("publicationYear");
				 availableQuantity = rs.getString("availableQuantity");
				flag=true;
				
				idTF.setText(bookId);
				titleTF.setText(bookTitle);
				authorNameTF.setText(authorName);
				publicationYearTF.setText(publicationYear);
				availableQuantityTF.setText(availableQuantity);
				//searchTF.setText(flag);
				
			}
			if(!flag)
			{
				idTF.setText("");
				titleTF.setText("");
				authorNameTF.setText("");
				publicationYearTF.setText("");
				availableQuantityTF.setText("");
				
				
				//JOptionPane.showMessageDialog(this,"Invalid ID"); 
			}
			//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			st.execute(query1);
			st.close();
			con.close();
			//JOptionPane.showMessageDialog(this, "Found !!!");
		}
        catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Not Found !!!");
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
            catch(Exception e){}
        }
    }	
}
	

		
		