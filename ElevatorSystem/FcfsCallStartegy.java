import java.util.List;

public class FcfsCallStartegy implements CallStrategy {
    private final List<ElevatorCar> cars;

    FcfsCallStartegy(List<ElevatorCar> cars) {
        this.cars = List.copyOf(cars);
    }

    public ElevatorCar assignCar(Floor requestedFloor, State requestedDirection) {
        ElevatorCar firstAvailableCar = null;

        for (ElevatorCar elevatorCar : cars) {
            if (!elevatorCar.isAvailable()) {
                continue;
            }

            if (firstAvailableCar == null) {
                firstAvailableCar = elevatorCar;
            }

            if (elevatorCar.getState() == State.IDLE) {
                return elevatorCar;
            }
        }

        if (firstAvailableCar == null) {
            throw new IllegalStateException("No elevator is available");
        }
        return firstAvailableCar;
    }
}
