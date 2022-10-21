package Commands;
import Collection.VehicleCollection;
import Functional.Command;
import java.io.Serializable;

public class Help extends Command implements Serializable {
    public Help() {}

    static private VehicleCollection collection = new VehicleCollection();
    private static final long serialVersionUID = 13L;
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
        return collection.help();
    }
}