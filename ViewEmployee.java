import javax.swing.JButton;
import javax.swing.JTextField;

public class ViewEmployee extends javax.swing.JFrame implements java.awt.event.ActionListener
{
  javax.swing.JLabel userLabel;
  javax.swing.JLabel eNameLabel;
  javax.swing.JLabel phoneLabel;
  javax.swing.JLabel roleLabel;
  javax.swing.JLabel salaryLabel;
  JTextField userTF;
  JTextField phoneTF1;
  JTextField phoneTF2;
  JTextField eNameTF;
  
  public ViewEmployee(String paramString)
  {
    super("ViewEmployee");
    
    setSize(800, 500);
    setDefaultCloseOperation(3);
    setLocationRelativeTo(null);
    userId = paramString;
    
    panel = new javax.swing.JPanel();
    panel.setLayout(null);
    
    logoutBtn = new JButton("Logout");
    logoutBtn.setBounds(600, 50, 100, 30);
    logoutBtn.addActionListener(this);
    panel.add(logoutBtn);
    





    userLabel = new javax.swing.JLabel("User ID : ");
    userLabel.setBounds(250, 70, 120, 30);
    panel.add(userLabel);
    
    eNameLabel = new javax.swing.JLabel("Customer Name : ");
    eNameLabel.setBounds(250, 120, 120, 30);
    panel.add(eNameLabel);
    
    phoneLabel = new javax.swing.JLabel("Phone No. : ");
    phoneLabel.setBounds(250, 170, 120, 30);
    panel.add(phoneLabel);
    
    roleLabel = new javax.swing.JLabel("Role : ");
    roleLabel.setBounds(250, 220, 120, 30);
    panel.add(roleLabel);
    
    salaryLabel = new javax.swing.JLabel("Salary : ");
    salaryLabel.setBounds(250, 270, 120, 30);
    panel.add(salaryLabel);
    
    userTF = new JTextField(paramString);
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
    
    updateBtn = new JButton("Update");
    updateBtn.setBounds(200, 400, 120, 30);
    updateBtn.setEnabled(false);
    updateBtn.addActionListener(this);
    panel.add(updateBtn);
    
    delBtn = new JButton("Delete");
    delBtn.setBounds(350, 400, 120, 30);
    delBtn.setEnabled(false);
    delBtn.addActionListener(this);
    panel.add(delBtn);
    
    backBtn = new JButton("Back");
    backBtn.setBounds(500, 400, 120, 30);
    backBtn.addActionListener(this);
    panel.add(backBtn);
    









    add(panel);
  }
  
  public void actionPerformed(java.awt.event.ActionEvent paramActionEvent)
  {
    String str = paramActionEvent.getActionCommand();
    Object localObject;
    if (str.equals(backBtn.getText()))
    {
      localObject = new EmployeeHome(userId);
      ((EmployeeHome)localObject).setVisible(true);
      setVisible(false);












    }
    else if (str.equals(logoutBtn.getText()))
    {
      localObject = new Home();
      ((Home)localObject).setVisible(true);
      setVisible(false);
    }
    else if (str.equals(loadBtn.getText()))
    {
      loadFromDB();
    }
    else if (str.equals(updateBtn.getText()))
    {
      updateInDB();
    }
    else if (str.equals(delBtn.getText()))
    {

      deleteFromDB();
      localObject = new Home();
      ((Home)localObject).setVisible(true);
      setVisible(false);
    }
  }
  
  JTextField roleTF;
  JTextField salaryTF;
  JButton refreshBtn;
  JButton loadBtn;
  JButton updateBtn;
  
  public void loadFromDB() {
    String str1 = "SELECT `employeeName`, `phoneNumber`, `role`, `salary` FROM `employee` WHERE `userId`='" + userId + "';";
    java.sql.Connection localConnection = null;
    java.sql.Statement localStatement = null;
    java.sql.ResultSet localResultSet = null;
    System.out.println(str1);
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("driver loaded");
      localConnection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
      System.out.println("connection done");
      localStatement = localConnection.createStatement();
      System.out.println("statement created");
      localResultSet = localStatement.executeQuery(str1);
      System.out.println("results received");
      
      int i = 0;
      String str2 = null;
      String str3 = null;
      String str4 = null;
      double d = 0.0D;
      String str5 = Double.toString(d);
      
      while (localResultSet.next())
      {
        str2 = localResultSet.getString("employeeName");
        str3 = localResultSet.getString("phoneNumber");
        str4 = localResultSet.getString("role");
        str5 = localResultSet.getString("salary");
        i = 1;
        
        eNameTF.setText(str2);
        phoneTF1.setText("+880");
        phoneTF2.setText(str3.substring(4, 14));
        roleTF.setText(str4);
        salaryTF.setText(str5);
        //userTF.setEnabled(false);
        updateBtn.setEnabled(true);
        delBtn.setEnabled(true);
      }
      if (i == 0)
      {
        eNameTF.setText("");
        phoneTF1.setText("");
        phoneTF2.setText("");
        roleTF.setText("");
        salaryTF.setText("");
        javax.swing.JOptionPane.showMessageDialog(this, "Invalid ID");
      }
      return;
    }
    catch (Exception localException2) {
      System.out.println("Exception : " + localException2.getMessage());
    }
    finally
    {
      try
      {
        if (localResultSet != null) {
          localResultSet.close();
        }
        if (localStatement != null) {
          localStatement.close();
        }
        if (localConnection != null)
          localConnection.close();
      } catch (Exception localException4) {} } }
  
  JButton delBtn;
  JButton backBtn;
  JButton logoutBtn;
  javax.swing.JPanel panel;
  String userId;
  public void updateInDB() { String str1 = userId;
    String str2 = eNameTF.getText();
    String str3 = phoneTF1.getText() + phoneTF2.getText();
    String str4 = roleTF.getText();
    String str5 = salaryTF.getText();
    String str6 = "UPDATE employee SET employeeName='" + str2 + "', phoneNumber = '" + str3 + "', role = '" + str4 + ",'salary = '" + str5 + "' WHERE userId='" + str1 + "'";
    java.sql.Connection localConnection = null;
    java.sql.Statement localStatement = null;
    System.out.println(str6);
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      localConnection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/m1", "root", "");
      localStatement = localConnection.createStatement();
      localStatement.executeUpdate(str6);
      localStatement.close();
      localConnection.close();
      javax.swing.JOptionPane.showMessageDialog(this, "Success !!!");
      
      updateBtn.setEnabled(false);
      delBtn.setEnabled(false);
      userTF.setEnabled(true);
      userTF.setText("");
      eNameTF.setText("");
      phoneTF1.setText("");
      phoneTF2.setText("");
      roleTF.setText("");
      salaryTF.setText("");

    }
    catch (Exception localException)
    {
      System.out.println(localException.getMessage());
      javax.swing.JOptionPane.showMessageDialog(this, "Oops !!!");
    }
  }
  
  public void deleteFromDB()
  {
    String str1 = userId;
    String str2 = "DELETE from employee WHERE userId='" + str1 + "';";
    String str3 = "DELETE from login WHERE userId='" + str1 + "';";
    System.out.println(str2);
    System.out.println(str3);
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      java.sql.Connection localConnection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/m1", "root", "");
      java.sql.Statement localStatement = localConnection.createStatement();
      localStatement.execute(str2);
      localStatement.execute(str3);
      localStatement.close();
      localConnection.close();
      javax.swing.JOptionPane.showMessageDialog(this, "Success !!!");






    }
    catch (Exception localException)
    {






      javax.swing.JOptionPane.showMessageDialog(this, "Oops !!!");
    }
  }
}
