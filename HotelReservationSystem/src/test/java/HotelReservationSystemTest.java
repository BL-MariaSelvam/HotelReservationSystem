package test.java;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.model.CustomerType;
import main.java.model.Hotel;
import main.java.service.HotelReservationService;

public class HotelReservationSystemTest {
	@Test
	public void givenHotel_WhenAdded_ShouldBeAbleToFindCheapestHotel() {

	    HotelReservationService service = new HotelReservationService();

	    service.addHotel(new Hotel("Lakewood", 3, 110, 90, 80, 80));

	    List<LocalDate> dates = List.of(LocalDate.of(2020, 3, 16));

	    Hotel cheapestHotel = service.findCheapestHotel(CustomerType.REGULAR, dates);

	    assertEquals("Lakewood", cheapestHotel.getName());
	}
}
