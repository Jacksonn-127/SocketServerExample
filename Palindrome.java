public class Palindrome {
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.err.println("expected url, port, and string command line arguments, palindrome");
            System.exit(0);
        }
        try
        {
            String addr = args[0];
            if (!SocketUtils.isValidIPAddr(addr))
                throw new RuntimeException("invalid ip address");
            int port = Integer.parseInt(args[1]);
            if (!SocketUtils.isValidPortNum(port))
                throw new RuntimeException("invalid port number command line argument");

            String clientWord = args[2].toLowerCase();
            PalindromeClient p = new PalindromeClient(addr, port, clientWord);
            String result = p.checkPalindrome();
            System.out.println(result);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
