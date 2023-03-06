package com.dxc.ticket.system.exception;

public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8508751845666784467L;

	public NotFoundException(String message) {
		super(message);
	}
}
