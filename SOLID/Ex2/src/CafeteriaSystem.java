import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final DataStore store = new FileStore();
    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

   
    public void checkout(Customer customer, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = SubTotal.get(lines, menu);

        double taxPct = customer.taxPercent();

        double tax =  TaxCalculation.get(subtotal, taxPct);

        double discount = customer.discountAmount( subtotal, lines.size());

        double total = TotalCalculation.get(subtotal, tax, discount);

        String printable = InvoiceFormatter.identityFormat(invId, lines, menu, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
