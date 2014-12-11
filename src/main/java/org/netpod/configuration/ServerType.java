package org.netpod.configuration;

public abstract class ServerType {
	private String type;
	private String port;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "ServerType [type=" + type + ", port=" + port + "]";
	}

}
