import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {
    public static void main(String[] args) {

        //
        // 1. Open UDP socket at well-known port
        //
        DatagramSocket socket = null;

        try{
            socket = new DatagramSocket(17); // QOTD port 17
        }catch (SocketException e){
            e.printStackTrace();
            System.exit(-1);
        }
        //System.out.println("Socket bind complete: "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());

        while(true){
            try{
                //
                // 2. Listen for UDP request from client
                //

                byte[] buffer = new byte[512];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String s = new String(buffer);
                System.out.println(s);

                InetAddress IPAddress = request.getAddress();
                int port = request.getPort();

                //
                // 3. Send UDP reply to client
                //

                byte[] replyBuf = "Hi there from the other side of the world!".getBytes("UTF-8");
                DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length,IPAddress,port);
                socket.send(reply);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
