package com.dxc.ticket.system.scheduler;

import com.dxc.ticket.system.model.Ticket;
import com.dxc.ticket.system.model.TicketStatus;
import com.dxc.ticket.system.repository.OrderRepository;
import com.dxc.ticket.system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TicketSystemScheduler {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private  OrderRepository orderRepository;

    @Scheduled(cron = "0/1 * * * * *")
    public void updateTicketSystem(){
        List<Ticket> tickets = ticketRepository.findByStatus(TicketStatus.LOCKED);
        if(!tickets.isEmpty()){
           for(Ticket ticket : tickets){
               Duration duration = Duration.between(ticket.getCreateTime(), LocalDateTime.now());
               if (duration.toMinutes() > 10) {
                   ticket.setStatus(TicketStatus.AVALIABLE);
                   ticketRepository.save(ticket);
                  orderRepository.deleteByTicketId(ticket.getId());
               }
           }
        }
    }
}