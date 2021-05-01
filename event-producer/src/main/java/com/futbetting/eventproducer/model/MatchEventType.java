package com.futbetting.eventproducer.model;

import lombok.Data;

@Data
public class MatchEventType {
	
	private Integer id;
	private Integer propability;
	private String name;
	private boolean goal;
	
	public MatchEventType(Integer id, Integer propability, String name, Boolean goal) {
		this.id = id;
		this.propability = propability;
		this.name = name;
		this.goal = goal;
	} 
}
