/**
 * Name: Toan Nguyen
 * Main Class contain the Country interface, that allow to choose the name of country from combobox
 * and display to the table
 * Name of Table in database: Country, Capital, Languages
 * 
 */
import java.sql.*;  
import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.image.BufferedImage; 
import java.io.ByteArrayInputStream; 
import java.io.IOException; 
  
import javax.imageio.ImageIO; 
import javax.swing.*;  
import javax.swing.table.DefaultTableModel; 
public class Main extends JFrame implements ActionListener {  
     private static JComboBox countrybox;  
     private DefaultComboBoxModel boxmodel;
     private static Vector<String> country = new Vector<String>();
     private JPanel topPanel;  
     private static Blob image;  
     private byte[] imgData = null; 
     private Statement stmt;  
     private JLabel label; 
     private JTable table; 
     private static String capital;
     private DefaultTableModel model; 
     public ResultSet initset;
     public Main()  
     {  
           topPanel = new JPanel();  
           topPanel.setLayout(null); 
            
    
            // Create a combobox  and add country name from database to combobox
         
           countrybox = new JComboBox();  
           boxmodel = new DefaultComboBoxModel(country); 
           countrybox.setModel(boxmodel);
           getcombox();
           countrybox.setBounds( 20, 35, 260, 20 );  
           topPanel.add(countrybox);  
           label = new JLabel(""); 
           label.setBounds(150,50,100,200); 
           topPanel.add(label);  
            String[] ColumnNames = {"Country", "Flag", "Capital", "Language" }; 
            Object[][] data = { 
                    { "", "", "", "" }}; 
            model = new DefaultTableModel (data,ColumnNames)  {
                public Class getColumnClass(int columnIndex) {
                    return String.class;
                  }
                };
            table = new JTable(model); 
            table.setEnabled(false);
            table.setRowHeight(0, 75);
          
            table.setPreferredScrollableViewportSize(table.getPreferredSize());   
            table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
            JScrollPane scrollPane = new JScrollPane(table); 
            scrollPane.setBounds(20, 100, 500, 100); 
            topPanel.add(scrollPane); 
            add(topPanel,BorderLayout.CENTER); 
            
              
              }  
     
     public void getcombox()
     {
    	
    	try
         {  
    		 
             Class.forName("com.mysql.jdbc.Driver");  
             System.out.println("Driver loaded");  
             String host = "jdbc:mysql://db.cs.wwu.edu/nguyen82_CS442";  
             String user = "nguyen82_writer";  
             String pass = "3hiGSkhw4";  
             Connection con = DriverManager.getConnection(host, user, pass);  
             System.out.println("Database connected");  
             stmt = con.createStatement();  
             String query = "Select Country_name from Country";  
             initset = stmt.executeQuery(query);  
             
             while (initset.next())  
             {  
                 String Country = initset.getString(1); 
                 country.add(Country);
                 
             }  
              country.add("New Entry");
              
            //countrybox.addItem("New Entry");
             countrybox.addActionListener(this); 
         }  
         catch (Exception err) {  
             System.out.println(err.getMessage());  
         } 
    	
    	
     }
     // select name from combobox and display its to the table
     public void actionPerformed(ActionEvent e)  
     {  
         JComboBox comboBox = (JComboBox)e.getSource();  
         String select = (String)comboBox.getSelectedItem();  
         try {  
         String query = "Select d.Flag,d.Country_Name AS 'Country', GROUP_CONCAT(p.Languages SEPARATOR '\n') AS 'Languages'"
         		+ " From Country d Left Join Languages p on p.CountryFK_Name = d.Country_Name Where d.Country_Name = '"+select+"' Group by d.Country_Name ";
        /* String query = "Select d.Country_Name AS 'Country', GROUP_CONCAT(c.Capital_Name SEPARATOR '\n') AS 'Capital'," 
         		+ "GROUP_CONCAT(p.Languages SEPARATOR '\n') AS 'Languages'"
              	+ " From Country d left join Capital c on d.Country_id = c.country_id "
              	+ "Left Join Languages p on p.country_id = d.Country_id   Where d.Country_Name = '"+select+"'";*/
        /* String query = "Select d.Flag, d.Country_Name AS 'Country', GROUP_CONCAT(p.Capital_Name SEPARATOR '\n') AS 'Capital'," 
          		+ "GROUP_CONCAT(p.Languages SEPARATOR '\n') AS 'Languages'"
               	+ " From Country d left join Languages p on p.CountryFK_Name = d.Country_Name "
               	+ "  Where d.Country_Name = '"+select+"' Group by d.Country_Name";*/
         	
         ResultSet rset = stmt.executeQuery(query);  
         while (rset.next())  
         {  
          
             String country = rset.getString("Country");  
             String language = rset.getString("Languages"); 
            image = rset.getBlob("Flag"); 
            if (image != null) {
            imgData = image.getBytes(1,(int)image.length()); 
             try { 
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgData)); 
                int type = img.getType() == 0 ? BufferedImage.TYPE_INT_ARGB: img.getType();
                BufferedImage resize = resizeImage(img,type);
                label.setText(null);
               
                label.setIcon(new ImageIcon(resize)); 
               
               
                    
            } catch (IOException e1) { 
                  
                e1.printStackTrace(); 
            } 
            }
            else 
            	{
            	 label.setIcon(null);
            	 label.setText("Not available" );
            	}
             SetData(country,0,0);
            
             SetData(language,0,3);
            
         }
         rset.close();
         
         }  
          catch (SQLException ex) {  
              ex.printStackTrace();  
         } 
         
         try {
        	  String query2 = "Select d.Country_Name AS 'Country', GROUP_CONCAT(c.Capital_Name SEPARATOR '\n') AS 'Capital'"
                		+ " From Country d Left Join Capital c on c.Country_FKName = d.Country_Name Where d.Country_Name = '"+select+"' Group by d.Country_Name ";
        	  ResultSet rset2 = stmt.executeQuery(query2);
     	     
		        while (rset2.next())
		        {
		        	capital  = rset2.getString("Capital");
		        	if (capital == "" || capital == null)
		        	{
		        		capital = "No Official Capital"; 
		        	}
		        	SetData(capital,0,2);
		        	System.out.println(capital);  
		        }
		        rset2.close();
         }
         catch (SQLException ex) {  
             ex.printStackTrace();  
        } 
        
         if (select == "New Entry")
         {
        	 Add_Country add = new Add_Country();
        	 add.setTitle("Add new Country");
        	 add.setSize(320,400);  
             add.setLocationRelativeTo(null); // Center the frame            
             add.setVisible(true);      
             add.setAutoRequestFocus(true);    
         }
     }  
     /* resize image to fix with the table */
     private static BufferedImage resizeImage(BufferedImage orig, int type)
     {
    	 BufferedImage resize = new BufferedImage(105,50, type);
    	 Graphics2D g = resize.createGraphics();
    	 g.drawImage(orig, 0, 0, 105, 50, null);
    	 return resize;
     }
     public void SetData(Object obj, int row_index, int col_index){
    	  table.getModel().setValueAt(obj,row_index,col_index);
    	
    	  }
    	
        
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        Main country = new Main();  
        country.setTitle("National Country");  
        country.setSize(640,480);  
        country.setLocationRelativeTo(null); // Center the frame       
        country.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        country.setVisible(true);      
        country.setAutoRequestFocus(true);    
       
    }  
    
}