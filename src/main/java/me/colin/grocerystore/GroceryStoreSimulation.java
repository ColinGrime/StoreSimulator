package me.colin.grocerystore;

import me.colin.grocerystore.customer.reader.CustomerReader;
import me.colin.grocerystore.lane.factory.DefaultLaneFactory;
import me.colin.grocerystore.lane.factory.LaneFactory;
import me.colin.grocerystore.store.GroceryStore;

/*
 * Created by: Colin Grimes
 */
public class GroceryStoreSimulation {

	private static final String inputFile = "arrival";

	public static void main(String[] args) {
		CustomerReader customerReader = new CustomerReader(inputFile);
		if (customerReader.getInputStream() == null) {
			System.out.println("Grocery Store Simulation has now been terminated.");
			return;
		}

		LaneFactory laneFactory = new DefaultLaneFactory(12, 9, 3);
		LogWriter logger = new LogWriter(inputFile);
		GroceryStore store = new GroceryStore(customerReader, laneFactory, logger);

		store.openStore();
		logger.log("");
		logger.log("Input file used: " + inputFile + ".txt");
		store.closeStore();
	}
}
