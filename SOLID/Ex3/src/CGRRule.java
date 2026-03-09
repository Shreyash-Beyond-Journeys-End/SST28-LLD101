import java.util.List;

public class CGRRule  extends EligibilityRule{

    @Override
    void validate(StudentProfile s, List<String> reasons){
        double minCgr = RuleInput.minCgr;
        if (s.cgr < minCgr) {
            reasons.add("CGR below "+minCgr);
        }
    }
    
}
