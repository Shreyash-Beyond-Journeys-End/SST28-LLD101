import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
         

        double base = req.roomType.getBasePrice();
       
        AddOnList allAddOn = new AddOnList();              

        double add = 0.0;

        for (AddOn a : req.addOns) {
            for (AddOnRule rule : allAddOn.list) {
                if (rule.appliesTo(a)) {
                    add += rule.getPrice();
                }
            }
        }

        return new Money(base + add);
    }
}
