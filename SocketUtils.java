public class SocketUtils
{
	private static final int IP_SECS = 4;
	
	private static final int MAX_PORT_NUMBER = 49999;
	
	/**
	 * @param s string
	 * @return whether s consists of all digits
	 */
	private static boolean isAllDigits(String s)
	{
		boolean result = true;
		if (s != null)
		{
			String regex = "[0-9]+";
			result = s.matches(regex);
		}
		return result;
	}
			
	/**
	 * @param s string
	 * @return whether s is a valid IP address
	 */
	public static boolean isValidIPAddr(String s)
	{
		boolean result = false;
		if (s != null && s.length() > 0)
		{
			String[] tokens = s.split("\\.");
			if (tokens.length == IP_SECS)
			{
				boolean done = false;
				int i = 0;
				while (i < IP_SECS && !done)
					if (!isAllDigits(tokens[i]))
						done = true;
					else
						i++;
				result = i == IP_SECS;
			}
		}
		return result;
	}
	
	/**
	 * @param port port number
	 * @return whether port is a valid port number
	 */
	public static boolean isValidPortNum(int port)
	{
		return port >= 1000 && port <= MAX_PORT_NUMBER;
	}

}

