package com.dxc.ticket.system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.ticket.system.exception.ResourceNotFoundException;
import com.dxc.ticket.system.model.Order;
import com.dxc.ticket.system.model.Ticket;
import com.dxc.ticket.system.model.User;
import com.dxc.ticket.system.repository.OrderRepository;
import com.dxc.ticket.system.repository.TicketRepository;
import com.dxc.ticket.system.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository userOrderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public Page<Order> getOrders(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return userOrderRepository.findAll(pageable);
	}

	public List<Order> getAllOrdersByUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return orderRepository.findByUser(user);
	}

	public Order createOrder(Long userId, Long ticketId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketId));
		Order order = new Order();
		order.setUser(user);
		order.setTicket(ticket);
		order.setCreateTime(LocalDateTime.now());
		order.setUpdateTime(LocalDateTime.now());
		return orderRepository.save(order);
	}
}
