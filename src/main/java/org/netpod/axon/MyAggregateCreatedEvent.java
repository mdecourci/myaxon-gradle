package org.netpod.axon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAggregateCreatedEvent {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAggregateCreatedEvent.class);

	public MyAggregateCreatedEvent(String identifier) {
		LOGGER.info("Create...identifier={}", identifier);
	}

}
