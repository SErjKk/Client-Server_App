package Filters;
import Collection.FuelType;
import Collection.VehicleCollection;
import Collection.VehicleType;
import Functional.Command;

import java.io.Serializable;

public class fuelFilter extends Command implements Serializable {
    public fuelFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 30L;
    private String login;
    private String password;
    private FuelType fuel;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public FuelType getFuel() {
        return fuel;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_fuel(getFuel()));
    }
}