package com.dxc.ticket.system.scheduler;

import com.dxc.ticket.system.model.Order;
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
               Order order = orderRepository.findByTicketId(ticket.getId());
               if(order != null) {
                   Duration duration = Duration.between(order.getCreateTime(), LocalDateTime.now());
                   if (duration.toMinutes() > 10) {
                       ticket.setStatus(TicketStatus.AVALIABLE);
                       ticket.setUpdateTime(LocalDateTime.now());
                       ticketRepository.save(ticket);
                       orderRepository.deleteById(order.getId());
                   }
               }
           }
        }
    }
}
