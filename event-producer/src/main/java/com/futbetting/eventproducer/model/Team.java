package com.futbetting.eventproducer.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Team {
	private Integer id;
	private String name;
	
	public Team() {
	}

	public Team(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
