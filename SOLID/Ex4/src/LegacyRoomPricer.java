import java.util.*;

public class LegacyRoomPricer implements RoomPricer {
    private final Map<Integer, Money> prices = new HashMap<>();

    public LegacyRoomPricer() {
        prices.put(LegacyRoomTypes.SINGLE, new Money(14000.0));
        prices.put(LegacyRoomTypes.DOUBLE, new Money(15000.0));
        prices.put(LegacyRoomTypes.TRIPLE, new Money(12000.0));
        prices.put(LegacyRoomTypes.DELUXE, new Money(16000.0));
    }

    @Override
    public Money priceFor(int roomType) {
        return prices.getOrDefault(roomType, new Money(0.0));
    }
}
