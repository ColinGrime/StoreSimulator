package me.colin.grocerystore;

import java.io.*;
import java.time.LocalDate;

public class LogWriter {

	private final File file;

	public LogWriter() {
		this.file = createNewFile(null);
	}

	public LogWriter(String directory) {
		this.file = createNewFile(directory);
	}

	/**
	 * Creates a new log file.
	 * Can be placed in a directory if applicable.
	 *
	 * Log files are named "yyyy-MM-dd-[id].txt"
	 *
	 * @param directory any directory
	 * @return log file
	 */
	private File createNewFile(String directory) {
		String path = "./src/main/resources/output/";
		String date = LocalDate.now().toString();
		if (directory != null) {
			path += directory + "/";
		}

		// finds next available name for the log file
		for (int currentLog=1; ; currentLog++) {
			File file = new File(path + date + "-" + currentLog + ".txt");
			if (file.exists()) {
				continue;
			}

			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Created new log file at location: " + path);
			return file;
		}
	}

	/**
	 * Logs a message to the current log file.
	 * @param message any message
	 */
	public void log(String message) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
			writer.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
