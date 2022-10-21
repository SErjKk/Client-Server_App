package Filters;
import Collection.VehicleCollection;
import Functional.Command;
import java.io.Serializable;

public class ownerFilter extends Command implements Serializable {
    public ownerFilter() {}

    private static VehicleCollection collection  = new VehicleCollection();
    private static final long serialVersionUID = 31L;
    private String login;
    private String password;
    private String owner;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String execute() {
        return String.valueOf(collection.filter_owner(getOwner()));
    }
}