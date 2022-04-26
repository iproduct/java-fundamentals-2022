package course.java.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    public static final double VAT_PERCENTAGE = 20;

    private Long id;
    private LocalDateTime dateTime;
    private User client;
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order(User client) {
        this.client = client;
    }

    public Order(User client, List<OrderLine> orderLines) {
        this.client = client;
        this.orderLines = orderLines;
    }

    public int getTotalCents(){
        return orderLines.stream().mapToInt(OrderLine::getLineTotalCents).sum();
    }

    public int getVatCents() {
        return (int) Math.round((VAT_PERCENTAGE / 100) * getTotalCents());
    }
}
