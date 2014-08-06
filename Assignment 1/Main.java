package assignment1;
import java.util.*;  
import java.util.concurrent.Executors;  
import java.util.concurrent.ExecutorService;  
import java.util.regex.*; 
public class Main { 
  
    public static void main(String[] args) { 
        // TODO Auto-generated method stub 
        int numofvehicle = 0;  
        int delaytime; 
        String input; 
        int delay = 0; 
        Scanner in = new Scanner(System.in);  
        input = in.nextLine(); 
        Pattern p = Pattern.compile("(\\d+)"); 
        Matcher m = p.matcher(input); 
          
        Pattern p2 = Pattern.compile("\\(([^)]+)\\)"); 
        Matcher m2 = p.matcher(input); 
  
        while(m.find()) { 
            numofvehicle = Integer.parseInt(m.group(1)); 
         
            } 
   
        while(m2.find()) { 
              
             delay = Integer.parseInt(m2.group(1)); 
           
              
            } 
       
         
            
          
        ExecutorService threadExecutor = Executors.newCachedThreadPool(); 
     /*   if (numofvehicle == 5) 
        { 
            int totalvehicle = numofvehicle * 4; 
            Vehicle[] Vehicles = new Vehicle[totalvehicle + 1]; 
            for (int i= 1; i <= totalvehicle; i++)    
            {    
                Vehicles[i] = new Vehicle(i);  
               threadExecutor.execute(Vehicles[i]); 
               if (i == 5 || i == 10 || i == 15) 
               { 
                   try{ 
                   Thread.sleep(delay * 1000); 
                   } 
                   catch(InterruptedException ex) { 
                       Thread.currentThread().interrupt(); 
                   } 
               } 
            }   
             threadExecutor.shutdown();  
              
        } 
        if (numofvehicle == 10) 
        { 
            int totalvehicle = numofvehicle * 2; 
            Vehicle[] Vehicles = new Vehicle[totalvehicle + 1]; 
            for (int i= 1; i <= totalvehicle; i++)    
            {    
                Vehicles[i] = new Vehicle(i);  
               threadExecutor.execute(Vehicles[i]);   
               if (i == 10) 
               { 
                   try{ 
                       Thread.sleep(delay * 1000); 
                       } 
                       catch(InterruptedException ex) { 
                           Thread.currentThread().interrupt(); 
                       } 
               } 
            } 
             threadExecutor.shutdown();  
        } */
       // if (numofvehicle == 20) { 
        Vehicle[] Vehicles = new Vehicle[numofvehicle+1];  
        for (int i= 1; i <= numofvehicle; i++)    
        {    
            Vehicles[i] = new Vehicle(i);  
           threadExecutor.execute(Vehicles[i]);  
              
        }    
        threadExecutor.shutdown();  
      // }  
    }  
} 