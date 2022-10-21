package Commands;
import Functional.Command;
import Functional.SQLManager;
import java.io.Serializable;

public class Authorization extends Command implements Serializable {
    public Authorization() {}

    static private SQLManager manager = new SQLManager();
    private static final long serialVersionUID = 1L;
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
        if (manager.isUserExists(getLogin(), getPassword())) {
            return ("1");
        } else {
            return ("Проверьте корректность введенных данных\n");
        }
    }
}