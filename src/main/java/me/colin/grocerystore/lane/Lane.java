package me.colin.grocerystore.lane;

import me.colin.grocerystore.customer.Customer;

import java.util.LinkedList;
import java.util.Queue;

public class Lane implements LaneUseBehavior {

	private static int ids = 1;
	private final int idNumber;

	private final Queue<Customer> line = new LinkedList<>();
	private final LaneType laneType;

	public Lane() {
		this(LaneType.Regular);
	}

	public Lane(LaneType laneType) {
		this.idNumber = ids++;
		this.laneType = laneType;
	}

	/**
	 * Adds the customer to the back of the line.
	 * Calls {@link me.colin.grocerystore.lane.Lane#submitLaneReport(Customer)}.
	 *
	 * @param customer any customer
	 */
	public void add(Customer customer) {
		submitLaneReport(customer);
		customer.setLane(this);
		line.add(customer);
	}

	/**
	 * Removes the customer from the line.
	 * @param customer any customer
	 */
	public void remove(Customer customer) {
		customer.setLane(null);
		line.remove(customer);
	}

	/**
	 * Submits a lane report to the requesting customer.
	 * @param customer any customer
	 */
	public void submitLaneReport(Customer customer) {
		boolean firstCustomerCheck = false;
		double waitTime = 0;

		// checks for wait time
		for (Customer other : line) {
			// accounts for customers currently checking out
			if (!firstCustomerCheck) {
				waitTime += other.getLaneReport().getCheckoutEnd() - customer.getEndShoppingTime();
				firstCustomerCheck = true;
				continue;
			}
			waitTime += checkoutTime(other);
		}

		double finishedShopping = customer.getEndShoppingTime();
		double frontOfLine = customer.getEndShoppingTime() + waitTime;
		double checkoutEnd = frontOfLine + checkoutTime(customer);

		// sets the lane report to the customer
		customer.setLaneReport(new LaneReport(finishedShopping, getCustomersInLine(), waitTime, frontOfLine, checkoutEnd));
	}

	/**
	 * Calculates the checkout time assuming there's no line.
	 * @param customer any customer
	 * @return checkout time (no line)
	 */
	private double checkoutTime(Customer customer) {
		double timeForItems = Math.round(customer.getOrderSize() * laneType.getAverageTimePerItem() * 100) / 100.0;
		return timeForItems + laneType.getPaymentProcessTime();
	}

	/**
	 * @return customers waiting in line to check out
	 */
	public int getCustomersInLine() {
		return line.size();
	}

	/**
	 * @return type of lane
	 */
	public LaneType getType() {
		return laneType;
	}

	@Override
	public boolean isUsable(Customer customer) {
		return laneType.isUsable(customer);
	}

	@Override
	public String toString() {
		return getType().name() + " Lane " + idNumber;
	}
}
