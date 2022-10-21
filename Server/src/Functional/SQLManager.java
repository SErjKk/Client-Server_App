package Functional;
import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleCollection;
import Collection.VehicleType;

import javax.management.Notification;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;

import static Functional.ColorPrinter.*;

public class SQLManager {

    //Статические переменные
    public static String url = "jdbc:postgresql://localhost:50678/studs";
    public static String login = "s336514";
    public static String password = "uvt456";
    public static Connection connection;
    public static VehicleCollection collection = new VehicleCollection();

    //Хэширование паролей SHA-224
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Подключение к базе данных
    public void connect_toSQL() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            connection = DriverManager.getConnection(url, login, password);
            println(BLUE,"{СЕРВЕР ПОДКЛЮЧЕН К БД}\n");

        } catch (ClassNotFoundException | InstantiationException | SQLException | IllegalAccessException e) {
            println(RED, "{Ошибка при подключении к PostgreSQL: " + e.getMessage() + "}");
        }
    }

    //Создание таблицы объектов
    public void createTable_ofObjects() {
        String insert = "CREATE TABLE objects("
                + "id INT NOT NULL, "
                + "name VARCHAR NOT NULL, "
                + "coord_x INT NOT NULL, "
                + "coord_y INT NOT NULL, "
                + "creationDate VARCHAR NOT NULL, "
                + "enginePower INT NOT NULL, "
                + "capacity INT NOT NULL, "
                + "VehicleType VARCHAR NOT NULL, "
                + "FuelType VARCHAR NOT NULL, "
                + "owner VARCHAR NOT NULL)";
        try {
            Statement statement = connection.createStatement();
            statement.execute(insert);
            System.out.println("Команда выполнена");
        } catch (Exception e) {
            println(RED,"{Ошибка во время создания БД: " + e.getMessage() + "}");
        }
    }

    //Создание таблицы юзеров
    public void createTable_ofUsers() {
        String insert = "CREATE TABLE users("
                + "login VARCHAR NOT NULL, "
                + "password VARCHAR NOT NULL)";
        try {
            Statement statement = connection.createStatement();
            statement.execute(insert);
            System.out.println("Команда выполнена");
        } catch (SQLException e) {
            println(RED, "{Ошибка создания БД для юзеров: " + e.getMessage() + "}");
        }
    }

    //Создание AI
    public void createSequence() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE SEQUENCE id START WITH 1;");
        } catch (SQLException e) {
            println(RED,"{Ошибка во время создания sequence: " + e.getMessage() + "}");
        }
    }

    //Проверка существования юзера в БД
    public boolean isUserExists(String login, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, hashPassword(password));
            ResultSet rs = statement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            println(RED,"{Ошибка проверки наличия пользователя в БД: " + e.getMessage() + "}");
            return false;
        }
    }

    //Регистрация нового пользователя
    public boolean signUpNewUser(String login, String password) throws SQLException{
        if (!isUserExists(login, password)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, password) VALUES (?, ?)");
            statement.setString(1, login);
            statement.setString(2, hashPassword(password));
            statement.execute();
            return true;
        } else {
            return false;
        }
    }

    //Очистка элементов юзера в БД
    public boolean clearMyObjects(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objects WHERE owner = ?");
            statement.setString(1, login);
            statement.execute();
            return true;

        } catch (SQLException e) {
            println(RED,"{Ошибка удаления объекта из БД: " + e.getMessage() + "}");
            return false;
        }
    }

    //Удаление объекта в БД
    public boolean removeMyObject(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objects WHERE id = ?");
            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            println(RED,"{Ошибка во время удаления объекта из БД: " + e.getMessage() + "}");
            return false;
        }
    }
    public boolean removeMyObjects(int id, String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM objects WHERE id <= ? AND owner = ?");
            statement.setInt(1, id);
            statement.setString(2, login);
            statement.execute();
            return true;
        } catch (SQLException e) {
            println(RED,"{Ошибка во время удаления объекта из БД: " + e.getMessage() + "}");
            return false;
        }
    }

    //Проверка на владельца
    public boolean isObjectMine(int id, String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objects WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String owner = rs.getString("owner");
                return (owner.equals(login));
            } else
                return false;
        } catch (SQLException e) {
            println(RED,"{Ошибка во время удаления объекта из БД: " + e.getMessage() + "}");
            return false;
        }
    }

    //Получение объектов из БД
    public String getAllObjects() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM objects");
            ResultSet rs = statement.executeQuery();
            String answer = new String();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String coord_x = rs.getString("coord_x");
                int coord_y = rs.getInt("coord_y");
                String creationDate = rs.getString("creationdate");
                int enginePower = rs.getInt("enginepower");
                int capacity = rs.getInt("capacity");
                String vehicleType = rs.getString("vehicletype");
                String fuelType = rs.getString("fueltype");
                String owner = rs.getString("owner");

                Vehicle vehicle = new Vehicle(id, name, Long.parseLong(coord_x), coord_y, LocalDateTime.parse(creationDate),
                        enginePower, capacity, VehicleType.valueOf(vehicleType.toUpperCase()), FuelType.valueOf(fuelType.toUpperCase()), owner);
                collection.add(vehicle);
                answer += vehicle.getInfo();
            }
            return answer;

        } catch (SQLException e) {
            println(RED,"{Ошибка получения id объекта в БД: " + e.getMessage() + "}");
            return null;
        }
    }

    //Обновление элемента в БД
    public boolean updateObject(Vehicle vehicle) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE objects " +
                    "SET name = ?, coord_x = ?, coord_y = ?, creationdate = ?, enginepower = ?, capacity = ?, vehicletype = ?, fueltype = ?" +
                    "WHERE id = ?;");
            statement.setString(1, vehicle.getName());
            statement.setLong(2, vehicle.getCoordinates().getX());
            statement.setFloat(3, vehicle.getCoordinates().getY());
            statement.setString(4, String.valueOf(vehicle.getCreationDate()));
            statement.setLong(5, vehicle.getEnginePower());
            statement.setDouble(6, vehicle.getCapacity());
            statement.setString(7, String.valueOf(vehicle.getType()));
            statement.setString(8, String.valueOf(vehicle.getFuelType()));
            statement.setInt(9, vehicle.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            println(RED, "{Ошибка во время обновления объекта в БД: " + e.getMessage() + "}");
            return false;
        }
    }

    //Добавление объекта в БД
    public boolean signUpNewObject(Vehicle vehicle, String owner) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO objects " +
                    "(id, name, coord_x, coord_y, creationDate, enginePower, capacity, VehicleType, FuelType, owner) " +
                    "VALUES (nextval('id'), ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, vehicle.getName());
            statement.setLong(2, vehicle.getCoordinates().getX());
            statement.setFloat(3, vehicle.getCoordinates().getY());
            statement.setString(4, String.valueOf(vehicle.getCreationDate()));
            statement.setLong(5, vehicle.getEnginePower());
            statement.setDouble(6, vehicle.getCapacity());
            statement.setString(7, String.valueOf(vehicle.getType()));
            statement.setString(8, String.valueOf(vehicle.getFuelType()));
            statement.setString(9, owner);
            statement.execute();
            return true;
        } catch (Exception e) {
            println(RED,"{Ошибка во время добавления объекта в БД: " + e.getMessage() + "}");
            return false;
        }
    }

    //Получение id из БД
    public String getIDfromBase(Vehicle vehicle, String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM objects WHERE name = ? AND " +
                    "coord_x = ? AND coord_y = ? AND creationdate = ? AND enginepower = ? AND capacity = ? AND vehicletype = ? AND fueltype = ? AND owner = ?");
            statement.setString(1, vehicle.getName());
            statement.setLong(2, vehicle.getCoordinates().getX());
            statement.setFloat(3, vehicle.getCoordinates().getY());
            statement.setString(4, String.valueOf(vehicle.getCreationDate()));
            statement.setLong(5, vehicle.getEnginePower());
            statement.setDouble(6, vehicle.getCapacity());
            statement.setString(7, String.valueOf(vehicle.getType()));
            statement.setString(8, String.valueOf(vehicle.getFuelType()));
            statement.setString(9, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return rs.getString("id");
            else
                return null;
        } catch (SQLException e) {
            println(RED,"{Ошибка получения id объекта в БД: " + e.getMessage() + "}");
            return null;
        }
    }
}