package com.bigbanana.lab.Session8.dto;

import java.util.Map;

public class BaseDTO {
	private Long id;

	private Map<String,String> feature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getFeature() {
		return feature;
	}

	public void setFeature(Map<String, String> feature) {
		this.feature = feature;
	}
}
