import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

public class EmployeeBook extends JFrame implements ActionListener
{   private JLabel label;
	private JTable userTable;
	private JPanel panel;
	private JScrollPane userScroll;
	private JButton addBtn,deleteBtn,logoutBtn,backBtn;
	String bookTitle,authorName,userId;
	int bookID, publicationYear,availableQuantity;
	DefaultTableModel tableModel;
	
	public EmployeeBook(String userId)
	{
		super("Book List");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		label = new JLabel("BOOK LIST ");
		label.setBounds(250, 20, 220, 70);
		panel.add(label);
		
		String []col = {"BookID","Title","Author name","publication year", "Available Quantity"};
		
		
		userTable = new JTable();
		tableModel = new DefaultTableModel(0,5);
		tableModel.setColumnIdentifiers(col);
		userTable.setModel(tableModel);
		userScroll = new JScrollPane(userTable);
		userScroll.setBounds(50,100,400,150);
		panel.add(userScroll);
		
		//PopulateTable(tableModel, "");
		
		addBtn = new JButton("Add");
		addBtn.setBounds(350,20-5,100,30);
		addBtn.addActionListener(this);
		panel.add(addBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(350,20-5,100,30);
		deleteBtn.addActionListener(this);
		panel.add(deleteBtn);
		
		logoutBtn = new JButton("logout");
		logoutBtn.setBounds(350,20-5,100,30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		//50,300,100,40
		backBtn = new JButton("BACK");
		backBtn.setBounds(50,300,100,40);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		
		 if(elementText.equals(deleteBtn.getText()))
		{
			Home hg = new Home();
			hg.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(logoutBtn.getText()))
		{
			Home hg = new Home();
			hg.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(backBtn.getText()))
		{
			EmployeeHome eh = new EmployeeHome(userId);
			eh.setVisible(true);
			this.setVisible(false);
		}
		
		
		else{}
		
	}
}
		
	