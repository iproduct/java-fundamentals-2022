package course.java.state;

import course.java.exception.InvalidOperationException;
import course.java.model.Book;
import course.java.model.Order;
import course.java.service.OrderService;

public final class PayedState extends OrderState {
    public PayedState(Order order, OrderService orderService) {
        super(order, orderService);
    }

    @Override
    public String next() {
        getOrder().setState(new DeliveredState(getOrder(), getOrderService()));
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
    public String pay() throws InvalidOperationException {
        throw new InvalidOperationException("Order is already payed.");
    }

    @Override
    public String deliver() {
        // TODO payment process orchestration here ...
        return "Order delivered successfully";
    }
}
