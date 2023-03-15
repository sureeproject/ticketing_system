package com.dxc.ticket.system.service;

import java.time.LocalDateTime;

import com.dxc.ticket.system.mapper.UserMapper;
import com.dxc.ticket.system.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.ticket.system.dto.UserDto;
import com.dxc.ticket.system.exception.NotFoundException;
import com.dxc.ticket.system.model.User;
import com.dxc.ticket.system.repository.UserRepository;
import com.dxc.ticket.system.mapper.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/*@Autowired
	private UserMapper userMapper;*/

	public UserDto getUserByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("User not found :: " + email));
		return (UserDto)ObjectMapper.copyObject(user, new UserDto());
	}

	public UserDto createUser(UserDto userDto) {
		User user = (User)ObjectMapper.copyObject(userDto,new User());
		user.setCreateTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		user.setRole(Role.ADMIN);
		user = userRepository.save(user);
		return (UserDto)ObjectMapper.copyObject(user,userDto);
	}

	public UserDto updateUser(Long id, UserDto userDto) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		//userMapper.updateFromDto(userDto, user);
		user = (User)ObjectMapper.copyObject(userDto,user);
		
		user = userRepository.save(user);
		return (UserDto)ObjectMapper.copyObject(user,userDto);
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		userRepository.delete(user);
	}
}
