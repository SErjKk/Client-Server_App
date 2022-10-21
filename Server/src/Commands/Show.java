package Commands;

import Collection.FuelType;
import Collection.Vehicle;
import Collection.VehicleType;
import Functional.Command;
import Functional.SQLManager;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Show extends Command implements Serializable {
    public Show() {}

    static private SQLManager manager = new SQLManager();
    private static final long serialVersionUID = 3L;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String execute() {
        return manager.getAllObjects();
    }
}