package com.dxc.ticket.system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dxc.ticket.system.dto.TicketDto;
import com.dxc.ticket.system.mapper.ObjectMapper;
import com.dxc.ticket.system.model.Role;
import com.dxc.ticket.system.model.User;
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

    public List<TicketDto> findAll(Pageable pageable) {
        Page<Ticket> ticketsPage = ticketRepository.findByStatus(TicketStatus.AVALIABLE,pageable);
        List<TicketDto> list = new ArrayList<>();
        for (Ticket ticket : ticketsPage.getContent()) {
            TicketDto ticketDto = (TicketDto) ObjectMapper.copyObject(ticket,new TicketDto());
            list.add(ticketDto);
        }
        return  list;
    }
    
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    public Ticket createTicket(TicketDto ticketDto) {
        Ticket ticket = (Ticket) ObjectMapper.copyObject(ticketDto,new Ticket());
        ticket.setCreateTime(LocalDateTime.now());
        ticket.setUpdateTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.AVALIABLE);
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setName(ticket.getName());
        existingTicket.setPrice(ticket.getPrice());
        existingTicket.setStatus(ticket.getStatus());
        existingTicket.setUpdateTime(LocalDateTime.now());
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

