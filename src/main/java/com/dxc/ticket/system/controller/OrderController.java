package com.dxc.ticket.system.controller;

import java.util.List;

import com.dxc.ticket.system.dto.OrderDto;
import com.dxc.ticket.system.dto.TicketDto;
import com.dxc.ticket.system.dto.UserDto;
import com.dxc.ticket.system.mapper.ObjectMapper;
import com.dxc.ticket.system.model.Ticket;
import com.dxc.ticket.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dxc.ticket.system.model.Order;
import com.dxc.ticket.system.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;


	@GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getAllOrdersByUser(@PathVariable Long userId) {
        List<OrderDto> orders = orderService.getAllOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/users/{userId}/tickets/{ticketId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @PathVariable Long ticketId) {
        Order order = orderService.createOrder(userId, ticketId);
        return ResponseEntity.ok(order);
    }

}