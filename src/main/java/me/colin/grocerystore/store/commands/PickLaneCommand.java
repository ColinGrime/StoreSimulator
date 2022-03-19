package me.colin.grocerystore.store.commands;

import me.colin.grocerystore.customer.Customer;
import me.colin.grocerystore.lane.Lane;
import me.colin.grocerystore.store.GroceryStore;

public class PickLaneCommand extends Command {

	public PickLaneCommand(GroceryStore store, Customer customer) {
		super(store, customer);
	}

	@Override
	public void execute(double time) {
		Customer customer = getCustomer();
		Lane lane = customer.pickBestLane();
		lane.add(customer);

		log("Finished Shopping - " + customer, time);
		log("Now checking out " + customer.getOrderSize() + " items @ " + lane);

		scheduleNextCommand(customer.getLaneReport().getCheckoutEnd(), new EndCheckoutCommand(getStore(), getCustomer()));
	}
}
