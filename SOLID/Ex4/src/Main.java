import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        RoomPricer roomPricer = new LegacyRoomPricer();
        AddOnPricer addOnPricer = new SimpleAddOnPricer();
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), roomPricer, addOnPricer);
        calc.process(req);
    }
}
