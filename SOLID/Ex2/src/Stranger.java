public class Stranger  extends Customer{

    @Override
    double taxPercent() {
        return 8.0;
    }

    @Override
    double discountAmount(double subtotal, int distinctLines) {
        return 0.0;
    }
    
}
