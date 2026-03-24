package model.entities;

import java.time.LocalDate;

public class Reservation {

	
	private int roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	//Constructors
	public Reservation() {
		
	}
	
	public Reservation(int roomNumber,LocalDate checkIn, LocalDate checkout) {
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkout;
	}
	
	//Getters and Setters
	public int getRoomNumber() {
		return this.roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public LocalDate getCheckIn() {
		return this.checkIn;
	}
	
	public void setCheckIn(LocalDate checkIn) {
		
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return this.checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	
	
}
