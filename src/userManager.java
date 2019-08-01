import java.io.File;

/**
 * 
 * @author jkryst
 * Manage the directories of the system per protocol
 * Accept a username and run the following:
 * 1-Create a main directory on the system, if it does not exist
 * 2-Accept username and see if folder for name exists
 * 2a-If it does not, create it
 * 2b-If it does, return a message
 * 3-If a log-in attempt occurs for a non-existent user FAIL!
 */
public class userManager {
	String username;
	String code;
	public userManager(String username, String code){
		this.username = username;
		this.code = code;
	}
	public static String dirManage(String username, String code){
		String output = null;
		File mainDir = new File("c:/temp/photoserver");
		File userDir = new File("c:/temp/photoserver/"+username);
		System.out.println(code);
		if (code.equals("register"))
		{
			if (userDir.isDirectory()){
				output = "Failure: name already in use";
			}
			else
			{
				userDir.mkdir();
				output = "Ok: name registered";
				serverVars.username = username;
				serverVars.ss = ServerState.CO;
			}
		}
		else
		{
			if (code.equals("login"))
			{
				if (userDir.isDirectory()){
					output = "Logged In";
					serverVars.username = username;
					serverVars.ss = ServerState.CO;
				}
				else
				{
					output = "Failure: unregistered user name";
				}
			}
			else{
				output = null;
			}
		}
		return output;
	}
}
