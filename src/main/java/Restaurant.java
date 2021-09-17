import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public  class Restaurant {
	private String name;
	private String location;
	

	private  LocalTime openingTime;
	private LocalTime closingTime;
	private List<Item> menu = new ArrayList<Item>();

	public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
		this.name = name;
		this.location = location;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	public boolean isRestaurantOpen() {
		LocalTime currentTime = getCurrentTime();
		int isOpen=currentTime.compareTo(openingTime);
		int isClosed = currentTime.compareTo(closingTime);
		return (isOpen>=0 && isClosed<=0);
	

	}

	public LocalTime getCurrentTime() {
		return LocalTime.now();
	}

	public List<Item> getMenu() {

		return this.menu;
	}

	public Item findItemByName(String itemName) {
		for (Item item : menu) {
			if (item.getName().equals(itemName))
				return item;
		}
		return null;
	}

	public void addToMenu(String name, int price) {
		Item newItem = new Item(name, price);
		menu.add(newItem);
	}

	public void removeFromMenu(String itemName) throws itemNotFoundException {

		Item itemToBeRemoved = findItemByName(itemName);
		if (itemToBeRemoved == null)
			throw new itemNotFoundException(itemName);

		menu.remove(itemToBeRemoved);
	}

	public void displayDetails() {
		System.out.println("Restaurant:" + name + "\n" + "Location:" + location + "\n" + "Opening time:" + openingTime
				+ "\n" + "Closing time:" + closingTime + "\n" + "Menu:" + "\n" + getMenu());

	}

	public String getName() {
		return name;
	}

	
	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	/**
	 *  This method returns total order value of all the items in the menu
	 */
	public int getOrderValue(List<Item> items) {
	
		int totalValue=0;
		for(Item item: items)
		{
			totalValue = totalValue + item.getPrice();
		}
		return totalValue;
	}
}
