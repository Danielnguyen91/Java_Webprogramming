import java.awt.geom.Ellipse2D; 
import java.util.Random; 
import java.awt.*; 
  
public class Balls { 
    private Ellipse2D.Double thisBall; 
    private int Ball_x; 
    private int Ball_y; 
    public int radius; 
    public  int start_y;  
    private static  int panel_y;
    private int frame_y;
    private int randomx; 
    private final static Random generator = new Random();   
    boolean draw = false; 
    boolean down = false; 
    private static int slot1 = 0;
    private static int slot2 = 0;
    private static int slot3 = 0;
    private int DelayedStart; 
    private int x_dimension; 
    private int x_last; 
    Mainpanel pa = new Mainpanel(); 
    public Balls(int DelayStart) 
    { 
        start_y = 100; 
        Ball_x = 305; 
        Ball_y = 50; 
        radius = 15; 
        this.DelayedStart = DelayStart; 
        panel_y = pa.getPanel_y();
        frame_y = pa.getPanel_y();
        x_dimension = 305; 
        x_last = pa.getlast_x();
       
    } 
    public Balls(int DelayStart, int randomlocation) 
    { 
        start_y = 100; 
        Ball_x = randomlocation; 
        Ball_y = 50; 
        radius = 15; 
        this.DelayedStart = DelayStart; 
        x_dimension = 305; 
        panel_y = pa.getPanel_y();
        frame_y = pa.getPanel_y();
        x_last = pa.getlast_x();
    } 
    public void draw(Graphics g) 
    { 
        if (draw) { 
        g.setColor(Color.RED); 
        g.fillOval(Ball_x, Ball_y, radius, radius); 
        } 
    } 
    public int get_y() 
    { 
        return Ball_y; 
    } 
    public void move() 
    { 
        if (draw) { 
           
         if (Ball_y < panel_y + 65)   
         {   
              
             Ball_y = Ball_y + 5;      
             if (Ball_y == panel_y + 65) panel_y = panel_y - 15;
         } 
             if (Ball_y == start_y - 10 && start_y < frame_y)  
             {  
                 int direction = generator.nextInt(2);  
                 if (direction == 0)  
                 {  
                	 if (Ball_x > x_dimension)
                     Ball_x = Ball_x - 20; 
                	 else
                		 Ball_x = Ball_x + 20;
                 }  
                 if (direction == 1 && Ball_x < x_last) 
                 {	
                	 Ball_x = Ball_x + 20; 
                 }  
                 start_y = start_y + 40;  
             }  
            
             System.out.println(Ball_y);
             System.out.println(panel_y); 
        
         } 
          
          
      
      
    } 
      public void decreaseDelay() { 
          if (DelayedStart <= 0) { 
              draw = true; 
          } else { 
              DelayedStart -= 1; 
          } 
      } 
      
} 