import java.util.*;

public class SimpleAddOnPricer implements AddOnPricer {
    private final Map<AddOn, Money> prices = new EnumMap<>(AddOn.class);

    public SimpleAddOnPricer() {
        prices.put(AddOn.MESS, new Money(1000.0));
        prices.put(AddOn.LAUNDRY, new Money(500.0));
        prices.put(AddOn.GYM, new Money(300.0));
    }

    @Override
    public Money priceFor(AddOn a) {
        return prices.getOrDefault(a, new Money(0.0));
    }
}
