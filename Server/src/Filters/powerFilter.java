package Filters;
import Collection.VehicleCollection;
import Functional.Command;

import java.io.Serializable;

public class powerFilter extends Command implements Serializable {
    public powerFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 24L;
    private String condition;
    private String login;
    private String password;
    private long power;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public long getPower() {
        return power;
    }
    public String getCondition() {
        return condition;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_power(getCondition(), getPower()));
    }
}