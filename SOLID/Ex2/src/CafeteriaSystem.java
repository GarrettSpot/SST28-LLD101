import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final FileStore store = new FileStore();
    private final Printer printer = new Printer();
    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        Order order = new Order(lines, customerType);
        Invoice inv = new Invoice(order, menu, ++invoiceSeq);

        printer.printInvoice(inv);

        store.save(inv);

        printer.printSavedInvoice(inv, store.countLines(inv.getInvId()));
    }
}
