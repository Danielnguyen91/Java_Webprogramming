package assignment1;
import java.util.*; 
public class Vehicle implements Runnable{ 
     private int ID;  
     private int Direct;  
     private int Crossing_time;  
     public static int vehicle_ON_VU = 0; 
     public static  int north_wait = 0; 
     public static int south_wait = 0; 
     public static int depart_index = 0; 
     private int north_consecutive = 0; 
     private int south_consecutive = 0; 
     private Queue<Integer> north_queue = new LinkedList<Integer>(); 
     private Queue<Integer> south_queue = new LinkedList<Integer>(); 
  
     private final static Random generator = new Random();  
     public Vehicle(int vehicle_id)  
     {  
            ID = vehicle_id; 
            Direct = generator.nextInt(2); 
     } 
     public void run() 
     { 
           
         OneVehicle( ID, Direct, Crossing_time);  
          
     } 
     public void OneVehicle(int vehicle_id,int direc, int time_to_cross) 
     { 
           ArriveSection(direc); 
      
           CrossSection(vehicle_id,direc,time_to_cross); 
           ExitSection(vehicle_id,direc); 
     } 
     public void ArriveSection(int direc) 
     { 
        // System.out.println("bus" + ID + "arrive from" + direc); 
    	 if (direc == 0)  
         { 
    	 synchronized (north_queue) {
        
             north_queue.add(ID); 
               
             while ((vehicle_ON_VU == 3) || (vehicle_ON_VU > 0 && direc != 0)) 
             { 
                  
                north_wait++; 
                 try { 
                 north_queue.wait(); 
                   // wait(); 
                 } 
                 catch (InterruptedException exception) {} 
                 north_wait--; 
                      
             } 
             vehicle_ON_VU++; 
             Direct = direc; 
           } 
    	}
    	 if (direc == 1) 
         { 
    	 synchronized (south_queue) {
         
             south_queue.add(ID); 
              
             while ((vehicle_ON_VU == 3) || (vehicle_ON_VU > 0 && direc != 1)) 
             { 
                south_wait++; 
                 try { 
                 south_queue.wait(); 
                  //  wait(); 
                 } 
                 catch (InterruptedException exception) {} 
                 south_wait--; 
             } 
             vehicle_ON_VU++; 
             Direct = direc; 
         } 
    	 }
      
     } 
     public  void CrossSection(int vehicle_id, int direc, int time_to_cross) 
     { 
         time_to_cross = 4000;  
         Crossing_time = time_to_cross;  
         for (Integer s: north_queue) 
         { 
             System.out.println("bus " + s + " arrive from" + direc); 
         } 
          for (Integer s: south_queue) 
         { 
             System.out.println("bus " + s + " arrive from" + direc); 
         } 
         System.out.println("bus " + ID + " cross the bridge" + direc); 
         try 
            {  
                Thread.sleep(Crossing_time);  
            }  
            catch ( InterruptedException exception )  
            {  
                 System.out.printf( "%s %s\n", vehicle_id,  
                            "terminated prematurely due to interruption" );  
            }  
     } 
     public void ExitSection(int vehicle_id, int direc)  
     { 
    	
    	 
    	 depart_index++; 
    	 if (direc == 0) 
         { 
    		
    	 synchronized (north_queue) {
    		 vehicle_ON_VU--; 
    	
             if (vehicle_ON_VU == 0) 
             { 
                 north_queue.notifyAll();  
             }
         } 
    	 }
    	 if (direc == 1) 
         { 
    	 synchronized (south_queue) {
    		 vehicle_ON_VU--; 
    		
        	if (vehicle_ON_VU == 0) 
             { 
                 south_queue.notifyAll();  
             }
         } 
    	 }
        // System.out.println("Bus" + ID + "exit the Section" + direc); 
      //   System.out.println("bus" + depart_index + " has arrive"); 
    	 System.out.println(vehicle_ON_VU);
     } 
      
}