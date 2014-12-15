package org.netpod.axon.resource;

import java.security.Principal;

import javax.inject.Inject;
import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.IdentifierFactory;
import org.netpod.axon.command.CreateToDoItemCommand;
import org.netpod.axon.request.ToDoItemRequest;
import org.netpod.configuration.AdminServer;
import org.netpod.configuration.Database;
import org.netpod.configuration.Resources;
import org.netpod.configuration.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class ToDoResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ToDoResource.class);

	@Inject private CommandGateway commandGateway;
	@Inject private IdentifierFactory identifierFactory;
	@Inject private Resources resources;
	@Inject private Database database;
	@Inject private AdminServer adminServer;
	@Inject private Server server;
	
	@RequestMapping(value = "/items", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasRole('DRM_Sales')")
	@ResponseStatus(value = HttpStatus.OK)
	public void createToDoItem(Principal principal, @RequestBody @Valid ToDoItemRequest request) {
		LOGGER.info("createToDoItem(): principal={}, request={}", principal, request);
		commandGateway.send(new CreateToDoItemCommand(identifierFactory.generateIdentifier(), request.getDescription()));
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DRM_Legal')")
	public String hello() {
		LOGGER.info("hello(): resources={}", resources);
		LOGGER.info("hello(): database={}", database);
		LOGGER.info("hello(): server={}", server);
		LOGGER.info("hello(): adminServer={}", adminServer);
		return "Hello there";
	}

//	@ExceptionHandler
//	public void handleException(Principal principal, Throwable exception) {
//		LOGGER.error("handleException(): principal={}, exception={}", principal, exception);
//	}
}
