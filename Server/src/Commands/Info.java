package Commands;
import Collection.VehicleCollection;
import Functional.Command;

import java.io.Serializable;

public class Info extends Command implements Serializable {
    public Info() {}

    private String login;
    private String password;
    static private VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 8L;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String execute() {
        return collection.info();
    }
}