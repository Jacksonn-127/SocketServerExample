import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
	private ServerSocket server = null;
	private ExecutorService executor = null;

	private boolean done;


	public Server(int port) throws IOException
	{
		if (!SocketUtils.isValidPortNum(port))
			throw new IllegalArgumentException ("invalid port number in Server constructor");
		done = false;
		server = new ServerSocket(port);
		executor = Executors.newCachedThreadPool();
		

		while (!done)
		{
			Socket soc = server.accept();
			ClientHandler handler = new ClientHandler(soc);
			executor.execute(handler);
		}
	}


	public void shutDown() throws IOException
	{
		done = true;
		server.close();
	}
}
