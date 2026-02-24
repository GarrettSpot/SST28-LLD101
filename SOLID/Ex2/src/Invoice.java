import java.util.Map;

public class Invoice {
    private Order order;
    private Map<String, MenuItem> menu;
    private double subtotal;
    private double taxPct;
    private double tax;
    private double discount;
    private double total;
    private int invoiceSeq;
    private String invId;

    private String formatted;

    public double getSubTotalAmount() {
        return subtotal;
    }

    public double getTaxAmount() {
        return tax;
    }

    public double getDiscountAmount() {
        return discount;
    }

    public double getTotalAmount() {
        return total;
    }

    public String getInvId() {
        return invId;
    }

    public String getInvoiceString() {
        return formatted;
    }

    Invoice(Order order, Map<String, MenuItem> menu, int invoiceSeq) {
        this.order = order;
        this.menu = menu;
        this.invoiceSeq = invoiceSeq;
        this.invId = "INV-" + (this.invoiceSeq);

        this.subtotal = calculateSubtotal();
        this.taxPct = TaxRules.taxPercent(order.customerType);
        this.tax = calculateTax();
        this.discount = calculateDiscount();

        this.total = subtotal + tax - discount;

        this.formatted = render();
    }

    public String render() {
        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(invId).append("\n");

        for (OrderLine l : order.lines) {
            MenuItem item = this.menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        String printable = InvoiceFormatter.identityFormat(out.toString());

        return printable;
    }

    private double calculateSubtotal() {
        double subtotal = 0.0;
        for (OrderLine l : order.lines) {
            MenuItem item = this.menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
        }

        return subtotal;
    }

    private double calculateTax() {
        double tax = subtotal * (this.taxPct / 100.0);

        return tax;
    }

    private double calculateDiscount() {
        double discount = DiscountRules.discountAmount(this.order.customerType, subtotal, this.order.lines.size());
        
        return discount;
    }
}
