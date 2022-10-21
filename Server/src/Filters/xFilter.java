package Filters;
import Collection.VehicleCollection;
import Functional.Command;

import java.io.Serializable;

public class xFilter extends Command implements Serializable {
    public xFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 21L;
    private String condition;
    private String login;
    private String password;
    private long x;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public long getX() {
        return x;
    }
    public String getCondition() {
        return condition;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_x(getCondition(), getX()));
    }
}