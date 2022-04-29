package course.java.state;

import course.java.exception.InvalidOperationException;
import course.java.model.Book;
import course.java.model.Order;
import course.java.service.OrderService;

public final class PendingState extends OrderState {
    public PendingState(Order order, OrderService orderService) {
        super(order, orderService);
    }

    @Override
    public String next() {
        getOrder().setState(new PayedState(getOrder(), getOrderService()));
        return "Order complete and payment is pending.";
    }

    @Override
    public String addProduct(Book product, int quantity) throws InvalidOperationException {
        throw new InvalidOperationException("Order is complete and more products can not be added.");
    }

    @Override
    public String removeProduct(Book product) throws InvalidOperationException {
        throw new InvalidOperationException("Order is complete and products can not be removed.");
    }

    @Override
    public String pay() {
        // TODO payment process orchestration here ...
        return "Order payed successfully";
    }

    @Override
    public String deliver() throws InvalidOperationException {
        throw new InvalidOperationException("Order is not payed yet and can not be delivered.");
    }
}
