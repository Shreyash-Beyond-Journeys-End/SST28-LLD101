import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElevatorCar {
    private final int carId;
    private final InButtons cabinButtons;
    private final List<Floor> scheduledStops;
    private Floor currentFloor;
    private int allowedWeight;
    private State currentState;

    public ElevatorCar(int carId, InButtons cabinButtons, Floor initialFloor) {
        this.carId = carId;
        this.cabinButtons = cabinButtons;
        this.currentFloor = initialFloor;
        this.allowedWeight = 700;
        this.currentState = State.IDLE;
        this.scheduledStops = new ArrayList<>();
        this.cabinButtons.attachTo(this);
    }

    void setState(State nextState) {
        currentState = nextState;
    }

    void setWeight(int weight) {
        allowedWeight = weight;
    }

    void addDestination(Floor destinationFloor) {
        if (destinationFloor == null) {
            throw new IllegalArgumentException("Destination floor cannot be null");
        }

        scheduledStops.add(destinationFloor);
        reorderStops();
        refreshState();
    }

    int getCarId() {
        return carId;
    }

    Floor getAtFloor() {
        return currentFloor;
    }

    State getState() {
        return currentState;
    }

    boolean isAvailable() {
        return currentState != State.MAINTENANCE && currentState != State.EMERGENCYSTOP;
    }

    void move() throws Exception {
        ensureOperational();
        if (scheduledStops.isEmpty()) {
            currentState = State.IDLE;
            return;
        }

        Floor nextStop = scheduledStops.remove(0);
        currentState = determineDirection(nextStop);
        currentFloor = nextStop;

        stop();
        openDoor();
        closeDoor();
        refreshState();
    }

    void stop() {
        System.out.println("Elevator " + carId + " stopped at floor " + currentFloor.getFloorNumber());
    }

    void openDoor() {
        System.out.println("Opening the door for elevator " + carId);
    }

    void closeDoor() throws Exception {
        if (DoorSensor.check()) {
            throw new Exception("Move Aside");
        }
        if (WeightSensor.check(allowedWeight)) {
            throw new Exception("Overloaded");
        }
        System.out.println("Closing the door for elevator " + carId);
    }

    private void ensureOperational() throws Exception {
        if (currentState == State.MAINTENANCE) {
            throw new Exception("Lift UnderMaintenance");
        }
        if (currentState == State.EMERGENCYSTOP) {
            throw new Exception("Lift is in emergency stop");
        }
    }

    private void reorderStops() {
        scheduledStops.sort(Comparator.comparingInt(this::distanceFromCurrentFloor));
    }

    private int distanceFromCurrentFloor(Floor floor) {
        return Math.abs(floor.getFloorNumber() - currentFloor.getFloorNumber());
    }

    private void refreshState() {
        if (scheduledStops.isEmpty()) {
            currentState = State.IDLE;
            return;
        }
        currentState = determineDirection(scheduledStops.get(0));
    }

    private State determineDirection(Floor destinationFloor) {
        int currentLevel = currentFloor.getFloorNumber();
        int requestedLevel = destinationFloor.getFloorNumber();
        if (requestedLevel > currentLevel) {
            return State.UP;
        }
        if (requestedLevel < currentLevel) {
            return State.DOWN;
        }
        return State.IDLE;
    }
}
