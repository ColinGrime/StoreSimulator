package me.colin.grocerystore.store;

import me.colin.grocerystore.customer.Customer;
import me.colin.grocerystore.customer.reader.CustomerReader;
import me.colin.grocerystore.lane.Lane;
import me.colin.grocerystore.lane.LaneType;
import me.colin.grocerystore.lane.factory.LaneFactory;
import me.colin.grocerystore.LogWriter;
import me.colin.grocerystore.store.commands.ArrivalCommand;

import java.util.*;
import java.util.stream.Collectors;

public class GroceryStore {

	private final Set<Customer> customers;
	private final Set<Lane> lanes;
	private final LogWriter logger;

	private final Queue<TimedCommand> timedCommands = new PriorityQueue<>();

	public GroceryStore(CustomerReader customerReader, LaneFactory laneFactory, LogWriter logger) {
		this.customers = customerReader.readCustomers(this);
		this.lanes = laneFactory.createLanes();
		this.logger = logger;
	}

	public void openStore() {
		// add commands that will run at different times in the simulation
		for (Customer customer : customers) {
			double arrivalTime = customer.getArrivalTime();
			timedCommands.add(new TimedCommand(arrivalTime, new ArrivalCommand(this, customer)));
		}

		// run the commands in order
		while (timedCommands.size() > 0) {
			timedCommands.poll().execute();
		}
	}

	public void closeStore() {
		double totalWaitTime = 0;
		for (Customer customer : customers) {
			totalWaitTime += customer.getLaneReport().getWaitTime();
		}

		getLogger().log(String.format("Average wait time: %.2f", totalWaitTime / customers.size()));
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public Set<Lane> getLanes() {
		return lanes;
	}

	public Set<Lane> getAvailableLanes() {
		return lanes.stream().filter(l -> l.getType() != LaneType.Closed).collect(Collectors.toSet());
	}

	public LogWriter getLogger() {
		return logger;
	}

	public Queue<TimedCommand> getTimedCommands() {
		return timedCommands;
	}
}
