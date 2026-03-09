import java.util.List;

public class DisciplineRule extends EligibilityRule{

    @Override
    void validate(StudentProfile s, List<String> reasons) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            reasons.add("disciplinary flag present");
        } 
    }
    
    
}
