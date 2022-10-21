package Collection;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Vehicle implements Comparable<Vehicle>, Serializable {
    public Vehicle(int id, String name, Long coordX, float coordY, LocalDateTime creationDate,
                   long enginePower, double capacity, VehicleType type, FuelType fuelType, String owner) {
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(coordX, coordY);
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.type = type;
        this.fuelType = fuelType;
        this.owner = owner;
    }

    private int id;                         //���������� id (id > 0, ������������ �������������)
    private String name;                    //�������� (name != null)
    private Coordinates coordinates;        //���������� (coordinates != null)
    private LocalDateTime creationDate;     //���� �������� (creationDate != null, ������������ �������������)
    private long enginePower;               //��������� ��������� (enginePower > 0)
    private double capacity;                //����������� (capacity > 0)
    private VehicleType type;               //��� ���������� (type != null)
    private FuelType fuelType;              //��� ������� (fuelType != null)
    private String owner;
    private static final long serialVersionUID = 7L;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = LocalDateTime.now();
    }

    public long getEnginePower() {
        return enginePower;
    }
    public void setEnginePower(long enginePower) {
        this.enginePower = enginePower;
    }

    public double getCapacity() {
        return capacity;
    }
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public VehicleType getType() {
        return type;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return ("������������ �������� [id: " + id + "]\n\t" + "��������: " + name + "\n\t����������:\n\t\tx = " +
                coordinates.getX() + "\n\t\ty = " + coordinates.getY() + "\n\t���� c�������: " + creationDate.getDayOfMonth() + "." +
                creationDate.getMonth() + "." + creationDate.getYear() + " " + creationDate.getHour() + ":" + creationDate.getMinute() +
                ":" + creationDate.getSecond() + "\n\t���� ���������: " + enginePower + "\n\t�����������: " + capacity +
                "\n\t��� ����������: " + type + "\n\t��� �������: " + fuelType + "\n\t��������: " + owner + "\n-------------------------------");
    }

    public String getInfo() {
        return (id + "   " + name + "   " + coordinates.getX() + "   " + coordinates.getY() + "   " + creationDate.getDayOfMonth() + "." +
                creationDate.getMonth() + "." + creationDate.getYear() + "  " + creationDate.getHour() + ":" + creationDate.getMinute() +
                ":" + creationDate.getSecond() + "   " + enginePower + "   " + capacity + "   " + type + "   " + fuelType + "   " + owner + "   ");
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        Integer integer = id;
        return integer.compareTo(vehicle.getId());
    }
}