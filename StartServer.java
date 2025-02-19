import java.io.IOException;

public class StartServer
{

	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.err.println ("expected port number command line argument");
			System.exit(0);
		}
		try
		{
			int port = Integer.parseInt(args[0]);
			if (!SocketUtils.isValidPortNum (port))
				throw new NumberFormatException();
			Server srv = new Server(port);
		}
		catch (IOException e)
		{
			System.err.println ("problem with connection");
		}
		catch (NumberFormatException e)
		{
			System.err.println ("invalid port number");
		}
		catch (Exception e)
		{
			System.err.println ("unknown error occurred - terminating");
		}
	}

}
