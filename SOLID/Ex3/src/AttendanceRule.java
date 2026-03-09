import java.util.List;

public class AttendanceRule extends EligibilityRule{
    
    @Override
    void validate(StudentProfile s, List<String> reasons){
        double minAttendance = RuleInput.minAttendance;
        if (s.attendancePct < minAttendance) {
            reasons.add("attendance below "+minAttendance);
        }
    }
}
