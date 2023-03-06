package com.dxc.ticket.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.ticket.system.model.Ticket;
import com.dxc.ticket.system.model.TicketStatus;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	Page<Ticket> findAll(Pageable pageable);

	List<Ticket> findByStatus(TicketStatus status);

	Page<Ticket> findByStatus(TicketStatus status, Pageable pageable);
	
	Page<Ticket> findByStatusIn(List<TicketStatus> status, Pageable pageable);
	
	Optional<Ticket> findById(Long id);

}
