package com.futbetting.eventproducer.model;

import lombok.Data;

@Data
public class GameEventType {
	
	private Integer id;
	private Integer propability;
	private String name;

	public GameEventType(Integer id, Integer propability, String name) {
		this.id = id;
		this.propability = propability;
		this.name = name;
	} 
}
