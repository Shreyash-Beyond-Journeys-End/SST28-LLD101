import java.util.List;

public class ShortestPathCallStrategy implements CallStrategy {
    private final List<ElevatorCar> cars;

    ShortestPathCallStrategy(List<ElevatorCar> cars) {
        this.cars = List.copyOf(cars);
    }

    public ElevatorCar assignCar(Floor requestedFloor, State requestedDirection) {
        ElevatorCar selectedCar = null;
        int bestDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevatorCar : cars) {
            if (!elevatorCar.isAvailable()) {
                continue;
            }

            int candidateDistance = Math.abs(
                elevatorCar.getAtFloor().getFloorNumber() - requestedFloor.getFloorNumber()
            );

            if (candidateDistance < bestDistance) {
                bestDistance = candidateDistance;
                selectedCar = elevatorCar;
            }
        }

        if (selectedCar == null) {
            throw new IllegalStateException("No elevator is available");
        }
        return selectedCar;
    }
}
