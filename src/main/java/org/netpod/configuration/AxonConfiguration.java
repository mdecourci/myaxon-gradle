package org.netpod.configuration;

import java.util.Arrays;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.domain.DefaultIdentifierFactory;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.SpringPrototypeAggregateFactory;
import org.axonframework.eventstore.jpa.JpaEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.netpod.core.domain.entity.ToDoItem;

@Configuration
@AnnotationDriven
@EnableTransactionManagement
public class AxonConfiguration {

	@Inject private EntityManagerFactory 	entityManagerFactory;
	@Bean
	public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
		CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
		factory.setCommandBus(commandBus());
		return factory;
	}

	@Bean
	public CommandBus commandBus() {
		SimpleCommandBus commandBus = new SimpleCommandBus();
		commandBus.setHandlerInterceptors(Arrays.asList(new BeanValidationInterceptor()));
//		commandBus.setTransactionManager(new SpringTransactionManager(transactionManager));
		return commandBus;
	}

	@Bean
	public EventBus eventBus() {
		return new SimpleEventBus();
	}
	
	@Bean
	public EntityManagerProvider entityManagerProvider() {
		ContainerManagedEntityManagerProvider entityManagerProvider = new ContainerManagedEntityManagerProvider();
		entityManagerProvider.setEntityManager(entityManagerFactory.createEntityManager());
		return entityManagerProvider;
	}
	
	@Bean
	public EventSourcingRepository<ToDoItem> toDoItemRepository() {
		JpaEventStore eventStore = new JpaEventStore(entityManagerProvider());
		EventSourcingRepository<ToDoItem> repository = new EventSourcingRepository<ToDoItem>(ToDoItem.class, eventStore);
		repository.setEventBus(eventBus());
		return repository;
	}
	
	@Bean
	public AggregateAnnotationCommandHandler<ToDoItem> toDoItemCommandHandler() {
		AggregateAnnotationCommandHandler<ToDoItem> commandHandler = AggregateAnnotationCommandHandler.subscribe(ToDoItem.class, toDoItemRepository(), commandBus());
		return commandHandler;
	}
	
	@Bean
	public SpringPrototypeAggregateFactory<ToDoItem> springPrototypeAggregateFactory() {
		SpringPrototypeAggregateFactory<ToDoItem>  springPrototypeAggregateFactory = new SpringPrototypeAggregateFactory<ToDoItem>();
		springPrototypeAggregateFactory.setPrototypeBeanName("toDoItem");
		springPrototypeAggregateFactory.setBeanName("springPrototypeAggregateFactory");
		return springPrototypeAggregateFactory;
	}

	@Bean
	public IdentifierFactory iIdentifierFactory() {
		return new DefaultIdentifierFactory();
	}
}
