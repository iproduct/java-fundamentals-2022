package course.java.state;

import course.java.exception.InvalidOperationException;
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
        getOrderService().addProduct(getOrder(), product, quantity);
        return String.format("Product '%s' added successfully to order %d.%n",
                product, getOrder().getId());
    }

    @Override
    public String removeProduct(Book product) {
        getOrderService().removeProduct(getOrder(), product);
        return String.format("Product '%s' added successfully to order %d.%n",
                product, getOrder().getId());
    }

    @Override
    public String pay() throws InvalidOperationException {
        throw new InvalidOperationException("Order not complete and can not be payed.");
    }

    @Override
    public String deliver() throws InvalidOperationException {
        throw new InvalidOperationException("Order not complete and can not be delivered.");
    }
}
