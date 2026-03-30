import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElevatorFactory {
    public ElevatorSystem getSystem(ArrayList<Integer> floorNumbers, String strategyName, int numberOfCars) {
        List<Floor> floors = buildFloors(floorNumbers);
        List<ElevatorCar> cars = buildCars(floors, numberOfCars);
        return new ElevatorSystem(floors, resolveStrategy(strategyName, cars), cars);
    }

    private List<Floor> buildFloors(List<Integer> floorNumbers) {
        List<Floor> floors = new ArrayList<>();
        for (Integer floorNumber : floorNumbers) {
            floors.add(new Floor(floorNumber, new OutButtons()));
        }
        floors.sort(Comparator.comparingInt(Floor::getFloorNumber));
        return floors;
    }

    private List<ElevatorCar> buildCars(List<Floor> floors, int numberOfCars) {
        List<ElevatorCar> cars = new ArrayList<>();
        Floor startingFloor = floors.get(0);
        for (int index = 0; index < numberOfCars; index++) {
            cars.add(new ElevatorCar(index + 1, new InButtons(floors), startingFloor));
        }
        return cars;
    }

    private CallStrategy resolveStrategy(String strategyName, List<ElevatorCar> cars) {
        return switch (strategyName) {
            case "fcfs" -> new FcfsCallStartegy(cars);
            case "shortestPath" -> new ShortestPathCallStrategy(cars);
            default -> throw new IllegalArgumentException("Unsupported call strategy");
        };
    }
}
