import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantTest {
	Restaurant restaurant;

	RestaurantService service = new RestaurantService();

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

	// >>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	// -------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN
	// INTO ANY TROUBLE
	@Test
	public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {

		restaurant.setOpeningTime(LocalTime.now().minusMinutes(10));
		restaurant.setClosingTime(LocalTime.now().plusMinutes(10));
		assertTrue(restaurant.isRestaurantOpen());

	}

	 @Test
	public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
		    restaurant.setOpeningTime(LocalTime.now().plusMinutes(10));
			restaurant.setClosingTime(LocalTime.now().minusMinutes(10));
		    assertFalse(restaurant.isRestaurantOpen());

	}

	// <<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Test
	public void adding_item_to_menu_should_increase_menu_size_by_1() {


		int initialMenuSize = restaurant.getMenu().size();
		restaurant.addToMenu("Sizzling brownie", 319);
		assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


		int initialMenuSize = restaurant.getMenu().size();
		restaurant.removeFromMenu("Veg platter");
		assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
	}

	@Test
	public void removing_item_that_does_not_exist_should_throw_exception() {


		assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
	}
	// <<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}