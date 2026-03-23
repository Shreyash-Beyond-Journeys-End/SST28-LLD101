public class Vehicle {
    String vehicleNumber;
    String vehicleType;

    public Vehicle(String vehicleNumber, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{number='" + vehicleNumber + "', type='" + vehicleType + "'}";
    }
}
