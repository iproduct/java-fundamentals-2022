package course.java.model;

import course.java.dao.Identifiable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Inherited;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
public class Order implements Identifiable<Long> {
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

    public int getTotalCents() {
        return orderLines.stream().mapToInt(OrderLine::getLineTotalCents).sum();
    }

    public int getVatCents() {
        return (int) Math.round((VAT_PERCENTAGE / 100) * getTotalCents());
    }

    @Override
    public String toString() {
        var sj = new StringJoiner("/n", "", "")
                .add("Client: " + getClient().getFirstName() + " " + getClient().getLastName())
                .add("Date: " + getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .add("Details:");
        for (int i = 0; i < getOrderLines().size(); i++) {
            var line = getOrderLines().get(i);
            sj.add(String.format("| %2d | %40.40s | %3d | %6.2f | %6.2f |",
                    i + 1, line.getProduct().getTitle(), line.getQuantity(),
                    line.getPriceCents() / 100.0, line.getLineTotalCents() / 100.0));
        }
        return sj.toString();
    }
}
