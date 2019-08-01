import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FTPServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		boolean listen = true;
		System.out.println("Waiting for connection");
		try {
			serverSocket = new ServerSocket(50000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (listen){
			serverGlobals.init();
			new FTPServerThread(serverSocket.accept()).start();
			System.out.println("Connection received");
		}
		serverSocket.close();
	}
}

/**
 *                   ________________.  ___     .______  
                 /                | /   \    |   _  \
                |   (-----|  |----`/  ^  \   |  |_)  |
                 \   \    |  |    /  /_\  \  |      /
            .-----)   |   |  |   /  _____  \ |  |\  \-------.
            |________/    |__|  /__/     \__\| _| `.________|
             ____    __    ____  ___     .______    ________.
             \   \  /  \  /   / /   \    |   _  \  /        |
              \   \/    \/   / /  ^  \   |  |_)  ||   (-----`
               \            / /  /_\  \  |      /  \   \
                \    /\    / /  _____  \ |  |\  \---)   |
                 \__/  \__/ /__/     \__\|__| `._______/
                 
                 Jason Kryst Verification Check
                 
                 NEEDS INIT

 */

