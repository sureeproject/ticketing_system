package com.dxc.ticket.system.dto;

import com.dxc.ticket.system.model.Order;
import com.dxc.ticket.system.model.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private TicketStatus status;

}
