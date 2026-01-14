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

    public int calculateTotalCost(Hotel hotel, CustomerType customerType, List<LocalDate> dates) {
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
    
    public List<Hotel> getHotels() {
        return hotels;
    }
    
    public Hotel findBestRatedHotel(CustomerType customerType, List<LocalDate> dates) {
        return hotels.stream()
                .max(Comparator.comparingInt(Hotel::getRating))
                .orElseThrow(() -> new RuntimeException("No hotels available"));
    }
    
    public void updateRewardRates(String hotelName, int weekdayRate, int weekendRate) {
        Hotel hotel = hotels.stream()
                .filter(h -> h.getName().equalsIgnoreCase(hotelName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Hotel not found: " + hotelName));
        hotel.setRewardRates(weekdayRate, weekendRate);
        System.out.println(hotel.getName() + " Reward Rates Updated: Weekday $" + weekdayRate +
                           ", Weekend $" + weekendRate);
    }

}

