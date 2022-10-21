package Commands;
import Collection.VehicleCollection;
import Functional.Command;
import Functional.SQLManager;

import java.io.Serializable;

public class Clear extends Command implements Serializable {
    public Clear() {}

    static private VehicleCollection collection  = new VehicleCollection();
    static private SQLManager manager = new SQLManager();
    private static final long serialVersionUID = 9L;
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

    @Override
    public String execute() {
        if (manager.clearMyObjects(getLogin()))
            return collection.clear(getLogin());
        else
            return ("Ошибка при очистке коллекции в БД");
    }
}