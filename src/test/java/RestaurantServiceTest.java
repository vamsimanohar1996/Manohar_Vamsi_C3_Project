import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Vamsi Manohar 16-Sep-2021 2021
 */

final class RestaurantServiceTest {

	 RestaurantService service = new RestaurantService();
	 Restaurant restaurant;

	/**
	 * Adding some static restaurant data which will be used across all the test
	 * cases
	 */
	@BeforeEach
	public void addRestaurantDetails() {
		

		restaurant = service.addRestaurant("Farzi Cafe", "Hyderabad", LocalTime.parse("10:00:00"),
				LocalTime.parse("23:30:00"));
		service.addRestaurant("Fat Pigeon", "Mumbai", LocalTime.parse("11:00:00"), LocalTime.parse("22:30:00"));
		restaurant.addToMenu("Chicken Biryani", 200);
		restaurant.addToMenu("Veg platter", 750);

	}
	
	

	// >>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Test
	public void searching_for_existing_restaurant_should_return_expected_restaurant_object()
			throws restaurantNotFoundException {
		String restuarantName = service.findRestaurantByName("Farzi Cafe").getName();
		assertEquals("Farzi Cafe", restuarantName);

	}

	@Test
	public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {

		assertThrows(restaurantNotFoundException.class, () -> {
			service.findRestaurantByName("Cafe");
		});

	}
	
	
	
	// <<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING
	// RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	@Test
	public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.removeRestaurant("Farzi Cafe");
		assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
	}

	@Test
	public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

		assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
	}

	@Test
	public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {
		int initialNumberOfRestaurants = service.getRestaurants().size();
		service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
		assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
	}
	// <<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING
	// RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}