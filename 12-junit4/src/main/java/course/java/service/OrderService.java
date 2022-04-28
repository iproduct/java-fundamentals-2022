package course.java.service;

import course.java.exception.InvalidOperationException;
import course.java.model.Book;
import course.java.model.Order;
import course.java.model.User;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {
    Order createOrder(User client);
    Order createOrder(User client, Collection<Book> products);
    void addProduct(Order order, Book product);
    void addProduct(Order order, Book product, int quantity);
    boolean removeProduct(Order order, Book product);

    void nextOrderState(Order order) throws InvalidOperationException;

    void payOrder(Order order) throws InvalidOperationException;
    void deliverOrder(Order order) throws InvalidOperationException;

    Collection<Order> getAllOrders();
    Optional<Order> getOrderById(long id);
}
