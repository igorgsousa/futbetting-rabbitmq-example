package com.futbetting.eventproducer.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class Match {
	private String id;
	private Team houseTeam; 	
	private Team visitorTeam; 
}
