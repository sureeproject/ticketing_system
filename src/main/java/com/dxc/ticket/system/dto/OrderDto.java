package com.dxc.ticket.system.dto;


import lombok.Data;

@Data
public class OrderDto {

    private Long id;
    private UserDto user;
    private TicketDto ticket;
}
