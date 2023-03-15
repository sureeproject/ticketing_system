package com.dxc.ticket.system;

import com.dxc.ticket.system.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts

@SpringBootApplication
public class TicketingSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(TicketingSystemApplication.class, args);
	}

}
