import java.awt.Color;   
import java.awt.Graphics;   
      
import java.awt.event.*; 

import javax.swing.*; 

import java.util.List;
import java.util.Random; 
import java.util.ArrayList;; 
      
public class Mainpanel extends JPanel  
    {    
        private int num;    
        private int number_ball;  
        private int ball_free; 
        public static int start_y = 100;  
        private static int  panel_x = 300;   
        private static int panel_y = 100;     
        private int diameter = 20;    
        private static int last_x = 0;     
        ArrayList<Balls> list_ball = new ArrayList<Balls>(); 
        ArrayList<Balls> pair_ball = new ArrayList<Balls>();
        private final static Random generator = new Random();  
        private static int randomDelayedStart;
        private static int[] array;
    
        private int m_interval = 100; 
        private Timer m_timer; 
       
        public Mainpanel()  
        {  
                
        }  
        public Mainpanel(int number)    
        {    
            num = number;    
        }    
        public Mainpanel(int number, int ball)    
        {    
            num = number;    
            number_ball = ball;   
            for (int i = 1; i <= number_ball; i++)    
            {    
                int randomDelayedStart = generator.nextInt(70); 
                list_ball.add(new Balls(randomDelayedStart));  
                   
             }    
            m_timer = new Timer(m_interval, new TimerAction()); 
        }    
        public Mainpanel(int number, int ball, int free_ball)    
        {    
            num = number;    
            number_ball = ball;   
            ball_free = free_ball; 
            for (int i = 0; i < number_ball; i = i + ball_free)    
            {    
              // int randomDelayedStart = generator.nextInt(70); 
            	randomDelayedStart =  randomDelayedStart + 10; 
            	for (int m = 0; m < ball_free; m++)
            	{
            	    
            	    array = new int[] {305,345,385,425}; 
            		int randomlocation = array[generator.nextInt(array.length)];
            		list_ball.add(new Balls(randomDelayedStart, randomlocation));
            	}
            
            }
            m_timer = new Timer(m_interval, new TimerAction()); 
        } 
        public int getPanel_y()  
        {  
            return panel_y;  
        }  
        public int getlast_x() 
        { 
            return last_x; 
        } 
        public void start() 
        { 
          
            m_timer.start(); 
          
        } 
        @Override
         protected void  paintComponent(Graphics g)    
            {    
                int start_y = 100;   
                panel_x = 300;    
                panel_y = 100;    
                diameter = 20;    
                last_x = 0;   
               super.paintComponent(g);    
                
               if (num % 2 == 0) {   
               for (int i = 1; i <= num; i++)   
                {   
                    if ((i % 2) != 0) {   
                    for (int k = 1; k <= num; k++)   
                    {   
                          
                        g.setColor(Color.BLUE);   
                        g.fillOval(panel_x,panel_y, diameter,diameter);   
                          
                          
                        panel_x = panel_x + 40;   
                    }   
                    }   
                    else if ((i % 2) == 0)   
                    {   
                        for (int k = 1; k <= num + 1 ; k++)   
                        {   
                              
                            g.setColor(Color.BLUE);   
                            g.fillOval(panel_x - 20,panel_y, diameter,diameter);   
                              
                              
                            panel_x = panel_x + 40;   
                        }   
                    }   
                    panel_y = panel_y + 40;   
                    panel_x = 300;   
                }   
               }   
               else if (num % 2 != 0)   
               {   
                   for (int i = 1; i <= num  ; i++)   
                    {   
                        if ((i % 2) != 0) {   
                        for (int k = 1; k <= num  ; k++)   
                        {   
                              
                            g.setColor(Color.BLUE);   
                            g.fillOval(panel_x,panel_y, diameter,diameter);   
                              
                              
                            panel_x = panel_x + 40;   
                        }   
                        }   
                        else if ((i % 2) == 0)   
                        {   
                            for (int k = 1; k <= num + 1; k++)   
                            {   
                                  
                                g.setColor(Color.BLUE);   
                                g.fillOval(panel_x - 20,panel_y, diameter,diameter);  
                                panel_x = panel_x + 40;   
                            }   
                        }   
                        panel_y = panel_y + 40;   
                        panel_x = 300;   
                    }   
               }   
                     
                      
                for (int n = 40; n < panel_y - 40; n = n + 40) {    
                    if (num % 2 == 0) {  
                   g.drawLine(panel_x - 50  + n, panel_y - 10, panel_x - 50   + n, panel_y + 80);    
                   g.drawLine(panel_x, panel_y + 80, panel_x - 50  + n, panel_y + 80);   
                   last_x = panel_x - 50 + n; }  
                    else if (num % 2 != 0)  
                    {  
                           g.drawLine(panel_x - 30  + n, panel_y - 10, panel_x - 30   + n, panel_y + 80);    
                           g.drawLine(panel_x, panel_y + 80, panel_x - 30  + n, panel_y + 80);   
                           last_x = panel_x - 30 + n;  
                    }  
                     
                }    
                  
             
                   
                  
            
        
        for (int i = 0; i< list_ball.size(); i++) 
        { 
            list_ball.get(i).draw(g); 
              
        } 
                  
            } 
    
        class TimerAction implements ActionListener {    
            public void actionPerformed(ActionEvent e) { 
            	
                  for (Balls p: list_ball) 
                  { 
                	 
                		  p.move(); 
                	  
                		  p.decreaseDelay(); 
                		  repaint();
                	  
                      
                  } 
                      
                  
                  
                  
                  
            } 
        } 
    }