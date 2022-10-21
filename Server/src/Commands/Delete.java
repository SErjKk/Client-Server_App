package Commands;
import Collection.VehicleCollection;
import Functional.Command;
import Functional.SQLManager;

import java.io.Serializable;

public class Delete extends Command implements Serializable {
    public Delete() {}

    static private VehicleCollection collection  = new VehicleCollection();
    static private SQLManager manager = new SQLManager();
    private static final long serialVersionUID = 4L;
    private int id;
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

    public int getId() {
        return id;
    }

    @Override
    public String execute() {
        if (manager.isObjectMine(getId(), getLogin()))
            if (manager.removeMyObject(getId()))
                return collection.remove(getId());
            else
                return "Вы не можете удалять чужие объекты\n";
        else
            return "Вы не можете удалять чужие объекты\n";
    }
}
