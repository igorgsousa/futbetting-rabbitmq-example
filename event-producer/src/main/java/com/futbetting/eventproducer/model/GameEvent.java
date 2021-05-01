package com.futbetting.eventproducer.model;

import lombok.Data;

@Data
public class GameEvent {
	
	private MatchEventType gameEventType;
	private Team team;
	private Integer minute;

	public GameEvent(MatchEventType gameEventType, Team team, Integer minute) {
		this.gameEventType = gameEventType;
		this.team = team;
		this.minute = minute;
	}
}
