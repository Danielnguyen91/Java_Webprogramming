/**
 * Name: Toan Nguyen
 * Add country Class that allow to user to add country into database
 * include add name of country, flag, capital names and languages
 * capital names and languages can be empty or more than one
 * Name of Table in database: Country, Capital, Languages
 * 
 */
import java.sql.*;  
import java.util.ArrayList;
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

  

import javax.swing.*;  


public class Add_Country extends JFrame implements ActionListener  {
	private JLabel label_country;
	private JTextField country_text;
	private JLabel label_language;
	private JTextField language_text;
	private JTextField language_text2;
	private JTextField language_text3;
	private JTextField language_text4;
	private JLabel label_capital;
	private JTextField capital_text;
	private JTextField capital_text2;
	private JTextField capital_text3;
	private JTextField capital_text4;
	private JLabel image;
	private JButton image_button;
	private JPanel main_panel;
	private JButton ok_button;
	private static File selectedFile;
	private static Connection conn;
	private static Add_Country add;
	private static ArrayList<String> language_list = new ArrayList<String>();
	private static ArrayList<String> capital_list = new ArrayList<String>();
	
	public Add_Country() {
		 try
         {  
             Class.forName("com.mysql.jdbc.Driver");  
             System.out.println("Driver loaded");  
             String host = "jdbc:mysql://db.cs.wwu.edu/nguyen82_CS442";  
             String user = "nguyen82_writer";  
             String pass = "3hiGSkhw4";  
             conn = DriverManager.getConnection(host, user, pass);  
             System.out.println("Database connected");  
             
         }
		 catch (Exception io) {  
             System.out.println(io.getMessage());  
         }  
		main_panel = new JPanel();
		main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
		label_country = new JLabel("Country Name:");
		main_panel.add(label_country);
	    country_text = new JTextField(8);
		main_panel.add(country_text);
		image = new JLabel("Country Flag");
		main_panel.add(image);
		image_button = new JButton("Insert Flag");
		main_panel.add(image_button);
		image_button.addActionListener(this);
		label_language = new JLabel("Language (None or max is 4):");
		main_panel.add(label_language);
	    language_text = new JTextField(8);
		main_panel.add(language_text);
		language_text2 = new JTextField(8);
		main_panel.add(language_text2);
		language_text3 = new JTextField(8);
		main_panel.add(language_text3);
		language_text4 = new JTextField(8);
		main_panel.add(language_text4);
					
		label_capital = new JLabel("Capital (None or max is 4):");
		main_panel.add(label_capital);
	    capital_text = new JTextField(8);
		main_panel.add(capital_text);
		capital_text2 = new JTextField(8);
		main_panel.add(capital_text2);
	    capital_text3 = new JTextField(8);
		main_panel.add(capital_text3);
		capital_text4 = new JTextField(8);
		main_panel.add(capital_text4);
		ok_button = new JButton ("Ok");
		// action to add more country to the Database
		ok_button.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
				    
	               String country_name = country_text.getText();
	                
	                 String language = language_text.getText();
	                if (language != null && !language.isEmpty()) language_list.add(language);
	              
	                String language1 = language_text2.getText();
	                if (language1 != null && !language1.isEmpty()) language_list.add(language1);
	                String language2 = language_text3.getText();
	                if (language2 != null && !language2.isEmpty())language_list.add(language2);
	                String language3 = language_text4.getText();
	                if (language3 != null && !language3.isEmpty())language_list.add(language3);
	          
	                String capital = capital_text.getText();
	                if (capital != null && !capital.isEmpty())capital_list.add(capital);
	                String capital1 = capital_text2.getText();
	                if (capital1 != null && !capital1.isEmpty())capital_list.add(capital1);
	                String capital2 = capital_text3.getText();
	                if (capital2 != null && !capital2.isEmpty())capital_list.add(capital2);
	                String capital3 = capital_text4.getText();
	                if (capital3 != null && !capital3.isEmpty())capital_list.add(capital3);
	               for (String s : language_list)
	                {
	                	System.out.println(s);
	                }
	               for (String c : capital_list)
	                {
	                	System.out.println(c);
	                }
	               
	             
	                try {
	                	 String query = "insert into Country(Country_Name,Flag) values (?,?)";
	                	 String query1 = "insert into Languages(CountryFK_Name, Languages) values (?,?)";
	                	 String query2 = "insert into Capital(Country_FKName,Capital_name) values (?,?)";
	                	 PreparedStatement ps = conn.prepareStatement(query);
	                try {
	                
	                if (selectedFile != null) {
	                FileInputStream fis = new FileInputStream(selectedFile);
	                ps.setString(1, country_name);
	                ps.setBinaryStream(2, fis, (int) selectedFile.length());
	                }
	                else
	                {
	                	ps.setString(1, country_name);
	   	                ps.setBinaryStream(2, null);
	                }
	                conn.setAutoCommit(false);
	                
	               
	                ps.executeUpdate();
	              
	               
	                }
	                catch(FileNotFoundException io)
	                {
	                	io.printStackTrace();
	                }
	                PreparedStatement ps1 = conn.prepareStatement(query1);
	                if (language_list.isEmpty())
	                {
	                	ps1.setString(1, country_name);
	                	ps1.setString(2, null);
	                	ps1.addBatch();
	                	ps1.executeBatch();
	                }
	                else {
	                	for(String s: language_list)
	                	{
	                		ps1.setString(1, country_name);
	                		ps1.setString(2, s);
	                		ps1.addBatch();
	                	}
	                	  ps1.executeBatch();
	                }
	            
	              
	               
	                PreparedStatement ps2 = conn.prepareStatement(query2);
	                if (capital_list.isEmpty())
	                {
	                	ps2.setString(1, country_name);
	                	ps2.setString(2, null);
	                	ps2.addBatch();
	                	ps2.executeBatch();
	                }
	                else {
	                for(String c: capital_list)
	                {
	                	ps2.setString(1, country_name);
	                	ps2.setString(2, c);
	                	ps2.addBatch();
	                	
	                }
	                ps2.executeBatch();
	                }
	              
	             
	                conn.commit();
	                ps.close();
	                ps1.close();
	                ps2.close();
	                }
	                catch (SQLException io)
	                {
	                	io.printStackTrace();  
	                }
	               
	                Main frame = new Main();
	               // frame.getcombox();
				   
	                dispose();
	                
	            }
		});
		
		main_panel.add(ok_button);
		
		add(main_panel);
	}
	
	// insert the flags from file browser
	 public void actionPerformed(ActionEvent e) 
	 {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    fileChooser.showOpenDialog(null);
		    selectedFile = fileChooser.getSelectedFile();
		 	
	 }
	 
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 add = new Add_Country();
    	 add.setTitle("Add new Country");
    	 add.setSize(320,400);  
         add.setLocationRelativeTo(null); // Center the frame       
         add.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
         add.setVisible(true);      
         add.setAutoRequestFocus(true);    
	}

}
