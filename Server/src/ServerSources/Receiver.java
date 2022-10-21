package ServerSources;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import Functional.Command;
import Functional.SQLManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.time.LocalDateTime;

import static Functional.ColorPrinter.*;
import static ServerSources.Main.executor2;

public class Receiver implements Runnable {
    public Receiver(DatagramSocket socket, byte[] buffer, SocketAddress client_address) {
        this.socket = socket;
        this.receiveBuffer = buffer;
        this.client_address = client_address;
    }

    //Статические переменные
    private DatagramSocket socket;
    private DatagramPacket receivePacket;
    private byte[] receiveBuffer;
    private ByteArrayInputStream in;
    private Command command;
    private SocketAddress client_address;

    //Получаем запрос от клиента и выводим его на экран
    private Command getRequest() {
        command = deserialization(receiveBuffer);
        println(BLUE, "{ЗАПРОС ОТ " + command.getLogin().toUpperCase() + "}");
        println(WHITE, command + "\n");

        return command;
    }

    //Десериализация
    public Command deserialization(byte[] receiveBuffer) {
        try {
            in = new ByteArrayInputStream(receiveBuffer);
            ObjectInputStream obj = new ObjectInputStream(in);
            command = (Command) obj.readObject();

        } catch (IOException | ClassNotFoundException e) {
            println(RED,"{ошибка при десериализации объекта: " + e.getMessage() + "}");
        }
        return command;
    }

    @Override
    public void run() {
        executor2.invoke(new Executor(getRequest(), socket, client_address));
    }
}
