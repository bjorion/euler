package org.jorion.euler.problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Demo class for the logging.
 */
public class Euler000 {

	// --- Constants ---
	private static final Logger LOG = LoggerFactory.getLogger(Euler000.class);

	// --- Methods ---
	public static void main(String[] args) {

		// print internal state
		// LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// StatusPrinter.print(lc);

		MDC.put("main", "Euler000");

		LOG.debug("print some debug");
		LOG.info("print some info");
		LOG.error("print some error");
	}
}
