import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

enum ServerState {
	CS, CO, FT
};

public class FTPServerThread extends Thread {
	private Socket socket = null;
	
	public FTPServerThread(Socket socket){
		super("FTPServerThread");
		this.socket = socket;
	}
	
	public void run(){
		
		
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputLine;
		String outputLine;		
		try {
			while((inputLine = in.readLine()) != null){
				System.out.println("Received: " + inputLine);
				outputLine = "";
				
				//Pull in of protocol
				Scanner sc = new Scanner(inputLine);
				String scNext = sc.next();
				
				if (scNext.equals("logout")){
					break;
				}
				else{			
					//Server State Check
					switch (serverVars.ss){
					//If in ClientSetup
						case CS:
							//Check Protocol
							//Move register and log-in to a method as 
							//Data processing is similarly handled
							switch (scNext){
							case "register": 
							case "login":
								//Must be persistent
								String register = sc.next();			
								serverVars.result = userManager.dirManage(register, scNext);
								outputLine = serverVars.result;
								
							break;
							default:
								outputLine = "Failure: not logged in";							
							}
						break;
						case CO:
							switch (scNext){
								case "list":
									serverVars.result = listDir.listsDir(serverVars.username);
									outputLine = serverVars.result;
								break;
							}
						break;
						}
					System.out.println("Sent: " + outputLine);
					out.println(outputLine);
					if (inputLine.equals("bye")){
						break;
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out.close();
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
