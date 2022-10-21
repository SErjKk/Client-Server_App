package Functional;
import java.io.Serializable;

public class Message implements Serializable {
    public String str;

    public Message(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

}
