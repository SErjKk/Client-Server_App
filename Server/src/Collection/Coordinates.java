package Collection;
import java.io.Serializable;

public class Coordinates implements Serializable {

    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates() {}

    private Long x;       //���������� �� Ox (x != null)
    private float y;      //���������� �� Oy
    private static final long serialVersionUID = 8L;

    public Long getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}