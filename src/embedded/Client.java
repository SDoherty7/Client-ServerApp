/* The Client Class is heavily expanded upon the sample written by Derek Molloy 
 * for the EE402 Module in Dublin City University
 * See: ee402.eeng.dcu.ie
 */

package embedded;

import java.net.*;
import java.io.*;

public class Client {
	
	private static int portNumber = 5050;
    private Socket socket = null;
    private ObjectOutputStream os = null;
    private ObjectInputStream is = null;
    public int temperature;

	// the constructor expects the IP address of the server - the port is fixed
    public Client(String serverIP) {
    	if (!connectToServer(serverIP)) {
    		System.out.println("XX. Failed to open socket connection to: " + serverIP);            
    	}
    }

    private boolean connectToServer(String serverIP) {
    	try { // open a new socket to the server 
    		this.socket = new Socket(serverIP,portNumber);
    		this.os = new ObjectOutputStream(this.socket.getOutputStream());
    		this.is = new ObjectInputStream(this.socket.getInputStream());
    		System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress() 
    				+ " on port: " + this.socket.getPort());
    		System.out.println("    -> from local address: " + this.socket.getLocalAddress() 
    				+ " and port: " + this.socket.getLocalPort());
    	} 
        catch (Exception e) {
        	System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
        	System.out.println("    Exception: " + e.toString());	
        	return false;
        }
		return true;
    }

    //Method to get the relevant data returned from the server
    private void getInfo(CanvasGraph canvas) {
    	String theDateCommand = "GetDate";
    	DateTimeService serverInfo = new DateTimeService();
    	System.out.println("01. -> Sending Command (" + theDateCommand + ") to the server...");
    	this.send(theDateCommand);
    	try{
    		//Receive DateTimeService object from the server
    		serverInfo = (DateTimeService) receive();
    		//Get relevant fields
    		String date = serverInfo.getDate();
    		int temp = serverInfo.getTemp();
    		
    		// Add value to the relevant canvas object which will be displayed in the GUI
    		canvas.addValues(temp);
    		
    		System.out.println("05. <- The Server responded with: ");
    		System.out.println("    <- " + date + " and temperature " + temp);
    	}
    	catch (Exception e){
    		System.out.println("XX. There was an invalid object sent back from the server");
    	}
    	System.out.println("06. -- Disconnected from Server.");
    }
    
	// method to send a generic object.
    private void send(Object o) {
		try {
		    System.out.println("02. -> Sending an object...");
		    os.writeObject(o);
		    os.flush();
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Sending:" +  e.toString());
		}
    }

    // method to receive a generic object.
    private Object receive() 
    {
		Object o = null;
		try {
			System.out.println("03. -- About to receive an object...");
		    o = is.readObject();
		    System.out.println("04. <- Object received...");
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Receiving:" + e.toString());
		}
		return o;
    }
    
    //Pass in the sample rate chosen by the user and the relevant canvas object
    // to distinguish between the graphs
    public static void ipConnect(String serverIP, int sampleRate, final CanvasGraph canvas) 
    {
    	if(!serverIP.contains(" ")){
    		final Client theApp = new Client(serverIP);
		    final int sample = sampleRate;
		    
		    new Thread(new Runnable() {
		        public void run() {
		            while (true) {
		            	theApp.getInfo(canvas);
		                try {
		                    Thread.sleep(sample);
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }).start();
		}
    	else
    	{
    		System.out.println("Error: you must provide the address of the server");
    		System.out.println("Usage is:  java Client x.x.x.x  (e.g. java Client 192.168.7.2)");
    		System.out.println("      or:  java Client hostname (e.g. java Client localhost)");
    	}    
    	
    }
}