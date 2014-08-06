//package network;
import java.io.*;
import java.net.*;
import java.text.NumberFormat;
import java.util.*;
public class LoanClient {
	private InetAddress host;
	   private int port;
	   
	   // This is not a reserved port number 
	   static final int DEFAULT_PORT = 5678;
	   public LoanClient(InetAddress host, int port)
	   {
		   this.host = host;
		   this.port = port;
	   }
	   public void run()
	   {
		   String user_name;
		   String loan_amount;
		   String nof;
		   String interest;
		 
		   try {
		   Socket client = new Socket(host, port);
		   BufferedReader fromServer;
		   fromServer = new BufferedReader( new InputStreamReader(
		            client.getInputStream() ) );
	         PrintWriter toServer = new PrintWriter(
	            client.getOutputStream(), true );
	         BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	         String sentence;
	        
	         System.out.print("Name: ");
	         user_name = stdIn.readLine();
	         System.out.print("Number of years: ");
	         nof = stdIn.readLine(); 
	         
	         
	         System.out.print("Loan Amount: ");
	         loan_amount = stdIn.readLine();
	       
	         System.out.print("Annual Interest Rate: ");
	         interest = stdIn.readLine();
	         
	         toServer.println(user_name);
	         try {
	        	 int year = Integer.parseInt(nof);
	        	 double valid_loan = Double.parseDouble(loan_amount);
	        	 double valid_inter = Double.parseDouble(interest);
	         if (year > 0 && year < 100) {
	         toServer.println(nof);
	         }
	         else 
	         {
	        	 System.out.print("please enter the valid year: ");
	        	 nof = stdIn.readLine();
	        	 toServer.println(nof);
	         }
	         if (valid_loan > 0)
	         {
	        	 toServer.println(loan_amount);
	         }
	         else
	         {
	        	 System.out.print("please enter the valid amount you want to borrow: ");
	        	 loan_amount = stdIn.readLine();
	        	 valid_loan = Double.parseDouble(loan_amount);
	        	 toServer.println(loan_amount);
	         }
	         if (valid_inter > 0 && valid_inter < 100)
	         {
	        	 toServer.println(interest + '\n');
	         }
	         else
	         {
	        	 System.out.print("please enter the valid interest: ");
	        	 interest = stdIn.readLine();
	        	 valid_inter = Double.parseDouble(interest);
	        	 toServer.println(interest + '\n');
	         }
	        
	         NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
	         System.out.println("Dear Mr/Mrs "+ user_name + ",");
	         System.out.println("Your loan request for " + currencyFormat.format(valid_loan) + " for " + nof +
	        		 " years at " + interest
	         + "% annual interest has been approved");
	         System.out.println("it will cost you " + fromServer.readLine() + " every month");
	         System.out.println("Thank you");
	         }
	         catch (NumberFormatException e)
	         {
	        	 System.out.println("Enter the valid number only");
	        	 client.close();
	         }
	        
	        
		   }
		   catch( IOException iox ) {
		         System.out.println( iox );
		         iox.printStackTrace();
		      }
		   
	   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		InetAddress host = InetAddress.getLocalHost();
		int port = DEFAULT_PORT;
		if (args.length > 0) 
		{
			port = Integer.parseInt(args[0]);
		}
		 if ( args.length > 1 ) {
	            host = InetAddress.getByName( args[1] );
	         }
		 LoanClient  addClient = new LoanClient(host,port);
		 addClient.run();
		}
		 catch ( UnknownHostException uhx ) {
	         System.out.println( uhx );
	         uhx.printStackTrace();
	      }
	}

}
