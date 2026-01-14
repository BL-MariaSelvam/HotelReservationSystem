package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import main.java.model.CustomerType;
import main.java.model.Hotel;
import main.java.service.HotelReservationService;

public class HotelReservationMain {

    public static void main(String[] args) {

        String input = "Regular: 16Mar2020(mon), 17Mar2020(tues), 18Mar2020(wed)";

        HotelReservationService service = new HotelReservationService();

        service.addHotel(new Hotel("Lakewood", 3, 110, 90, 80, 80));
        service.addHotel(new Hotel("Bridgewood", 4, 160, 60, 110, 50));
        service.addHotel(new Hotel("Ridgewood", 5, 220, 150, 100, 40));

        
        System.out.println("Hotels added successfully");
        CustomerType customerType = CustomerType.valueOf(
                input.split(":")[0].trim().toUpperCase()
        );

        List<LocalDate> dates = Arrays.stream(input.split(":")[1].split(","))
                .map(date -> date.replaceAll("\\(.*?\\)", "").trim())
                .map(date -> LocalDate.parse(date,
                        DateTimeFormatter.ofPattern("ddMMMyyyy")))
                .collect(Collectors.toList());

        Hotel cheapestHotel = service.findCheapestHotel(customerType, dates);

        System.out.println(cheapestHotel.getName());
    }
}

