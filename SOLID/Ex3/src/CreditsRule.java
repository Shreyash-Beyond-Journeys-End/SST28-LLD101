import java.util.List;

public class CreditsRule extends EligibilityRule{

    @Override
    void validate(StudentProfile s, List<String> reasons) {
        int minCredits = RuleInput.minCredits;
        if (s.earnedCredits < minCredits) {
            reasons.add("credits below "+minCredits);
        }
    }
    
}
