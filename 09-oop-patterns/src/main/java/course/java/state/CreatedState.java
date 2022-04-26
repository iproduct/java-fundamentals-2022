package course.java.state;

import course.java.model.Book;
import course.java.model.Order;
import course.java.service.OrderService;

public final class CreatedState extends OrderState {
    public CreatedState(Order order, OrderService orderService) {
        super(order, orderService);
    }

    @Override
    public String next() {
        getOrder().setState(new PendingState(getOrder(), getOrderService()));
        return "Order complete and payment is pending.";
    }

    @Override
    public String addProduct(Book product, int quantity) {
        return null;
    }

    @Override
    public String removeProduct(Book product) {
        return null;
    }

    @Override
    public String pay() {
        return null;
    }

    @Override
    public String deliver() {
        return null;
    }
}
