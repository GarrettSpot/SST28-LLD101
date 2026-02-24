public class Printer {
    Printer() {}
    
    public void printInvoice(Invoice inv) {
        System.out.println(inv.render());
    }

    public void printSavedInvoice(Invoice inv, int linecount) {
        System.out.println("Saved invoice: " + inv.getInvId() + " (lines=" + linecount + ")");
    }
}
