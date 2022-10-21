package ServerSources;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import static Functional.ColorPrinter.*;
import static ServerSources.Main.executor1;

public class Connector {

    public void connect() {
        int port = 59812;
        try {
            SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(true);
            channel.bind(address);

            while (true) {
                byte[] buffer = new byte[65000];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                channel.socket().receive(packet);
                if (packet != null) {
                    SocketAddress client_address = packet.getSocketAddress();
                    executor1.execute(new Receiver(channel.socket(), buffer, client_address));
                }
            }

        } catch (IOException e) {
            println(RED,"{ошибка соединения: " + e.getMessage() + "}");
        }
    }
}
