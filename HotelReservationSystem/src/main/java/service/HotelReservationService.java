package main.java.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import main.java.model.CustomerType;
import main.java.model.Hotel;

public class HotelReservationService {

	private final List<Hotel> hotels = new ArrayList<>();

    // UC1: Add Hotel
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    // Find Cheapest Hotel
    public Hotel findCheapestHotel(CustomerType customerType, List<LocalDate> dates) {

        return hotels.stream()
                .min(Comparator
                        .comparingInt((Hotel hotel) ->
                                calculateTotalCost(hotel, customerType, dates))
                        .thenComparing(Hotel::getRating, Comparator.reverseOrder()))
                .orElseThrow(() -> new RuntimeException("No hotels available"));
    }

    private int calculateTotalCost(Hotel hotel, CustomerType customerType, List<LocalDate> dates) {
        int total = 0;

        for (LocalDate date : dates) {
            DayOfWeek day = date.getDayOfWeek();
            boolean isWeekend = day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;

            total += isWeekend ?
                    hotel.getWeekendRate(customerType) :
                    hotel.getWeekdayRate(customerType);
        }
        return total;
    }
}

