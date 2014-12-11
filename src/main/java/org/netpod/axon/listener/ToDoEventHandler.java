package com.ubs.drm.axon.listener;

import org.axonframework.eventhandling.annotation.EventHandler;

import com.ubs.drm.axon.event.ToDoItemCompletedEvent;
import com.ubs.drm.axon.event.ToDoItemCreatedEvent;

public class ToDoEventHandler {
	@EventHandler
	public void handle(ToDoItemCreatedEvent event) {
		System.out.println("We've got something to do: " + event.getDescription() + " (" + event.getTodoId() + ")");
	}

	@EventHandler
	public void handle(ToDoItemCompletedEvent event) {
		System.out.println("We've completed a task: " + event.getTodoId());
	}
}
