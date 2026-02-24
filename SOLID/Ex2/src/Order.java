import java.util.List;

public class Order {
    public List<OrderLine> lines;
    public String customerType;

    Order(List<OrderLine> lines, String customerType) {
        this.lines = lines;
        this.customerType = customerType;
    }
}
