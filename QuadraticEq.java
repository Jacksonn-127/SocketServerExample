public class QuadraticEq {
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.out.println("expected url, port, and string command line arguments, palindrome");
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

            // I chose not to make 3 separate reads from args but instead when I input my double values to separate them with "!" character
            String clientWord = args[2].toLowerCase();
            QuadraticEqClient QEC = new QuadraticEqClient(addr, port, clientWord);
            String result = QEC.checkQuadraticEq();
            System.out.println(result);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}