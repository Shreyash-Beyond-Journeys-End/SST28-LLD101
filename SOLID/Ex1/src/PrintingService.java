public class PrintingService {
    public void displayInput(String raw){
        System.out.println("INPUT: " + raw);
    }

    public void displayConfirmation(StudentRecord rec , String id , StudentDatabase db){
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + db.count());
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
