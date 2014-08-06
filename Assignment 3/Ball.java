import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Ball extends JPanel implements Runnable {
	 private float ball_x = 385;   
     private float ball_y = 50;  
     private float radius = 15;  
	 private int ball; 
	 private int start_y = 100;
	  private final static Random generator = new Random(); 
	 mainpanel pa = new mainpanel();
	 public Ball()
	 {
		 
	 }
	 public Ball(int ballnum) 
	 { 
	        ball = ballnum; 
	 } 
	 protected void  paintComponent(Graphics g) 
     { 
	
		
		//setBounds(0,0,20,20);
		 super.paintComponent(g); 
        for (int i = 1; i <= ball; i++) 
        { 
            g.setColor(Color.RED); 
            g.fillOval((int)ball_x, (int)ball_y, (int)radius, (int)radius); 
          //  ball_x = ball_x + 20; 
        } 
        
	}
	public void run()
	{
		 while (ball_y < pa.getPanel_y() + 65) 
         { 
         	int direction = generator.nextInt(2);
             ball_y = ball_y + 5; 
             if (ball_y == start_y - 10 && start_y < pa.getPanel_y())
             {
             	if (direction == 0)
             	{
             		ball_x = ball_x + 20;
             	}
             	else ball_x = ball_x - 20;
             	start_y = start_y + 40;
             }
             
            System.out.println(ball_y);
            System.out.println(start_y);
         	 repaint(); 
            
             try
             { 
                 Thread.sleep(50); 
             } 
              catch (InterruptedException ex){} 
               
               
           
           
         } 
	}
    public void start() 
    { 
            
        Thread th = new Thread(this); 
       th.setPriority(Thread.NORM_PRIORITY); 
        th.start(); 
          
    } 

}
