import java.util.ArrayList;
import java.util.List;

public class CheckEligibility {
    public static List<String> check(StudentProfile s){
        List<String> reasons = new ArrayList<>();

        AllActiveRules rules = new AllActiveRules();

        for (EligibilityRule rule : rules.list) {
            rule.validate(s, reasons);
        }

        return reasons;
    }
}
