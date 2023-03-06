package com.dxc.ticket.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.ticket.system.dto.UserDto;
import com.dxc.ticket.system.exception.NotFoundException;
import com.dxc.ticket.system.model.User;
import com.dxc.ticket.system.repository.UserRepository;
import com.dxc.ticket.sytem.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	public UserDto getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found" + id));
		return userMapper.toDto(user);
	}

	public UserDto createUser(UserDto userDto) {
		User user = userMapper.toEntity(userDto);
		user = userRepository.save(user);
		return userMapper.toDto(user);
	}

	public UserDto updateUser(Long id, UserDto userDto) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		userMapper.updateFromDto(userDto, user);
		user = userRepository.save(user);
		return userMapper.toDto(user);
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		userRepository.delete(user);
	}
}
