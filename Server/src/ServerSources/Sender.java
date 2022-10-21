package ServerSources;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import Functional.Command;
import Functional.Message;
import Functional.SQLManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.LocalDateTime;

import static Functional.ColorPrinter.*;

public class Sender implements Runnable {
    public Sender(Message message, DatagramSocket socket, SocketAddress client_address){
        this.message = message;
        this.socket = socket;
        this.client_address = client_address;
    }

    //Статические переменные
    private Message message;
    private DatagramPacket sendPacket;
    private  DatagramSocket socket;
    private SocketAddress client_address;
    private DatagramChannel channel;
    private int port = 59812;
    private ByteArrayOutputStream out;
    private byte[] sendBuffer;

    //Сериализация
    public byte[] serialization(Message message) {
        try {
            out = new ByteArrayOutputStream();
            ObjectOutputStream obj = new ObjectOutputStream(out);
            obj.writeObject(message);

        } catch (IOException e) {
            println(RED,"{ошибка при сериализации объекта: " + e.getMessage() + "}");
        }
        return out.toByteArray();
    }

    @Override
    public void run() {
        sendBuffer = new byte[65000];

        try {
            socket = new DatagramSocket();
            if (message != null) {
                sendBuffer = serialization(message);
                sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, client_address);
            }
            socket.send(sendPacket);
        } catch (IOException e) {
            println(RED,"{ошибка при отправке ответа: " + e.getMessage() + "}");
        }
    }
}
