import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InButtons {
    private final Set<Floor> serviceableFloors;
    private ElevatorCar owner;

    public InButtons(List<Floor> floors) {
        serviceableFloors = new HashSet<>(floors);
    }

    void attachTo(ElevatorCar elevatorCar) {
        owner = elevatorCar;
    }

    void press(Floor floor) {
        if (!serviceableFloors.contains(floor)) {
            throw new IllegalArgumentException("Invalid floor selected");
        }
        if (owner == null) {
            throw new IllegalStateException("Buttons are not attached to an elevator");
        }
        owner.addDestination(floor);
    }

    void pressEmergency() {
        Emergency.emergency();
    }
}
