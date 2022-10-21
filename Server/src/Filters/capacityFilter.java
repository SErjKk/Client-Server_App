package Filters;
import Collection.VehicleCollection;
import Functional.Command;

import java.io.Serializable;

public class capacityFilter extends Command implements Serializable {
    public capacityFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 27L;
    private String condition;
    private String login;
    private String password;
    private double capacity;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public double getCapacity() {
        return capacity;
    }
    public String getCondition() {
        return condition;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_capacity(getCondition(), getCapacity()));
    }
}