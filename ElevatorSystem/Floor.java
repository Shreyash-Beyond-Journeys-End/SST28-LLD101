public class Floor {
    private final int floorNumber;
    private final OutButtons outButtons;

    public Floor(int floorNumber, OutButtons outButtons) {
        this.floorNumber = floorNumber;
        this.outButtons = outButtons;
    }

    int getFloorNumber() {
        return floorNumber;
    }

    OutButtons getOutButtons() {
        return outButtons;
    }
}
