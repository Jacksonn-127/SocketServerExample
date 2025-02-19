import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PalindromeClient {
    private String addr = null;
    private int port = 0;
    private String s = null;

    public PalindromeClient(String addr, int port, String s)
    {
        if (!SocketUtils.isValidIPAddr(addr))
            throw new IllegalArgumentException("invalid ip address");
        this.addr = addr;
        if (port < 0)
            throw new IllegalArgumentException ("invalid port number");
        this.port = port;
        if (s == null)
            throw new IllegalArgumentException ("null string argument");
        this.s = s;
    }

    public String checkPalindrome()
    {
        String clientWord = "";
        try
        {
            Socket soc = new Socket(addr, port);
            DataOutputStream out = new DataOutputStream(soc.getOutputStream());
            out.writeUTF("2 " + s);
            out.flush();
            DataInputStream input = new DataInputStream(soc.getInputStream());
            clientWord = input.readUTF();
            soc.close();
        }
        catch (IOException e)
        {
            System.err.println ("problem making connection");
        }
        return clientWord;
    }
}
