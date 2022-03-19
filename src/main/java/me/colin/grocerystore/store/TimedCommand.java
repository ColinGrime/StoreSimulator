package me.colin.grocerystore.store;

import me.colin.grocerystore.store.commands.Command;

public class TimedCommand implements Comparable<TimedCommand> {

	private final double time;
	private final Command command;

	public TimedCommand(double time, Command command) {
		this.time = time;
		this.command = command;
	}

	public void execute() {
		command.execute(time);
	}

	@Override
	public int compareTo(TimedCommand other) {
		return Double.compare(this.time, other.time);
	}
}
