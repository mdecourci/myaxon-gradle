package org.netpod.axon;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.auditing.AuditDataProvider;
import org.axonframework.commandhandling.CommandMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAuditDataProvider implements AuditDataProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuditDataProvider.class);

	@Override
	public Map<String, Object> provideAuditDataFor(CommandMessage<?> command) {
		LOGGER.info("provideAuditDataFor(): command={}", command);
		Map<String, Object> map = new HashMap<>();
		map.put(command.getCommandName(), command);
		return map;
	}

}
