package course.java.service.impl;

import course.java.dao.OrderRepository;
import course.java.exception.InvalidOperationException;
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
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository bookRepo) {
        this.orderRepository = bookRepo;
    }

    @Override
    public Order createOrder(User client) {
        var order = new Order(client);
        order.setState(new CreatedState(order, this));
        orderRepository.create(order);
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
    public void addProduct(Order order, Book product) {
        addProduct(order, product, 1);
    }

    @Override
    public void addProduct(Order order, Book product, int quantity) {
        var lines = order.getOrderLines();
        var productLine = lines.stream()
                .filter(line -> line.getProduct().getId().equals(product.getId())).findAny();
        if (productLine.isPresent()) {
            var oldQuantity = productLine.get().getQuantity();
            productLine.get().setQuantity(oldQuantity + quantity);
        } else {
            order.getOrderLines().add(new OrderLine(product, quantity));
        }
    }

    @Override
    public boolean removeProduct(Order order, Book product) {
        var lines = order.getOrderLines();
        var productLine = lines.stream()
                .filter(line -> line.getProduct().getId().equals(product.getId())).findAny();
        if (productLine.isPresent()) {
            return order.getOrderLines().remove(new OrderLine(product, 0));
        }
        return false;
    }

    @Override
    public void nextOrderState(Order order) throws InvalidOperationException {
        order.getState().next();
    }

    @Override
    public void payOrder(Order order) throws InvalidOperationException {
        order.getState().pay();
        nextOrderState(order);
    }

    @Override
    public void deliverOrder(Order order) throws InvalidOperationException {
        order.getState().deliver();
        nextOrderState(order);
    }

    @Override
    public Collection<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }
}
