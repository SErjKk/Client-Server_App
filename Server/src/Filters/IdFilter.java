package Filters;
import Collection.VehicleCollection;
import Functional.Command;
import java.io.Serializable;

public class IdFilter extends Command implements Serializable {
    public IdFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 20L;
    private String condition;
    private String login;
    private String password;
    private int id;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
    public String getCondition() {
        return condition;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_id(getCondition(), getId()));
    }
}