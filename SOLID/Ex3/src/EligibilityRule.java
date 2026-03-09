import java.util.List;

abstract class EligibilityRule {
    abstract void validate(StudentProfile s,List<String> reasons);
}
