import java.util.HashMap;
import java.util.Map;

public class ParkingSlot {
    String slotId;
    String floorId;
    String slotType;
    boolean isFree;
    Map<String, Integer> gateDistances;

    public ParkingSlot(String slotId, String floorId, String slotType) {
        this.slotId = slotId;
        this.floorId = floorId;
        this.slotType = slotType;
        this.isFree = true;
        this.gateDistances = new HashMap<>();
    }

    public ParkingSlot setDistance(String gateId, int distance) {
        gateDistances.put(gateId, distance);
        return this;
    }

    public int getDistance(String gateId) {
        Integer distance = gateDistances.get(gateId);
        if (distance == null) {
            return Integer.MAX_VALUE;
        }
        return distance;
    }
}
