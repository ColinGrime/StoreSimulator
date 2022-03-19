package me.colin.grocerystore.store.commands;

import me.colin.grocerystore.customer.Customer;
import me.colin.grocerystore.store.GroceryStore;

public class ArrivalCommand extends Command {

	public ArrivalCommand(GroceryStore store, Customer customer) {
		super(store, customer);
	}

	@Override
	public void execute(double time) {
		log("Arrival - " + getCustomer(), time);
		scheduleNextCommand(getCustomer().getEndShoppingTime(), new PickLaneCommand(getStore(), getCustomer()));
	}
}
