package course.java.state;

import course.java.exception.InvalidEntityDataException;
import course.java.exception.InvalidOperationException;
import course.java.model.Book;
import course.java.model.Order;
import course.java.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.StringJoiner;

@Data
@AllArgsConstructor
public sealed abstract class OrderState permits CreatedState, PendingState, PayedState, DeliveredState {
    private final Order order;
    private final OrderService orderService;

    public abstract String next() throws InvalidOperationException;

    public abstract String addProduct(Book product, int quantity);

    public abstract String removeProduct(Book product);

    public abstract String pay();

    public abstract String deliver();

    public String getStatus() {
        return new StringJoiner(", ", "Order [" + getClass().getSimpleName() + "]: ", "")
                .add(getOrder().getOrderLines().size() + " products")
                .add(String.format("Price: %6.2f", getOrder().getTotalCents() / 100.0))
                .add(String.format("VAT: %6.2f", getOrder().getVatCents() / 100.0))
                .add(String.format("Total: %6.2f", (getOrder().getTotalCents() + getOrder().getVatCents()) / 100.0))
                .toString();
    }

}
