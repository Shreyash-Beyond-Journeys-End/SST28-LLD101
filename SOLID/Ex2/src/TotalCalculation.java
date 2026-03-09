public class TotalCalculation {
    public static double get(double subtotal , double tax , double discount){
        double total = subtotal + tax - discount;
        return total;
    }
}
