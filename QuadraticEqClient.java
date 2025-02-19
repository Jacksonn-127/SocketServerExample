import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class QuadraticEqClient {
    private String addr = null;
    private int port = 0;
    private String s = "";

    public QuadraticEqClient(String addr, int port, String s)
    {
        if (!SocketUtils.isValidIPAddr(addr))
            throw new IllegalArgumentException("invalid ip address");
        this.addr = addr;

        if (port < 0)
            throw new IllegalArgumentException ("invalid port number");
        this.port = port;


        if(s == null)
            throw new IllegalArgumentException("Invalid argument exception");
        this.s = s;
    }

    public String checkQuadraticEq()
    {
        String clientResponse = "";

        try
        {
            Socket soc = new Socket(addr, port);
            DataOutputStream out = new DataOutputStream(soc.getOutputStream());
            out.writeUTF("1 " + s);
            out.flush();
            DataInputStream input = new DataInputStream(soc.getInputStream());
            // grab client response and split it into individual numbers
            clientResponse = input.readUTF();

            soc.close();
        }
        catch (IOException e)
        {
            System.err.println ("problem making connection");
        }
        catch (NumberFormatException e){
            System.err.println("Error parsing Integers " + e.getMessage());
        }

        return clientResponse;
    }
}
