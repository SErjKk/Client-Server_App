package Commands;
import Collection.Vehicle;
import Collection.VehicleCollection;
import Functional.Command;
import Functional.SQLManager;

import java.io.Serializable;

public class Update extends Command implements Serializable {
    public Update() {}

    static private VehicleCollection collection  = new VehicleCollection();
    static private SQLManager manager = new SQLManager();
    private static final long serialVersionUID = 5L;
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
        if (manager.isObjectMine(getVehicle().getId(), getLogin()))
            if (manager.updateObject(getVehicle()))
                return collection.update(getVehicle());
            else
                return "Вы не можете изменять чужие объекты\n";
        else
            return "Вы не можете изменять чужие объекты\n";
    }
}
