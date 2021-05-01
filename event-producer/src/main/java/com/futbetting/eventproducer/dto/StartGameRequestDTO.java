package com.futbetting.eventproducer.dto;

import com.futbetting.eventproducer.model.Team;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class StartGameRequestDTO {
	private Team houseTeam; 	
	private Team visitorTeam; 	
}
