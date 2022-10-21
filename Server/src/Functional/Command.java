package Functional;
import java.sql.SQLException;

public abstract class Command {

    public abstract String execute() throws SQLException;
    public abstract String getLogin();
    public abstract String getPassword();
}
