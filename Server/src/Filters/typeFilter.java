package Filters;
import Collection.VehicleCollection;
import Collection.VehicleType;
import Functional.Command;

import java.io.Serializable;

public class typeFilter extends Command implements Serializable {
    public typeFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 26L;
    private String login;
    private String password;
    private VehicleType type;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_type(getType()));
    }
}