public class TaxCalculation {
    public static double get(double subtotal , double taxPct){
        double tax = subtotal * (taxPct / 100.0);
        return tax;
    }
}
