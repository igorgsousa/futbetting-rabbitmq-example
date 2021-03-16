package com.futbetting.dbregisterservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TeamEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final Integer event;
	private final Integer team;

	public TeamEvent(Integer event, Integer team) {
		this.event = event;
		this.team = team;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEvent() {
		return event;
	}

	public Integer getTeam() {
		return team;
	}
}
