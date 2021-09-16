import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Vamsi Manohar
 *16-Sep-2021 2021
 */

public final class RestaurantService {
	private static List<Restaurant> restaurants = new ArrayList<>();

	
	/**
	 *   Iterating through the available list of restaurants and returning the restaurant which is matching with name
	 * @param restaurantName
	 * @return
	 * @throws restaurantNotFoundException
	 */
	public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
		for (Restaurant restaurant : restaurants) {
			if (restaurant.getName().equalsIgnoreCase(restaurantName)) {
				return restaurant;
			}
		}

		throw new restaurantNotFoundException("The Restaurant named" + restaurantName + " is not Found !!!");

	}

	public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
		Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
		restaurants.add(newRestaurant);
		return newRestaurant;
	}

	public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
		Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
		restaurants.remove(restaurantToBeRemoved);
		return restaurantToBeRemoved;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
}
