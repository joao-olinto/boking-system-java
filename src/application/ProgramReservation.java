package application;

import model.entities.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ProgramReservation {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		// exception handling
		try {
			Reservation customer = addCustomer(sc);
			System.out.println(customer);
			System.out.println();

			// Update reservation date
			updateReservation(customer, sc);

			System.out.println(customer);
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("Error: input invalid");
		} catch (IllegalArgumentException e) {

			System.out.println(e.getMessage());
		} catch (DateTimeParseException e) {
			System.out.println("Error inserting date: " + e.getMessage());
		} finally {
			sc.close();
		}

	}

	public static Reservation addCustomer(Scanner sc) throws IllegalArgumentException, InputMismatchException {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();

		// Throws an exception if the number entered is less than zero.
		if (roomNumber <= 0) {
			throw new IllegalArgumentException("Invalid room number");
		}

		System.out.print("Check-in date (dd/MM/yyyy): ");
		sc.nextLine();
		LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Check-out date (dd/MM/yyyy): ");
		LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

		// Throws an exception if the entry date is after the exit date.
		if (checkIn.isAfter(checkOut)) {
			throw new IllegalArgumentException("Error in reservation: Check-out date must be after check-in date");
		}

		return new Reservation(roomNumber, checkIn, checkOut);

	}

	public static void updateReservation(Reservation customer, Scanner sc) throws IllegalArgumentException {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Enter to update the reservation:");
		System.out.print("Check-in date (dd/MM/yyyy): ");
		LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Check-out date (dd/MM/yyyy): ");
		LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

		if (checkIn.isAfter(checkOut)) {
			throw new IllegalArgumentException("Error in reservation: Check-out date must be after check-in date");
		}

		// It makes an exception if the update dates are before the registration dates.
		if (customer.getCheckIn().isAfter(checkIn) || customer.getCheckOut().isAfter(checkOut)) {

			throw new IllegalArgumentException(
					"Error in reservation: Reservation dates for update must be future dates");
		}

		customer.updateDates(checkIn, checkOut);
	}

}
