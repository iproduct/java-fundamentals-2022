package course.java.state;

import course.java.exception.InvalidOperationException;
import course.java.model.Book;
import course.java.model.Order;
import course.java.service.OrderService;

public final class DeliveredState extends OrderState {
    public DeliveredState(Order order, OrderService orderService) {
        super(order, orderService);
    }

    @Override
    public String next() throws InvalidOperationException {
        throw new InvalidOperationException("Order is already delivered - no further state transitions available.");
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
    public String deliver() throws InvalidOperationException {
        throw new InvalidOperationException("Order is already delivered.");
    }

}
