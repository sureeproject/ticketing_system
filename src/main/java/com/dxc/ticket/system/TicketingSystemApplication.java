package com.dxc.ticket.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//https://stackoverflow.com/questions/35842751/lombok-not-working-with-sts
@SpringBootApplication
//@ComponentScan(basePackages = "com.dxc.ticket.sytem.mapper")
public class TicketingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingSystemApplication.class, args);
	}

}
