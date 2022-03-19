package me.colin.grocerystore.customer.reader;

import me.colin.grocerystore.store.GroceryStore;
import me.colin.grocerystore.customer.Customer;
import me.colin.grocerystore.customer.RegularCustomer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomerReader {

	private final String path;

	public CustomerReader(String path) {
		this.path = path.endsWith(".txt") ? path : path + ".txt";
	}

	/**
	 * @return InputStream of the instance's path field
	 */
	public InputStream getInputStream() {
		InputStream stream = getClass().getResourceAsStream("/input/" + path);
		if (stream == null) {
			System.out.println("The path \"" + path + "\" does not exist.");
			return null;
		}
		return stream;
	}

	/**
	 * Gets all the customers in the store
	 * based off the path's input file.
	 *
	 * @param store any store
	 * @return customers in the store
	 */
	public Set<Customer> readCustomers(GroceryStore store) {
		Set<Customer> customers = new HashSet<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream()))) {
			reader.lines().map(c -> parseCustomer(store, c)).filter(Objects::nonNull).forEach(customers::add);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(customers.size() + " customers have been read.");
		return customers;
	}

	/**
	 * Parses customer string into a {@link me.colin.grocerystore.customer.RegularCustomer}.
	 * @param store any store
	 * @param customerString customer string
	 * @return new RegularCustomer object if parsing was successful
	 */
	private Customer parseCustomer(GroceryStore store, String customerString) {
		// check for tabs
		Customer customer = parseCustomer(store, customerString.split("\t"));
		if (customer != null) return customer;

		// check for spaces
		customer = parseCustomer(store, customerString.split(" "));
		if (customer != null) return customer;

		// check for colons
		customer = parseCustomer(store, customerString.split(":"));
		return customer;
	}

	/**
	 * Parses customer string arguments into a {@link me.colin.grocerystore.customer.RegularCustomer}.
	 * @param store any store
	 * @param args customer string arguments
	 * @return new RegularCustomer object if parsing was successful
	 */
	private Customer parseCustomer(GroceryStore store, String[] args) {
		if (args.length < 3) {
			return null;
		}

		try {
			double arrivalTime = Double.parseDouble(args[0]);
			int orderSize = Integer.parseInt(args[1]);
			double averageSelectionTime = Double.parseDouble(args[2]);

			return new RegularCustomer(store, arrivalTime, orderSize, averageSelectionTime);
		} catch (NumberFormatException ex) {
			return null;
		}
	}
}
