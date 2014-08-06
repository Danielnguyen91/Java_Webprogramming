//package network;

import java.io.*;
import java.net.*;
import java.text.NumberFormat;
import java.util.*;
public class LoanServer {
	private int port;
	static final int DEFAULT_PORT = 5678;
	
	LoanServer(int port)
	{
		this.port = port;
	}
	
	/*running the server*/
	public void run()
	{
		try {
		ServerSocket ss = new ServerSocket(port);
		System.out.println("Waiting for clients...");
		Socket incoming  = ss.accept();
		BufferedReader inFromClient;
        inFromClient = new BufferedReader( new InputStreamReader(incoming.getInputStream() ) );
        PrintWriter outToClient = new PrintWriter(
           incoming.getOutputStream(), true );
        try {
        String name;
        name = inFromClient.readLine();
        String years;
        years = inFromClient.readLine();
        int y = Integer.parseInt(years);
        String amount;
        amount = inFromClient.readLine();
        double loan = Double.parseDouble(amount);
        String in;
        in = inFromClient.readLine();
        double interest = Double.parseDouble(in);
        double rate = interest / 1200;
        double monthly;
        int nofpay = 12 * y;
        monthly = (rate + (rate / (Math.pow(rate + 1, nofpay) - 1))) * loan;
        double monthly_round = (double) Math.round(monthly * 100.0)/ 100.0;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        
        System.out.print("Account: ");
        System.out.println(name);
        System.out.print("Monthly Payment: ");
        System.out.println(currencyFormat.format(monthly_round));
       
        
        outToClient.println(currencyFormat.format(monthly_round));
        }
        catch (NumberFormatException ie)
        {
        	System.out.println("The input data is invalid");
        	ss.close();
        }
		}
		catch(IOException iox)
		{
			System.out.println( iox );
	         iox.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = DEFAULT_PORT;
		if (args.length > 0 ) {
	         port = Integer.parseInt( args[0] );
	      }
		  LoanServer addserver = new LoanServer(port);
		  addserver.run();
	}

}
