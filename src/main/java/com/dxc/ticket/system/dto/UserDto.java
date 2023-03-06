package com.dxc.ticket.system.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data 
public class UserDto {
	private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
