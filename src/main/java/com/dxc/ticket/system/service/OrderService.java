package com.dxc.ticket.system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dxc.ticket.system.dto.OrderDto;
import com.dxc.ticket.system.dto.TicketDto;
import com.dxc.ticket.system.dto.UserDto;
import com.dxc.ticket.system.mapper.ObjectMapper;
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

	public List<OrderDto> getAllOrdersByUser(Long userId) {
		User orderUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		List<Order> orders = orderRepository.findByUser(orderUser);
		List<OrderDto> orderDtos = new ArrayList<>();
		for(Order order : orders){
//			OrderDto orderDto = (OrderDto) ObjectMapper.copyObject(order,new OrderDto());
//			orderDtos.add(orderDto);
			User user = order.getUser();
			Ticket ticket = order.getTicket();
			TicketDto ticketDto = (TicketDto) ObjectMapper.copyObject(ticket,new TicketDto());
			UserDto userDto = (UserDto) ObjectMapper.copyObject(user,new UserDto());
			OrderDto orderDto = new OrderDto();
			orderDto.setId(order.getId());
			orderDto.setUser(userDto);
			orderDto.setTicket(ticketDto);
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}

	public Order createOrder(Long userId, Long ticketId) {
		/*User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketId));
		Order order = new Order();
		order.setUser(user);
		order.setTicket(ticket);
		order.setCreateTime(LocalDateTime.now());
		order.setUpdateTime(LocalDateTime.now());
		return orderRepository.save(order);*/

		//User user = userRepository.getOne(userId);
		//Ticket ticket = ticketRepository.getOne(ticketId);

		Order order = new Order();
		//order.setUser(user);
		//order.setTicket(ticket);
		order.setUserId(userId);
		order.setTicketId(ticketId);
		order.setCreateTime(LocalDateTime.now());
		order.setUpdateTime(LocalDateTime.now());

		return orderRepository.save(order);
	}
}
