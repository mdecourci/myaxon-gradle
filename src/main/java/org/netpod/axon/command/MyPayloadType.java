package org.netpod.axon.command;

import java.time.ZonedDateTime;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPayloadType {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyPayloadType.class);

	@TargetAggregateIdentifier
	private String aggregateId;

	private String caller;
	private String message;
	private ZonedDateTime transitTime;
	
	public MyPayloadType() {
		super();
		LOGGER.info("Create...");
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ZonedDateTime getTransitTime() {
		return transitTime;
	}

	public void setTransitTime(ZonedDateTime transitTime) {
		this.transitTime = transitTime;
	}

	@Override
	public String toString() {
		return "MyPayloadType [caller=" + caller + ", message=" + message
				+ ", transitTime=" + transitTime + "]";
	}

}
