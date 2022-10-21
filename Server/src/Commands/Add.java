package Commands;
import Collection.Vehicle;
import Collection.VehicleCollection;
import Functional.Command;
import Functional.SQLManager;

import java.io.Serializable;

public class Add extends Command implements Serializable {
    public Add() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static SQLManager manager = new SQLManager();
    private static final long serialVersionUID = 13L;
    private Vehicle vehicle;
    private String login;
    private String password;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String execute() {
        if (manager.signUpNewObject(getVehicle(), getLogin())) {
            int id = Integer.parseInt(manager.getIDfromBase(getVehicle(), getLogin()));
            Vehicle vehicle = new Vehicle(id, getVehicle().getName(), getVehicle().getCoordinates().getX(), getVehicle().getCoordinates().getY(),
                    getVehicle().getCreationDate(), getVehicle().getEnginePower(), getVehicle().getCapacity(),
                    getVehicle().getType(), getVehicle().getFuelType(), getVehicle().getOwner());

            return collection.add(vehicle);
        } else
            return ("Ошибка при добавлении объекта в БД");
    }
}