package org.netpod.axon.configuration;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Named
@ConfigurationProperties("resources")
public class Resources {
	private String mediaType;
	private String root;
	private String apiKey;
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	@Override
	public String toString() {
		return "Resources [mediaType=" + mediaType + ", root=" + root
				+ ", apiKey=" + apiKey + "]";
	}

}
