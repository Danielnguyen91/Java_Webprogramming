import java.awt.*;      
import java.awt.event.ActionEvent;     
import java.awt.event.ActionListener;      
import java.io.IOException;  
    
import javax.swing.*;      
public class Main extends JFrame {     
    private String num_slots;    
    private String num_balls;    
    private String ball_free;    
    private JButton Display;    
    private JButton Start;    
    private JPanel textpanel;    
    private JPanel mainpanel;     
    public Main(){      
    textpanel = new JPanel();      
    textpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10 ,20));      
    textpanel.add(new JLabel("Number of Slots"));      
    final  JTextField text1 = new JTextField(10);     
    textpanel.add(text1);      
    textpanel.add(new JLabel("Number of Balls"));      
    final JTextField text2 = new JTextField(10);     
    textpanel.add(text2);      
    textpanel.add(new JLabel("How many balls can be freed"));      
    final JTextField text3 = new JTextField(10);     
    textpanel.add(text3);     
    Display = new JButton("Display");     
    textpanel.add(Display);     
    Start = new JButton("Start");    
    textpanel.add(Start);     
             
          
    // Create panel p2 to hold a text field and p1     
    mainpanel = new JPanel(new BorderLayout());     
     
    mainpanel.add(textpanel, BorderLayout.PAGE_START);     
    add(mainpanel, BorderLayout.CENTER);    
     
          
    Display.addActionListener(new ActionListener()    
    {    
        @Override
        public void actionPerformed(ActionEvent e) {    
            if (e.getSource() == Display) {    
            num_slots = text1.getText();    
            int slots = Integer.parseInt(num_slots);    
            num_balls = text2.getText();    
            int balls = Integer.parseInt(num_balls);   
            ball_free = text3.getText(); 
            if (ball_free != null && !ball_free.isEmpty()) { 
                int free = Integer.parseInt(ball_free); 
                 Mainpanel pa = new Mainpanel(slots,balls,free); 
                 mainpanel.add(pa,BorderLayout.CENTER);     
            } 
            else {  
                Mainpanel pa = new Mainpanel(slots,balls);  
                 mainpanel.add(pa,BorderLayout.CENTER);  
                 } 
        
          
                
            
         
            
           mainpanel.revalidate();   
                       
                    
    }    
        }    
                
    });    
   Start.addActionListener(new ActionListener()    
    {   
          public void actionPerformed(ActionEvent e) {    
              if (e.getSource() == Start)   
              {   
                    num_slots = text1.getText();    
                    int slots = Integer.parseInt(num_slots);    
                    num_balls = text2.getText();    
                    int balls = Integer.parseInt(num_balls);    
                     
                    ball_free = text3.getText(); 
                    if (ball_free != null && !ball_free.isEmpty()) { 
                        int free = Integer.parseInt(ball_free); 
                         Mainpanel pa = new Mainpanel(slots,balls,free); 
                         mainpanel.add(pa,BorderLayout.CENTER);  
                         pa.start(); 
                    } 
                    else {  
                        Mainpanel pa = new Mainpanel(slots,balls);  
                         mainpanel.add(pa,BorderLayout.CENTER);  
                        pa.start();} 
                           
                        
                        
                    mainpanel.revalidate();   
                    mainpanel.repaint();   
              }   
          }   
    });   
    }    
           
        
    public static void main(String[] args) {      
        // TODO Auto-generated method stub      
        Main frame = new Main();      
         frame.setTitle("The Galton board");      
            frame.setSize(1000, 800);      
            frame.setLocationRelativeTo(null); // Center the frame      
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
            frame.setVisible(true);     
            frame.setAutoRequestFocus(true);   
        
      
    }      
            
}