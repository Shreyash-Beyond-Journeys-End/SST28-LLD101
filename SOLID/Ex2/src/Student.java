public class Student  extends Customer{

    @Override
    double taxPercent() {
        return 0.0;
    }

    @Override
    double discountAmount(double subtotal, int distinctLines) {
        if (subtotal >= 180.0) return 10.0;
        return 0.0;
    }
    
}
