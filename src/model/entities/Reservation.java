package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exceptions.BusinessException;

public class Reservation {

	private int roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;

	// Constructors
	public Reservation() {

	}

	public Reservation(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
		
		validateNumberAndDates(roomNumber, checkIn, checkOut);
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;

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

		// Casting for integer
		return (int) ChronoUnit.DAYS.between(getCheckIn(), getCheckOut());

	}

	/*
	 * Update customer check-in and check-out dates. The only way to modify the
	 * check-in e check-out dates.
	 */
	public void updateDates(LocalDate checkIn, LocalDate checkOut) throws BusinessException {

		
		validateNumberAndDates(checkIn, checkOut);
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	// methods of exception handling
	private void validateNumberAndDates(int roomNumber, LocalDate checkIn, LocalDate checkOut)
			throws BusinessException {

		// Throws an exception if the number entered is less than zero.
		if (roomNumber <= 0) {
			throw new BusinessException("Error in Reservartion: Invalid room number");
		}

		// Throws an exception if the entry date is after the exit date.
		if (!checkIn.isBefore(checkOut)) {
			throw new BusinessException("Error in reservation: Check-out date must be after check-in date");
		}

		if (checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())) {
			throw new BusinessException("Error in reservation: Reservation dates for update must be future dates");
		}

	}

	// Overload that receives two LocalDate parameters.
	private void validateNumberAndDates(LocalDate checkIn, LocalDate checkOut) throws BusinessException {

		if (!checkIn.isBefore(checkOut)) {
			throw new BusinessException("Error in reservation: Check-out date must be after check-in date");
		}

		if (checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())) {
			throw new BusinessException("Error in reservation: Reservation dates for update must be future dates");
		}

	}

	@Override
	public String toString() {

		// date formatter
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Reservation: ").append("Room " + getRoomNumber());
		sb.append(", check-in: " + getCheckIn().format(fmt)).append(", check-out: " + getCheckOut().format(fmt));
		sb.append(", " + duration()).append(" nights");

		return sb.toString();
	}

}
