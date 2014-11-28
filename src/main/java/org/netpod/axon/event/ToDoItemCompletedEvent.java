package org.netpod.axon.event;

public class ToDoItemCompletedEvent {
	private final String todoId;

	public ToDoItemCompletedEvent(String todoId) {
		super();
		this.todoId = todoId;
	}

	public String getTodoId() {
		return todoId;
	}
}
