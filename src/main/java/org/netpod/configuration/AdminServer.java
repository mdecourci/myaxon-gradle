package org.netpod.configuration;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Named
@ConfigurationProperties(prefix="adminConnectors")
public class AdminServer extends ServerType {

}
