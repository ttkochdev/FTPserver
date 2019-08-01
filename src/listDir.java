import java.io.File;
import java.util.Arrays;


public class listDir {
	String username;
	public listDir(String username){
		this.username = username;
	}
	public static String listsDir(String username){
		String list = null;
		File userDir = new File("c:/temp/photoserver/"+username);
		list = (Arrays.toString(userDir.listFiles()));
		return list;
	}

}
