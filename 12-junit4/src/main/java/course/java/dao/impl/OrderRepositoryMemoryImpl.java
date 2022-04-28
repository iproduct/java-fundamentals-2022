package course.java.dao.impl;

import course.java.dao.IdGenerator;
import course.java.dao.OrderRepository;
import course.java.model.Order;

class OrderRepositoryMemoryImpl extends RepositoryMemoryImpl<Order, Long> implements OrderRepository {
    public OrderRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}
