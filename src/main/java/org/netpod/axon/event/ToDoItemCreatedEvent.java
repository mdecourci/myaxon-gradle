package com.ubs.drm.axon.event;

public class ToDoItemCreatedEvent {
	private final String todoId;
	private final String description;
	public ToDoItemCreatedEvent(String todoId, String description) {
		super();
		this.todoId = todoId;
		this.description = description;
	}
	public String getTodoId() {
		return todoId;
	}
	public String getDescription() {
		return description;
	}

}
