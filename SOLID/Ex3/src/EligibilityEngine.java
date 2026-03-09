import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;

    public EligibilityEngine(FakeEligibilityStore store) { this.store = store; }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s); 
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = CheckEligibility.check(s);
        String status = "ELIGIBLE";

        if(!reasons.isEmpty()) status = "NOT_ELIGIBLE";

        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
