public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();
        lot.addGate("Gate1");
        lot.addGate("Gate2");
        lot.setRate("SMALL", 20);
        lot.setRate("MEDIUM", 40);
        lot.setRate("LARGE", 100);

        Level firstFloor = new Level("Floor-1");
        firstFloor.addSlot(new ParkingSlot("A1", "Floor-1", "SMALL").setDistance("Gate1", 2).setDistance("Gate2", 8));
        firstFloor.addSlot(new ParkingSlot("A2", "Floor-1", "MEDIUM").setDistance("Gate1", 5).setDistance("Gate2", 6));
        firstFloor.addSlot(new ParkingSlot("A3", "Floor-1", "LARGE").setDistance("Gate1", 9).setDistance("Gate2", 4));

        Level secondFloor = new Level("Floor-2");
        secondFloor.addSlot(new ParkingSlot("B1", "Floor-2", "SMALL").setDistance("Gate1", 4).setDistance("Gate2", 2));
        secondFloor.addSlot(new ParkingSlot("B2", "Floor-2", "MEDIUM").setDistance("Gate1", 1).setDistance("Gate2", 7));
        secondFloor.addSlot(new ParkingSlot("B3", "Floor-2", "LARGE").setDistance("Gate1", 8).setDistance("Gate2", 1));

        lot.addLevel(firstFloor);
        lot.addLevel(secondFloor);

        Ticket t1 = lot.park(new Vehicle("KA-01-1111", "TWO_WHEELER"), "Gate1");
        Ticket t2 = lot.park(new Vehicle("KA-02-2222", "CAR"), "Gate1");
        Ticket t3 = lot.park(new Vehicle("KA-03-3333", "BUS"), "Gate2");

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);

        System.out.println(lot.getStatus());

        t2.totalHours = 2;
        int bill = lot.exit(t2);
        System.out.println("Amount to pay = " + bill);

        System.out.println(lot.getStatus());
    }
}
