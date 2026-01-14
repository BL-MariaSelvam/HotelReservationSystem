package main.java;

import main.java.model.CustomerType;
import main.java.model.Hotel;
import main.java.service.HotelReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HotelReservationMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelReservationService service = new HotelReservationService();

        int option;
        do {
            System.out.println("\n=== Hotel Reservation System Menu ===");
            System.out.println("1. Add Hotel");
            System.out.println("2. Find Cheapest Hotel for a Date Range");
            System.out.println("3. Exit");
            System.out.print("Enter your option: ");
            option = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter Hotel Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Hotel Rating: ");
                    int rating = sc.nextInt();

                    System.out.print("Enter Weekday Rate (Regular): ");
                    int weekdayRegular = sc.nextInt();

                    System.out.print("Enter Weekend Rate (Regular): ");
                    int weekendRegular = sc.nextInt();

                    System.out.print("Enter Weekday Rate (Rewards): ");
                    int weekdayRewards = sc.nextInt();

                    System.out.print("Enter Weekend Rate (Rewards): ");
                    int weekendRewards = sc.nextInt();
                    sc.nextLine(); // consume newline

                    Hotel hotel = new Hotel(name, rating, weekdayRegular, weekendRegular,
                                            weekdayRewards, weekendRewards);
                    service.addHotel(hotel);
                    System.out.println("Hotel added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Customer Type (Regular/Rewards): ");
                    String customerInput = sc.nextLine().trim().toUpperCase();
                    CustomerType customerType = CustomerType.valueOf(customerInput);

                    System.out.print("Enter Dates (ddMMMyyyy) separated by commas, e.g. 10Sep2020,11Sep2020: ");
                    String dateInput = sc.nextLine();

                    List<LocalDate> dates = Arrays.stream(dateInput.split(","))
                            .map(String::trim)
                            .map(date -> LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMMyyyy")))
                            .toList();

                    Hotel cheapestHotel = service.findCheapestHotel(customerType, dates);
                    int totalCost = service.calculateTotalCost(cheapestHotel, customerType, dates);

                    System.out.println("Cheapest Hotel: " + cheapestHotel.getName());
                    System.out.println("Total Rates: $" + totalCost);
                    break;

                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (option != 3);

        sc.close();
    }
}
