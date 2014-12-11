package org.netpod.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class DoSomethingCommand {

	@TargetAggregateIdentifier
	private String aggregateId;

	// code omitted for brevity
}
