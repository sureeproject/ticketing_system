package com.dxc.ticket.system.controller;

import java.util.List;

import com.dxc.ticket.system.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.ticket.system.exception.ResourceNotFoundException;
import com.dxc.ticket.system.model.Ticket;
import com.dxc.ticket.system.model.TicketStatus;
import com.dxc.ticket.system.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("")
    public ResponseEntity<List<TicketDto>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        List<TicketDto> tickets = ticketService.findAll(pageable);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("/create")
    public Ticket createTicket(@RequestBody TicketDto ticket) {

        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
    }

}
