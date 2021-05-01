package com.futbetting.eventproducer.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.futbetting.eventproducer.model.MatchEventType;
import com.futbetting.eventproducer.model.Match;
import com.futbetting.eventproducer.model.Team;

@Service
public class MatchEventService {
	
	private Random random;
	
	@PostConstruct
	public void init() {
		this.random = new Random();
	}

	@Async
	public void generateMatchEvents(Match match , List<MatchEventType> typesDistributed) {
		for(int minute = 1; minute <= 90; minute++) {
			
			waitOneSecond();
			
			Team team = drawTeam(Arrays.asList(match.getHouseTeam(), match.getVisitorTeam()));
			MatchEventType eventType = drawGameEventType(typesDistributed);
			
			System.out.println(String.format("%s - %d' Team %s : %s", match.getId(), minute, team.getName(), eventType.getName()));
		}
	}
	
	private MatchEventType drawGameEventType(List<MatchEventType> types) {
		Collections.shuffle(types);
		return types.get(this.random.nextInt(100));
	}

	private Team drawTeam(List<Team> teams) {
		return teams.get(this.random.nextInt(2));
	}

	private void waitOneSecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
