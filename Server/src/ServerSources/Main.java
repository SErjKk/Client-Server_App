package ServerSources;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import Functional.Command;
import Functional.SQLManager;
import ServerSources.Connector;
import ServerSources.Receiver;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;

import static Functional.ColorPrinter.*;

public class Main {

    public final static ExecutorService executor1 = Executors.newFixedThreadPool(5);
    public final static ForkJoinPool executor2 = new ForkJoinPool();
    public final static ExecutorService executor3 = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //SQLManager manager = new SQLManager();
        //manager.connect_toSQL();
        //manager.getAllObjects();

        //Connector connector = new Connector();
        //connector.connect();

        FileOutputStream ñ = new FileOutputStream();

    }
}