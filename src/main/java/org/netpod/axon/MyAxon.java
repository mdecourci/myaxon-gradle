package org.netpod.axon;

import java.time.ZonedDateTime;

import org.axonframework.auditing.AuditingInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.GatewayProxyFactory;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.netpod.axon.command.MyPayloadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAxon {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAxon.class);

	public static void main(String[] args) {
		LOGGER.info("Create command bus");
		CommandBus commandBus = new SimpleCommandBus();
		EventStore eventStore = new EventStore() {
			
			@Override
			public DomainEventStream readEvents(String type, Object identifier) {
				LOGGER.info("readEvents(): type={}, identifier={}", type, identifier);
				return null;
			}
			
			@Override
			public void appendEvents(String type, DomainEventStream events) {
				LOGGER.info("appendEvents(): type={}", type);
			}
		};
		EventSourcingRepository<MyAggregate> repository = new EventSourcingRepository<>(MyAggregate.class, eventStore);
		// to generate the command handlers for this aggregate:
		AggregateAnnotationCommandHandler<MyAggregate> handler = AggregateAnnotationCommandHandler.subscribe(MyAggregate.class, repository , commandBus);
		
		AuditingInterceptor auditingInterceptor = new AuditingInterceptor();
		auditingInterceptor.setAuditDataProvider(new MyAuditDataProvider());
		// To create an instance:
		LOGGER.info("Create gateway");
		GatewayProxyFactory factory = new GatewayProxyFactory(commandBus);
		MyGateway myGateway = factory.createGateway(MyGateway.class);
		
		MyPayloadType command = new MyPayloadType();
		command.setCaller("Fred Jones");
		command.setMessage("Hello!");
		command.setTransitTime(ZonedDateTime.now());
		
		myGateway.sendCommand(command);
	}

}
