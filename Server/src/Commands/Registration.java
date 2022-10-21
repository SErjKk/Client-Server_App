package Commands;

import Functional.Command;
import Functional.SQLManager;

import java.io.Serializable;
import java.sql.SQLException;

public class Registration extends Command implements Serializable {
    public Registration() {}

    static private SQLManager manager = new SQLManager();
    private static final long serialVersionUID = 2L;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String execute() throws SQLException {
        if (manager.signUpNewUser(getLogin(), getPassword())) {
            return ("1");
        } else {
            return ("Такой пользователь уже существует\n");
        }
    }
}