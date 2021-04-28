package com.futbetting.eventproducer.model;

import lombok.Data;

@Data
public class Team {
	private Integer id;
	private String name;

	public Team(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
