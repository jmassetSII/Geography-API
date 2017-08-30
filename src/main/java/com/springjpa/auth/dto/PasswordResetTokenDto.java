package com.springjpa.auth.dto;

import java.util.Calendar;
import java.util.Date;

import com.springjpa.auth.entity.User;

public class PasswordResetTokenDto {
	
	private static final int EXPIRATION = 60 * 24;
	
	private Long id;
	private String token;
	private User user;
	private Date expirationDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public PasswordResetTokenDto(String token, User user) {
		super();
		this.token = token;
		this.user = user;
		this.expirationDate = calculateExpirationDate(EXPIRATION);
	}
	
	public PasswordResetTokenDto(String token) {
		super();
		this.token = token;
		this.expirationDate = calculateExpirationDate(EXPIRATION);
	}
	
	public PasswordResetTokenDto() {
		super();
	}
	
	private Date calculateExpirationDate(final int timeInMinutes) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, timeInMinutes);
		
		return new Date(calendar.getTime().getTime());
	}
	
	

}
