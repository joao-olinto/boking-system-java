package application;

import model.entities.Reservation;
import model.exceptions.BusinessException;

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

		try {
		Reservation customer = addCustomer(sc);
		System.out.println(customer);
		System.out.println();

		// Update reservation date
		updateReservation(customer, sc);

		System.out.println(customer);
		}catch(InputMismatchException e) {
			System.out.println("Input error: "+ e.getMessage());
		} catch(DateTimeParseException e) {
			System.out.println("Error inserting date: " + e.getMessage());
		}catch(BusinessException e) {
			 System.out.println(e.getMessage());
		}finally {
			sc.close();
		}
	}

	public static Reservation addCustomer(Scanner sc) throws IllegalArgumentException, InputMismatchException {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();

		System.out.print("Check-in date (dd/MM/yyyy): ");
		sc.nextLine();
		LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Check-out date (dd/MM/yyyy): ");
		LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

		return new Reservation(roomNumber, checkIn, checkOut);
	}

	public static void updateReservation(Reservation customer, Scanner sc) throws IllegalArgumentException {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Enter to update the reservation:");
		System.out.print("Check-in date (dd/MM/yyyy): ");
		LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Check-out date (dd/MM/yyyy): ");
		LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

		customer.updateDates(checkIn, checkOut);
	}

}
