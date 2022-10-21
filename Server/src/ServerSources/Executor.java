package ServerSources;
import Functional.Command;
import Functional.Message;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.util.concurrent.RecursiveAction;
import static ServerSources.Main.executor3;

public class Executor extends RecursiveAction {
    public Executor(Command command, DatagramSocket socket, SocketAddress client_address) {
        this.command = command;
        this.socket = socket;
        this.client_address = client_address;
    }

    //Статические переменные
    private Command command;
    private DatagramChannel channel;
    private Message message;
    private DatagramSocket socket;
    private SocketAddress address;
    private SocketAddress client_address;

    @Override
    protected void compute() {
        try {
            message = new Message(command.execute());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        executor3.execute(new Sender(message, socket, client_address));
    }
}