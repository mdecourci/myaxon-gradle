package org.netpod.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.netpod.axon.command.DoSomethingInEntityCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyEntity extends AbstractAnnotatedEntity {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyEntity.class);

    @CommandHandler
    public void handleSomeCommand(DoSomethingInEntityCommand command) {
    	LOGGER.info("handleSomeCommand(): command={}", command);
        // do something
    }
}
