package com.dxc.ticket.system.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.ticket.system.model.Order;
import com.dxc.ticket.system.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAll(Pageable pageable);
    List<Order> findByUser(User user);

    Order findByTicketId(Long ticketId);
    void deleteByTicketId(Long ticketId);

    void deleteById(Long orderId);
}
