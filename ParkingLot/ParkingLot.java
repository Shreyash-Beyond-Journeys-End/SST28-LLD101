import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ParkingLot {
    List<Level> allLevels;
    List<String> allGates;
    Map<String, Integer> rateCard;
    Map<String, Ticket> openTickets;
    int nextTicketNumber;

    public ParkingLot() {
        allLevels = new ArrayList<>();
        allGates = new ArrayList<>();
        rateCard = new HashMap<>();
        openTickets = new LinkedHashMap<>();
        nextTicketNumber = 1;
    }

    public void addLevel(Level level) {
        allLevels.add(level);
    }

    public void addGate(String gateId) {
        allGates.add(gateId);
    }

    public void setRate(String slotType, int rate) {
        rateCard.put(slotType, rate);
    }

    public Ticket park(Vehicle vehicle, String entryGateId) {
        if (!allGates.contains(entryGateId)) {
            throw new RuntimeException("Unknown gate: " + entryGateId);
        }

        String requiredSlotType = findRequiredSlotType(vehicle.vehicleType);
        ParkingSlot selectedSlot = findNearestFreeSlot(requiredSlotType, entryGateId);

        if (selectedSlot == null) {
            throw new RuntimeException("Parking full for " + vehicle.vehicleType);
        }

        selectedSlot.isFree = false;
        Ticket newTicket = new Ticket(
                "TK" + nextTicketNumber,
                vehicle,
                selectedSlot,
                entryGateId
        );
        nextTicketNumber++;
        openTickets.put(newTicket.id, newTicket);
        return newTicket;
    }

    public int exit(Ticket ticket) {
        Ticket foundTicket = openTickets.remove(ticket.id);
        if (foundTicket == null) {
            throw new RuntimeException("Invalid ticket");
        }

        foundTicket.parkedSlot.isFree = true;
        return foundTicket.totalHours * rateCard.get(foundTicket.parkedSlot.slotType);
    }

    public Map<String, Integer> getStatus() {
        Map<String, Integer> freeCount = new HashMap<>();
        freeCount.put("SMALL", 0);
        freeCount.put("MEDIUM", 0);
        freeCount.put("LARGE", 0);

        for (Level level : allLevels) {
            for (ParkingSlot slot : level.slotList) {
                if (slot.isFree) {
                    int old = freeCount.get(slot.slotType);
                    freeCount.put(slot.slotType, old + 1);
                }
            }
        }
        return freeCount;
    }

    private String findRequiredSlotType(String vehicleType) {
        if (vehicleType.equals("TWO_WHEELER")) {
            return "SMALL";
        }
        if (vehicleType.equals("CAR")) {
            return "MEDIUM";
        }
        return "LARGE";
    }

    private ParkingSlot findNearestFreeSlot(String requiredSlotType, String gateId) {
        ParkingSlot answer = null;
        int minDistance = Integer.MAX_VALUE;

        for (Level level : allLevels) {
            for (ParkingSlot slot : level.slotList) {
                if (!slot.isFree) {
                    continue;
                }
                if (!slot.slotType.equals(requiredSlotType)) {
                    continue;
                }

                int currentDistance = slot.getDistance(gateId);
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    answer = slot;
                }
            }
        }

        return answer;
    }
}
