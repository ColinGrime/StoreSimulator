package me.colin.grocerystore.store.commands;

import me.colin.grocerystore.customer.Customer;
import me.colin.grocerystore.lane.Lane;
import me.colin.grocerystore.store.GroceryStore;

public class EndCheckoutCommand extends Command {

	public EndCheckoutCommand(GroceryStore store, Customer customer) {
		super(store, customer);
	}

	@Override
	public void execute(double time) {
		Customer customer = getCustomer();
		Lane lane = customer.getLane();
		lane.remove(customer);

		log("Finished Checkout - " + customer + " on " + lane, time);
		log(String.valueOf(customer.getLaneReport()));
	}
}
