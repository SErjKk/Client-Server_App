package Collection;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class VehicleCollection implements Serializable {

    //Статические переменные
    private static ConcurrentSkipListSet<Vehicle> collection = new ConcurrentSkipListSet<>();
    private static LocalDateTime creationDate = LocalDateTime.now();
    private static final long serialVersionUID = 6L;

    //Вернуть размер или коллекцию целиком
    public static int getSize() {
        return collection.size();
    }
    public static ConcurrentSkipListSet<Vehicle> getCollection() {
        return collection;
    }

    //Методы коллекции
    public String help() {
        return  ("INFO: вывести информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "ADD: добавить новый элемент в коллекцию\n" +
                "DELETE: удалить элемент из коллекции по его id\n" +
                "UPDATE: изменить элемент с данным id\n" +
                "CLEAR: очистить коллекцию\n" +
                "AVERAGE: вывести среднее значение поля capacity для всех элементов коллекции");
    }
    public static String info() {
        return  ("Тип коллекции: TreeSet<Vehicle>\n" +
                "Размер коллекции: " + VehicleCollection.getSize() + "\n" +
                "Время последней инициализации: " + creationDate + "\n");
    }
    public String clear(String login) {
        int a = 0;
        for (Vehicle vehicles : collection) {
            if (vehicles.getOwner().equals(login)) {
                a += 1;
            }
        }
        if ((collection.size() == 0) || (a == 0))
            return "У вас нет элементов которые можно удалить\n";
        else {
            collection.clear();
            return "Значения ваших элементов очищены\n";
        }
    }
    public String add(Vehicle vehicle) {
        collection.add(vehicle);
        return "1";
    }
    public String remove(int id) {
        Vehicle delete = VehicleCollection.getCollection().stream().filter(x -> x.getId() == id).findFirst().get();
        VehicleCollection.getCollection().remove(delete);
        return "1";
    }
    public String update(Vehicle vehicle) {
        remove(vehicle.getId());
        collection.add(vehicle);
        return "1";
    }
    public String average() {
        if (collection.size() != 0) {
            float capacityALL, sum = 0;

            for (Vehicle vehicle : collection) {
                sum += vehicle.getCapacity();
            }
            capacityALL = sum / collection.size();
            return  "Среднее значение поля capacity по всем элементам таблицы = " + capacityALL + "\n";
        } else {
            return "Коллекция пуста, для выполнения команды необходимо заполнить коллекцию\n";
        }
    }

    //Фильтрация колнок таблицы
    public String filter_id(String condition, int id) {
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        switch (condition) {
            case "=":
                list = collection.stream().filter((vehicle -> vehicle.getId() == id)).collect(Collectors.toList());
                break;
            case "<":
                list = collection.stream().filter((vehicle -> vehicle.getId() < id)).collect(Collectors.toList());
                break;
            case ">":
                list = collection.stream().filter((vehicle -> vehicle.getId() > id)).collect(Collectors.toList());
                break;
            case "<=":
                list = collection.stream().filter((vehicle -> vehicle.getId() <= id)).collect(Collectors.toList());
                break;
            case ">=":
                list = collection.stream().filter((vehicle -> vehicle.getId() >= id)).collect(Collectors.toList());
                break;
        }

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        return info.toString();
    }
    public String filter_x(String condition, long x) {
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        switch (condition) {
            case "=":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getX() == x)).collect(Collectors.toList());
                break;
            case "<":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getX() < x)).collect(Collectors.toList());
                break;
            case ">":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getX() > x)).collect(Collectors.toList());
                break;
            case "<=":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getX() <= x)).collect(Collectors.toList());
                break;
            case ">=":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getX() >= x)).collect(Collectors.toList());
                break;
        }

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        return info.toString();
    }
    public String filter_y(String condition, float y) {
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        switch (condition) {
            case "=":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getY() == y)).collect(Collectors.toList());
                break;
            case "<":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getY() < y)).collect(Collectors.toList());
                break;
            case ">":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getY() > y)).collect(Collectors.toList());
                break;
            case "<=":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getY() <= y)).collect(Collectors.toList());
                break;
            case ">=":
                list = collection.stream().filter((vehicle -> vehicle.getCoordinates().getY() >= y)).collect(Collectors.toList());
                break;
        }

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        return info.toString();
    }
    public String filter_power(String condition, long power) {
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        switch (condition) {
            case "=":
                list = collection.stream().filter((vehicle -> vehicle.getEnginePower() == power)).collect(Collectors.toList());
                break;
            case "<":
                list = collection.stream().filter((vehicle -> vehicle.getEnginePower() < power)).collect(Collectors.toList());
                break;
            case ">":
                list = collection.stream().filter((vehicle -> vehicle.getEnginePower() > power)).collect(Collectors.toList());
                break;
            case "<=":
                list = collection.stream().filter((vehicle -> vehicle.getEnginePower() <= power)).collect(Collectors.toList());
                break;
            case ">=":
                list = collection.stream().filter((vehicle -> vehicle.getEnginePower() >= power)).collect(Collectors.toList());
                break;
        }

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        return info.toString();
    }
    public String filter_capacity(String condition, double capacity) {
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        switch (condition) {
            case "=":
                list = collection.stream().filter((vehicle -> vehicle.getCapacity() == capacity)).collect(Collectors.toList());
                break;
            case "<":
                list = collection.stream().filter((vehicle -> vehicle.getCapacity() < capacity)).collect(Collectors.toList());
                break;
            case ">":
                list = collection.stream().filter((vehicle -> vehicle.getCapacity() > capacity)).collect(Collectors.toList());
                break;
            case "<=":
                list = collection.stream().filter((vehicle -> vehicle.getCapacity() <= capacity)).collect(Collectors.toList());
                break;
            case ">=":
                list = collection.stream().filter((vehicle -> vehicle.getCapacity() >= capacity)).collect(Collectors.toList());
                break;
        }

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        return info.toString();
    }
    public String filter_type(VehicleType type) {
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        list = collection.stream().filter((vehicle -> vehicle.getType() == type)).collect(Collectors.toList());

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        return info.toString();
    }
    public String filter_fuel(FuelType fuel) {
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        list = collection.stream().filter((vehicle -> vehicle.getFuelType() == fuel)).collect(Collectors.toList());

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        return info.toString();
    }
    public String filter_owner(String owner) {
        System.out.println(owner);
        List<Vehicle> list = null;
        StringBuilder info = new StringBuilder();

        list = collection.stream().filter((vehicle -> vehicle.getOwner().equals(owner))).collect(Collectors.toList());

        if (list != null) {
            for (Vehicle vehicle : list) {
                info.append(vehicle.getInfo());
            }
        }
        System.out.println(info);
        return info.toString();
    }
}