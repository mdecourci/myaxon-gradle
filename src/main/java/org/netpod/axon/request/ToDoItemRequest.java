package org.netpod.axon.request;

public class ToDoItemRequest {
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ToDoItemRequest [description=" + description + "]";
	}

}
