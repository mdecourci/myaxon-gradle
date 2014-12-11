package org.netpod.axon.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoSomethingInEntityCommand {
	private static final Logger LOGGER = LoggerFactory.getLogger(DoSomethingInEntityCommand.class);

	public DoSomethingInEntityCommand() {
		super();
		LOGGER.info("Create...");
	}

}
