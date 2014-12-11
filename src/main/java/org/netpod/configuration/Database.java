package org.netpod.configuration;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Named
@ConfigurationProperties("database")
public class Database {
	private String driverClass;
	private String user;
	private String password;
	private String url;
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Database [driverClass=" + driverClass + ", user=" + user
				+ ", password=" + password + ", url=" + url + "]";
	}

}
