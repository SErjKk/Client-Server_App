package Filters;
import Collection.VehicleCollection;
import Functional.Command;

import java.io.Serializable;

public class yFilter extends Command implements Serializable {
    public yFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 22L;
    private String condition;
    private String login;
    private String password;
    private float y;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public float getY() {
        return y;
    }
    public String getCondition() {
        return condition;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_y(getCondition(), getY()));
    }
}