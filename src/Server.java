
import java.io.BufferedReader ;
import java.io.IOException;
import java.io.InputStreamReader ;
import java.io.PrintWriter ;
import java.net.ServerSocket ;
import java.net.Socket ;
import java.util.Scanner ;
 
public class Server {

   private static ServerSocket ss; 
    private static Scanner sc;

public static void main (String [] test) {
 
      
    sc = new Scanner (System.in);
 
     try {
     ss = new ServerSocket (5555);
     System.out.println("Server is listenning... ");
     Socket  s  = ss.accept();
     PrintWriter out = new PrintWriter (s.getOutputStream ());  // Copy  //Send 
     BufferedReader  in = new BufferedReader (new InputStreamReader (s.getInputStream ())); // Read Group Group  and Speed 
       
     
       // Server 
         Thread sending = new Thread (new Runnable () {
          String msg;
          @Override
          public void run () {
             while (true) {
                 msg =sc.nextLine();
                out.println (msg);
                out.flush ();
                if (msg.equalsIgnoreCase("fermer")) {
              	  System.out.println("Déconnect");
              	  try {
              		  out.close();
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              	  break ; 
					
				}
             }
          }
       }); 
       
       
       
       sending.start ();
 
       Thread receive = new Thread (new Runnable () {
          String msg = "";
          @Override
          public void run () {
        	  while (true)   {
        		  try  {
      	 
                 msg = in.readLine();
             

             } catch (IOException e) {
                  e.printStackTrace ();
             }
	             System.out.println ("Client Says >>  " + msg) ;

	             if (msg.equalsIgnoreCase("fermer")) {
	               	  System.out.println("Déconnect");
	               	     break ; 
						
					}
         
        	  }  }
      });
      receive.start ();
      
     
      
      } catch (IOException e) {
         e.printStackTrace ();
      }
     
   
   }
}


