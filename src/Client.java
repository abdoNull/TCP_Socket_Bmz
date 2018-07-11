
import java.io.BufferedReader ;  
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter ;
import java.net.Socket ;
import java.util.Scanner ;
 
public class Client{

   private static Scanner sc;

public static void main (String [] args) {
   
      Socket  socket;
      final BufferedReader in;
      final PrintWriter out;
      sc = new Scanner (System.in);

      try {
         /*
         * Server informations (port and IP address or host name ) 
         * 127.0.0.1 is the host local address
         */
    	  socket = new Socket ("169.254.229.37", 8888);
    	  System.out.println("Connect");
          out = new PrintWriter (socket.getOutputStream ());
          in = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
 
         Thread send = new Thread (new Runnable () {
             String msg;
                @Override
              public void run () { 	
                while (true) {
                  msg =  sc.nextLine();
                  out.println (msg);
                  out.flush ();
                  if (msg.equalsIgnoreCase("fermer")) {
                	
				
                	 System.out.println("Déconnect");
                	 
                	 break ; 

					
				}
          
                   
                }
             }
         });
         send.start ();
      //   socket.close();
     Thread receive = new Thread (new Runnable () {
            String msg;
            @Override
            public void run () {
            	while (true)    {
              
                 try  {
              
                     msg = in.readLine();
              
               } catch (IOException e) {
                   e.printStackTrace ();
               }
               System.out.println ("Server Sys >> " + msg);
               if (msg.equalsIgnoreCase("fermer")) {
             	  System.out.println("Déconnect");
             
                         break ;          	 
					
				}
            	 } 
           }
        });
        receive.start ();
       
 
      } catch (IOException e) {
           e.printStackTrace ();
      }
  }
}