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

    private int id;                         //уникальный id (id > 0, генерируется автоматически)
    private String name;                    //название (name != null)
    private Coordinates coordinates;        //коотдинаты (coordinates != null)
    private LocalDateTime creationDate;     //дата создания (creationDate != null, генерируется автоматически)
    private long enginePower;               //мощьность двигателя (enginePower > 0)
    private double capacity;                //вместимость (capacity > 0)
    private VehicleType type;               //тип транспорта (type != null)
    private FuelType fuelType;              //тип топлива (fuelType != null)
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
        return ("Транспортное средство [id: " + id + "]\n\t" + "Название: " + name + "\n\tКоординаты:\n\t\tx = " +
                coordinates.getX() + "\n\t\ty = " + coordinates.getY() + "\n\tДата cоздания: " + creationDate.getDayOfMonth() + "." +
                creationDate.getMonth() + "." + creationDate.getYear() + " " + creationDate.getHour() + ":" + creationDate.getMinute() +
                ":" + creationDate.getSecond() + "\n\tМощь двигателя: " + enginePower + "\n\tВместимость: " + capacity +
                "\n\tТип транспорта: " + type + "\n\tТип топлива: " + fuelType + "\n\tВладелец: " + owner + "\n-------------------------------");
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