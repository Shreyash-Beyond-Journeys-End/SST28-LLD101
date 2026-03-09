import java.util.*;

public class OnboardingService {
    private final StudentDatabase db;

    public OnboardingService(StudentDatabase db) { this.db = db; }

    
    public void registerFromRawInput(String raw) {
        PrintingService print = new PrintingService();

        print.displayInput(raw);

        Map<String,String> kv = ParseInput.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        
        boolean accepted = ValidateParsedInput.validate(kv);
        if(accepted == false) return;

        String id = IdUtil.nextStudentId(db.count());

        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);


        print.displayConfirmation(rec, id, db);

        
    }
}
