package application;

import model.entities.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ProgramReservation {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		
		try {
			Reservation customer = addCostumer(sc);
			System.out.println(customer);
			System.out.println();

			updateReservation(customer, sc);
			System.out.println(customer);
		} catch (InputMismatchException e) {
			System.out.println("Error: input invalid");
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}

		
		//Atenção!!! FALTA FINALIZAR COMENTARIOS!!!!
		// !!!!!!!
		
		sc.close();
		
	}

	public static Reservation addCostumer(Scanner sc) throws RuntimeException {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();

		// cria exceção caso seja digitado um numero de quarto invalido
		if (roomNumber <= 0) {
			throw new IllegalArgumentException("Invalid room number");
		}
		System.out.print("Check-in date (dd/MM/yyyy): ");
		sc.nextLine();
		LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
		LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

		
		if (checkIn.isAfter(checkOut)) {
			throw new IllegalArgumentException("Error in reservation: Check-out date must be after check-in");
		}

		return new Reservation(roomNumber, checkIn, checkOut);

	}

	public static void updateReservation(Reservation customer, Scanner sc) throws IllegalArgumentException {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Enter to update the reservation:");
		System.out.print("Check-in date: (dd/MM/yyyy): ");
		LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);
		LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

		if (customer.getCheckIn().isAfter(checkIn) || customer.getCheckOut().isAfter(checkOut)) {
			
			throw new IllegalArgumentException("Error in reservation: Reservation dates for update must be future dates");
		}
		
		customer.UpdateDates(checkIn, checkOut);
	}

}
