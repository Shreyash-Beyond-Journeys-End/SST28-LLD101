public class Ticket {
    String id;
    Vehicle parkedVehicle;
    ParkingSlot parkedSlot;
    String entryGate;
    int totalHours;

    public Ticket(String id, Vehicle parkedVehicle, ParkingSlot parkedSlot, String entryGate) {
        this.id = id;
        this.parkedVehicle = parkedVehicle;
        this.parkedSlot = parkedSlot;
        this.entryGate = entryGate;
        this.totalHours = 1;
    }

    @Override
    public String toString() {
        return "Ticket{id='" + id + "', vehicle=" + parkedVehicle
                + ", slot='" + parkedSlot.slotId + "', floor='" + parkedSlot.floorId
                + "', gate='" + entryGate + "'}";
    }
}
