import java.net.*;
import java.io.*;
public class ServerSuma {
    public static void trimiteDateCatreClient(DataOutputStream dos, String sir) throws IOException
    {
        dos.writeUTF(sir);
        dos.flush();
        System.out.println("Am trimis catre client: " + sir);
    }
    public static String primesteDateDeLaClient(DataInputStream dis) throws IOException
    {
        String sir = dis.readUTF();
        System.out.println("Am primit de la client: " + sir);
        return sir;
    }

    public static void main(String[] args) {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Socket s = null;
        ServerSocket server = null;
        try
        {
            server = new ServerSocket(1080);
            s = server.accept();
            System.out.println("S-a produs conectarea clientului");
            dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
        }
        catch(IOException e)
        {
            System.out.println("Eroare la conectare: " + e);
        }
        String sirDouble = "";
        double dUnu = 0.0, dDoi = 0.0;
        double sumaDouble = 0.0;
        try
        {
            sirDouble = primesteDateDeLaClient(dis);
            Double dlTemp = Double.valueOf(sirDouble);
            dUnu = dlTemp.doubleValue();
            sirDouble = primesteDateDeLaClient(dis);
            dlTemp = Double.valueOf(sirDouble);
            dDoi = dlTemp.doubleValue();
            sumaDouble = dUnu + dDoi;
            trimiteDateCatreClient(dos, Double.toString(sumaDouble));
        }
        catch(IOException e)
        {
            System.out.println("Eroare la trimitere + primire date: " + e);
        }
        System.out.println(dUnu + " + " + dDoi + " = " + sumaDouble);
    }
}
