import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class Rfc865UdpClient {

    static DatagramSocket socket;
    public static void main(String[] args) {
        //
        // 1. Open UDP Socket
        //
        try{
            socket = new DatagramSocket(); // init
            InetAddress IpAddress = InetAddress.getByName("localhost");
            socket.connect(IpAddress, 17);

        }catch(SocketException e){
            e.printStackTrace();
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            //
            // 2. Send UDP request to server
            //
            byte[] buffer = "Hans Tananda, FEP2, 172.21.150.135".getBytes("UTF-8");
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.send(request);

            //
            // 3. Receive UDP reply from server
            //
            byte[] reply_buffer = new byte[512];
            DatagramPacket reply = new DatagramPacket(reply_buffer, reply_buffer.length);
            socket.receive(reply);

            String quote = new String(reply_buffer);
            System.out.println(quote);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            socket.close();
        }
    }
}
