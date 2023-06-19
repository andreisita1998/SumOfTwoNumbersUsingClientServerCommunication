import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientSuma {
    public static void trimiteDateCatreServer(DataOutputStream dos, String sir) throws IOException
    {
        dos.writeUTF(sir);
        dos.flush();
        System.out.println("Am trimis catre server: " + sir);
    }
    public static String primesteDateDeLaServer(DataInputStream dis) throws IOException
    {
        String sir = dis.readUTF();
        System.out.println("Am primit de la server: " + sir);
        return sir;
    }

    public static void main(String[] args) {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Socket s = null;
        try
        {
            s = new Socket("127.0.0.1", 1080);
            System.out.println("Ne-am conectat la serverul sumaDouble");
            dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
        }
        catch(IOException e)
        {
            System.out.println("Eroare la conectare: " + e);
        }
        double dUnu = 0.0, dDoi = 0.0;
        Scanner sc;
        String linie;
        sc = new Scanner(System.in);
        System.out.flush();
        System.out.println("Dati primul numar double");
        linie = sc.nextLine();
        Double dlTemp = Double.valueOf(linie);
        dUnu = dlTemp.doubleValue();
        System.out.flush();
        System.out.println("Dati al doilea numar double");
        linie = sc.nextLine();
        dlTemp = Double.valueOf(linie);
        dDoi = dlTemp.doubleValue();
        sc.close();
        String sirDouble = "";
        try
        {
            trimiteDateCatreServer(dos,Double.toString(dUnu));
            trimiteDateCatreServer(dos,Double.toString(dDoi));
            sirDouble = primesteDateDeLaServer(dis);
        }
        catch(IOException e)
        {
            System.out.println("Eroare la trimitere + primire date: " + e);
        }
        System.out.println(dUnu + " + " + dDoi + " = " + sirDouble);
    }
}
