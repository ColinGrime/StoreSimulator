package me.colin.grocerystore.customer;

import me.colin.grocerystore.lane.LaneReport;
import me.colin.grocerystore.store.GroceryStore;
import me.colin.grocerystore.lane.Lane;
import me.colin.grocerystore.lane.LaneType;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RegularCustomer implements Customer {

	private static int ids = 1;
	private final int idNumber;

	private final GroceryStore store;
	private final double arrivalTime;
	private final int orderSize;
	private final double averageSelectionTime;

	private Lane lane;
	private LaneReport laneReport;

	public RegularCustomer(GroceryStore store, double arrivalTime, int orderSize, double averageSelectionTime) {
		this.idNumber = ids++;
		this.store = store;
		this.arrivalTime = arrivalTime;
		this.orderSize = orderSize;
		this.averageSelectionTime = averageSelectionTime;
	}

	@Override
	public double getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public int getOrderSize() {
		return orderSize;
	}

	@Override
	public double getAverageSelectionTime() {
		return averageSelectionTime;
	}

	@Override
	public double getShoppingTime() {
		return getOrderSize() * getAverageSelectionTime();
	}

	@Override
	public double getEndShoppingTime() {
		return getArrivalTime() + getShoppingTime();
	}

	@Override
	public Lane pickBestLane() {
		Customer customer = this;
		Set<Lane> lanes = store.getAvailableLanes().stream().filter(l -> l.isUsable(customer)).collect(Collectors.toSet());

		// finds the lanes with the lowest amount of customers in them
		int minCustomersInLine = lanes.stream().mapToInt(Lane::getCustomersInLine).min().orElse(0);
		List<Lane> minLanes = lanes.stream().filter(l -> l.getCustomersInLine() == minCustomersInLine).collect(Collectors.toList());

		// return the lane with the lowest customers
		if (minLanes.size() == 1) {
			return minLanes.get(0);
		}

		// return an express lane
		for (Lane lane : minLanes) {
			if (lane.getType() == LaneType.Express) {
				return lane;
			}
		}

		// return random lane with the lowest customers
		return minLanes.get(new Random().nextInt(minLanes.size()));
	}

	@Override
	public Lane getLane() {
		return lane;
	}

	@Override
	public void setLane(Lane lane) {
		this.lane = lane;
	}

	@Override
	public LaneReport getLaneReport() {
		return laneReport;
	}

	@Override
	public void setLaneReport(LaneReport laneReport) {
		this.laneReport = laneReport;
	}

	@Override
	public String toString() {
		return "Customer " + idNumber;
	}
}
