package course.java.service.impl;

import course.java.dao.BookRepository;
import course.java.dao.OrderRepository;
import course.java.model.Book;
import course.java.model.Order;
import course.java.model.OrderLine;
import course.java.model.User;
import course.java.service.OrderService;
import course.java.state.CreatedState;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository bookRepo;

    public OrderServiceImpl(OrderRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Order createOrder(User client) {
        var order =  new Order(client);
        order.setState(new CreatedState(order, this));
        return order;
    }

    @Override
    public Order createOrder(User client, Collection<Book> products) {
        var order = createOrder(client);
        var lines = products.stream().map(p -> new OrderLine(p, 1)).collect(Collectors.toList());
        order.setOrderLines(lines);
        return order;
    }

    @Override
    public Order addProduct(Order order, Book product) {
        return order;
    }

    @Override
    public Order addProduct(Order order, Book product, int quantity) {
        return null;
    }

    @Override
    public Order removeProduct(Order order, Book product) {
        return null;
    }

    @Override
    public Collection<Order> getAllOrders() {
        return null;
    }

    @Override
    public Optional<Order> getOrderById(long id) {
        return Optional.empty();
    }
}
