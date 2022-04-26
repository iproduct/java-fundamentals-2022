package course.java.service;

import course.java.model.Book;
import course.java.model.Order;
import course.java.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(User client);
    Order createOrder(User client, Collection<Book> products);
    Order addProduct(Order order, Book product);
    Order addProduct(Order order, Book product, int quantity);
    Order removeProduct(Order order, Book product);
    Collection<Order> getAllOrders();
    Optional<Order> getOrderById(long id);
}
