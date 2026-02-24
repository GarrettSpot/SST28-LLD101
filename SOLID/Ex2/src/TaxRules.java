import java.util.HashMap;
import java.util.Map;

public class TaxRules {
    private static final Map<String, Double> taxmap = new HashMap<>(); 

    private TaxRules() {}

    public static void addTaxRule(String customerType, Double taxPercent) {
        taxmap.put(customerType, taxPercent);
    }

    public static double taxPercent(String customerType) {
        // hard-coded policy (smell)
        // if ("student".equalsIgnoreCase(customerType)) return 5.0;
        // if ("staff".equalsIgnoreCase(customerType)) return 2.0;
        return taxmap.getOrDefault(customerType, 8.0);
    }
}
