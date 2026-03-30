import java.util.List;

public class ElevatorSystem {
    private final List<Floor> buildingFloors;
    private final CallStrategy callStrategy;
    private final List<ElevatorCar> elevatorCars;

    public ElevatorSystem(List<Floor> buildingFloors, CallStrategy callStrategy, List<ElevatorCar> elevatorCars) {
        this.buildingFloors = List.copyOf(buildingFloors);
        this.callStrategy = callStrategy;
        this.elevatorCars = List.copyOf(elevatorCars);
        Emergency.setCars(this.elevatorCars);
    }

    ElevatorCar requestElevator(Floor targetFloor, State direction) {
        ElevatorCar assignedCar = callStrategy.assignCar(targetFloor, direction);
        assignedCar.addDestination(targetFloor);
        return assignedCar;
    }

    List<Floor> getFloors() {
        return buildingFloors;
    }

    List<ElevatorCar> getCars() {
        return elevatorCars;
    }
}
