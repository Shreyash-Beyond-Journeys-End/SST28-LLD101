import java.util.List;

public class Emergency {
    private static List<ElevatorCar> fleet = List.of();

    static void setCars(List<ElevatorCar> cars) {
        fleet = List.copyOf(cars);
    }

    static void emergency() {
        System.out.println("all cars are being shut down");
        for (ElevatorCar elevatorCar : fleet) {
            elevatorCar.stop();
            elevatorCar.setState(State.EMERGENCYSTOP);
        }
    }
}
