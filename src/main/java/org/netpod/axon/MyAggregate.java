package org.netpod.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.commandhandling.annotation.CommandHandlingMember;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.netpod.axon.command.CreateMyAggregateCommand;
import org.netpod.axon.command.DoSomethingCommand;
import org.netpod.axon.command.MyPayloadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAggregate extends AbstractAnnotatedAggregateRoot<String> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAggregate.class);

	@AggregateIdentifier
    private String id;
    
    @CommandHandlingMember
    private MyEntity entity;
    
    @CommandHandler
    public MyAggregate(CreateMyAggregateCommand command) {
		LOGGER.info("Create...");
        apply(new MyAggregateCreatedEvent(IdentifierFactory.getInstance().generateIdentifier()));
    }

    // no-arg constructor for Axon
    MyAggregate() {
    }

    @CommandHandler
    public void doSomething(DoSomethingCommand command) {
		LOGGER.info("doSomething()...command={}", command);
        // do something...
    }

    @CommandHandler
    public void executeMyPayload(MyPayloadType command) {
		LOGGER.info("doSomething()...command={}", command);
        // do something...
    }

    // code omitted for brevity. The event handler for MyAggregateCreatedEvent must set the id field
}
