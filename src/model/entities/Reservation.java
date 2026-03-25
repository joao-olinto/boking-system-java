package model.entities;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Reservation {

	private int roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;

	// Constructors
	public Reservation() {

	}

	public Reservation(int roomNumber, LocalDate checkIn, LocalDate checkout) {

		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkout;
	}

	// Getters and Setters
	public int getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return this.checkIn;
	}

	public LocalDate getCheckOut() {
		return this.checkOut;
	}

	// Method for calculating customer stay.
	public int duration() {

		// Calculates the duration between the check-in and check-out dates.
		Period periodStay = Period.between(getCheckIn(), getCheckOut());

		// Convert to whole and return the days
		return periodStay.getDays();

	}

	public void UpdateDates(LocalDate checkIn, LocalDate checkOut) {

		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		
		//date formatter
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		StringBuilder sb = new StringBuilder();
		sb.append("Reservation: ").append("Room " + getRoomNumber());
		sb.append(", check-in: " + getCheckIn().format(fmt)).append(", check-out: " + getCheckOut().format(fmt));
		sb.append(", " + duration()).append(" nights");

		return sb.toString();
	}

}
