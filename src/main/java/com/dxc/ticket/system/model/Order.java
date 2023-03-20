package com.dxc.ticket.system.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_order_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_user_id", insertable = false, updatable = false)
	@JsonIgnore
	private User user;

	@ManyToOne
	@JoinColumn(name = "fk_ticket_id", insertable = false, updatable = false)
	@JsonIgnore
	private Ticket ticket;

	@Column(name = "fk_ticket_id")
	private Long ticketId;

	@Column(name = "fk_user_id")
	private Long userId;

	@Column(name = "create_time")
	private LocalDateTime createTime;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

}
