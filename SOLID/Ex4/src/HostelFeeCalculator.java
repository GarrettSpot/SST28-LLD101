import java.util.*;

public class HostelFeeCalculator {
    private final BookingRepository repo;
    private final RoomPricer roomPricer;
    private final AddOnPricer addOnPricer;

    public HostelFeeCalculator(BookingRepository repo, RoomPricer roomPricer, AddOnPricer addOnPricer) {
        this.repo = repo;
        this.roomPricer = roomPricer;
        this.addOnPricer = addOnPricer;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money base = roomPricer.priceFor(req.roomType);
        Money add = new Money(0.0);
        for (AddOn a : req.addOns) {
            add = add.plus(addOnPricer.priceFor(a));
        }
        return base.plus(add);
    }
}
