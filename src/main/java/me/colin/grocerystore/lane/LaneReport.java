package me.colin.grocerystore.lane;

public class LaneReport {

	private final double finishedShopping;
	private final int customersInLine;
	private final double waitTime;
	private final double frontOfLine;
	private final double checkoutEnd;

	public LaneReport(double finishedShopping, int customersInLine, double waitTime, double frontOfLine, double checkoutEnd) {
		this.finishedShopping = finishedShopping;
		this.customersInLine = customersInLine;
		this.waitTime = waitTime;
		this.frontOfLine = frontOfLine;
		this.checkoutEnd = checkoutEnd;
	}

	public double getFinishedShopping() {
		return finishedShopping;
	}

	public int getCustomersInLine() {
		return customersInLine;
	}

	public double getWaitTime() {
		return waitTime;
	}

	public double getFrontOfLine() {
		return frontOfLine;
	}

	public double getCheckoutEnd() {
		return checkoutEnd;
	}

	@Override
	public String toString() {
		return String.format("[Lane Report] Finished shopping @ %.2f, Front of line @ %.2f, Checkout ended @ %.2f (%s customers in line, %.2f wait time).",
				finishedShopping, frontOfLine, checkoutEnd, customersInLine, waitTime);
	}
}
