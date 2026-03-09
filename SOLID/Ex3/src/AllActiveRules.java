import java.util.ArrayList;
import java.util.List;

public class AllActiveRules{
    List<EligibilityRule> list = new ArrayList<>();

    public AllActiveRules(){
        list.add(new AttendanceRule());

        list.add(new CGRRule());

        list.add(new CreditsRule());

        list.add(new DisciplineRule());
    }
}
