package me.colin.grocerystore.customer;

import me.colin.grocerystore.lane.Lane;
import me.colin.grocerystore.lane.LaneReport;

public interface Customer {

	/**
	 * @return time the customer arrived at the store
	 */
	double getArrivalTime();

	/**
	 * @return amount of items customer is purchasing at the store
	 */
	int getOrderSize();

	/**
	 * @return average time it took customer to select each item
	 */
	double getAverageSelectionTime();

	/**
	 * This time doesn't include checkout time.
	 * @return time it took the customer to shop for all their items
	 */
	double getShoppingTime();

	/**
	 * This time doesn't include checkout time.
	 * @return current simulation time at which the customer was done shopping
	 */
	double getEndShoppingTime();

	/**
	 * The customer chooses the best lane to check out at.
	 *
	 * This is based off how many people are in line
	 * and which lane types are available.
	 *
	 * @return best lane based off the implementation
	 */
	Lane pickBestLane();

	/**
	 * @return lane the customer chose, or null if they haven't chosen a lane
	 */
	Lane getLane();

	/**
	 * @param lane any lane
	 */
	void setLane(Lane lane);

	/**
	 * Gets statistics given when checking out.
	 * @return any lane report
	 */
	LaneReport getLaneReport();

	/**
	 * Sets the statistics given when checking out.
	 * @param laneReport any lane report
	 */
	void setLaneReport(LaneReport laneReport);
}
