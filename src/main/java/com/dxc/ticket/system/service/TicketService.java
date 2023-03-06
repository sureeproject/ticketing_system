package com.dxc.ticket.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.ticket.system.exception.ResourceNotFoundException;
import com.dxc.ticket.system.model.Ticket;
import com.dxc.ticket.system.model.TicketStatus;
import com.dxc.ticket.system.repository.TicketRepository;

@Service
@Transactional
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Page<Ticket> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }
    
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setName(ticket.getName());
        existingTicket.setPrice(ticket.getPrice());
        existingTicket.setStatus(ticket.getStatus());
        return ticketRepository.save(existingTicket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
    
    public List<Ticket> getAllTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status);
    }
    
    public Page<Ticket> findAllByStatus(TicketStatus status, Pageable pageable) {
        return ticketRepository.findByStatus(status, pageable);
    }
    
    public Page<Ticket> getAllTicketsByStatus(List<TicketStatus> status, Pageable pageable) {
        return ticketRepository.findByStatusIn(status, pageable);
    }
}

