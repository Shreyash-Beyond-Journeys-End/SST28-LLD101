import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElevatorSystemDemo {
    public static void main(String[] args) throws Exception {
        ElevatorSystem system = createDemoSystem();
        List<Floor> floors = system.getFloors();

        Floor groundFloor = requireFloor(floors, 0);
        Floor secondFloor = requireFloor(floors, 2);
        Floor fifthFloor = requireFloor(floors, 5);
        Floor sixthFloor = requireFloor(floors, 6);

        runTrip(system, fifthFloor, State.UP, secondFloor, "Requesting elevator to floor 5",
            "Passenger selects floor 2 from inside elevator ");
        runTrip(system, sixthFloor, State.DOWN, groundFloor, "Requesting another elevator to floor 6",
            "Passenger selects ground floor from inside elevator ");

        printFleetSummary(system.getCars());
    }

    private static ElevatorSystem createDemoSystem() {
        List<Integer> floorNumbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
        return new ElevatorFactory().getSystem(new ArrayList<>(floorNumbers), "shortestPath", 2);
    }

    private static void runTrip(
        ElevatorSystem system,
        Floor pickupFloor,
        State direction,
        Floor destinationFloor,
        String requestMessage,
        String destinationMessage
    ) throws Exception {
        System.out.println(requestMessage);
        ElevatorCar car = system.requestElevator(pickupFloor, direction);
        System.out.println("Assigned elevator: " + car.getCarId());
        car.move();

        System.out.println(destinationMessage + car.getCarId());
        car.addDestination(destinationFloor);
        car.move();
    }

    private static void printFleetSummary(List<ElevatorCar> cars) {
        System.out.println("Final elevator positions:");
        for (ElevatorCar car : cars) {
            System.out.println(
                "Elevator " + car.getCarId() + " is at floor "
                    + car.getAtFloor().getFloorNumber()
                    + " with state " + car.getState()
            );
        }
    }

    private static Floor requireFloor(List<Floor> floors, int floorNumber) {
        return floors.stream()
            .filter(floor -> floor.getFloorNumber() == floorNumber)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Floor not found: " + floorNumber));
    }
}
