package Commands;
import Collection.VehicleCollection;
import Functional.Command;
import java.io.Serializable;

public class Average extends Command implements Serializable {
    public Average() {}

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

    static private VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 10L;

    @Override
    public String execute() {
        return collection.average();
    }
}