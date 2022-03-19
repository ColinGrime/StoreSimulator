package me.colin.grocerystore.store.commands;

import me.colin.grocerystore.customer.Customer;
import me.colin.grocerystore.store.GroceryStore;
import me.colin.grocerystore.store.TimedCommand;

public abstract class Command {

	private final GroceryStore store;
	private final Customer customer;

	public Command(GroceryStore store, Customer customer) {
		this.store = store;
		this.customer = customer;
	}

	/**
	 * Executes a store command.
	 */
	public abstract void execute(double time);

	/**
	 * Schedules the next command to run.
	 */
	protected void scheduleNextCommand(double time, Command command) {
		getStore().getTimedCommands().add(new TimedCommand(time, command));
	}

	/**
	 * Logs a message to the logger.
	 * @param message any message
	 */
	protected void log(String message) {
		log(message, -1);
	}

	/**
	 * Logs a message to the logger, including
	 * the current time if applicable.
	 *
	 * @param message any message
	 * @param logTime the log time, or -1 to not log the time
	 */
	protected void log(String message, double logTime) {
		if (logTime == -1) {
			store.getLogger().log(message);
		} else {
			store.getLogger().log(String.format("[%.2f] " + message, logTime));
		}
	}

	public GroceryStore getStore() {
		return store;
	}

	public Customer getCustomer() {
		return customer;
	}
}
