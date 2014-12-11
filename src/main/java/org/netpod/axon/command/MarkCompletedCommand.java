package com.ubs.drm.axon.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class MarkCompletedCommand {
	@TargetAggregateIdentifier
	private final String todoId;

	public MarkCompletedCommand(String todoId) {
		super();
		this.todoId = todoId;
	}

	public String getTodoId() {
		return todoId;
	}
}
