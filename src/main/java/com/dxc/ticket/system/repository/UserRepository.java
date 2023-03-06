package com.dxc.ticket.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.ticket.system.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAll();

	Optional<User> findById(Long id);

	void deleteById(Long id);
	
	Optional<User> findByEmail(String email);
	
	
}
