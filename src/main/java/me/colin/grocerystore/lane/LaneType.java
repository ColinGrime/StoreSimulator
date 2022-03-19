package me.colin.grocerystore.lane;

import me.colin.grocerystore.customer.Customer;

public enum LaneType implements LaneUseBehavior {

	Regular(0.05, 2.0, c -> true),
	Express(0.10, 1.0, c -> c.getOrderSize() <= 12),
	Closed(0, 0, c -> false);

	private final double averageTimePerTime;
	private final double paymentProcessTime;
	private final LaneUseBehavior useBehavior;

	LaneType(double averageTimePerTime, double paymentProcessTime, LaneUseBehavior useBehavior) {
		this.averageTimePerTime = averageTimePerTime;
		this.paymentProcessTime = paymentProcessTime;
		this.useBehavior = useBehavior;
	}

	/**
	 * @return average time it takes the lane to check out an item
	 */
	public double getAverageTimePerItem() {
		return averageTimePerTime;
	}

	/**
	 * @return time taken to process the payment
	 */
	public double getPaymentProcessTime() {
		return paymentProcessTime;
	}

	@Override
	public boolean isUsable(Customer customer) {
		return useBehavior.isUsable(customer);
	}
}
