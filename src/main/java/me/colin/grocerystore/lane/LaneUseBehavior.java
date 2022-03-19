package me.colin.grocerystore.lane;

import me.colin.grocerystore.customer.Customer;

@FunctionalInterface
public interface LaneUseBehavior {

	/**
	 * Checks if the customer is able to use the lane.
	 *
	 * @param customer any customer
	 * @return whether the customer can use the lane
	 */
	boolean isUsable(Customer customer);
}
