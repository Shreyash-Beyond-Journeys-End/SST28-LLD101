public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        
        Checker pc = new PlagiarismChecker();
        Grader grader = new CodeGrader();
        Writer writer = new ReportWriter();
        Rubric rubric = new Rubric();
        

        new EvaluationPipeline().evaluate(sub , pc , grader , writer , rubric);
    }
}
