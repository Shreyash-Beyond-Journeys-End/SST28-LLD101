import java.util.ArrayList;
import java.util.List;

public class Level {
    String floorName;
    List<ParkingSlot> slotList;

    public Level(String floorName) {
        this.floorName = floorName;
        this.slotList = new ArrayList<>();
    }

    public Level addSlot(ParkingSlot slot) {
        slotList.add(slot);
        return this;
    }
}
