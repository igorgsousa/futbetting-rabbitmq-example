package com.futbetting.eventproducer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.futbetting.eventproducer.model.GameEventType;
import com.futbetting.eventproducer.model.Team;

@SpringBootApplication
public class EventProducerApplication {
	
	private Random random;
	
	public EventProducerApplication() {
		this.random = new Random();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EventProducerApplication.class, args);
	}
	
	@EventListener(classes = ApplicationReadyEvent.class)
	public void onApplicationReady() {
		
		List<String> dataList = getFileLines();
		
		List<GameEventType> types = convertFileLinesToModel(dataList);
		
		List<GameEventType> typesDistributed = buildDistributedList(types);
		
		List<Team> teams = Arrays.asList(new Team(1, "Flamengo"), new Team(2, "River Plate"));
		
		
		for(int minute = 1; minute <= 90; minute++) {
			
			waitOneSecond();
			
			Team team = drawTeam(teams);
			GameEventType eventType = drawGameEventType(typesDistributed);
			
			System.out.println(String.format("%d' Team %s : %s", minute, team.getName(), eventType.getName()));

			
			
		}
		
	}

	private GameEventType drawGameEventType(List<GameEventType> types) {
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

	private List<GameEventType> buildDistributedList(List<GameEventType> types) {
		List<GameEventType> ret = new ArrayList<>();
		
		types.forEach(e->{
			for(int i = 0; i < e.getPropability(); i++) {
				ret.add(new GameEventType(e.getId(), e.getPropability(), e.getName()));
			}
		});
		
		return ret;
	}

	private List<GameEventType> convertFileLinesToModel(List<String> dataList) {
		return dataList.stream().map(e-> {
			String[] values = e.split(";");
			
			return new GameEventType(new Integer(values[0]), new Integer(values[1]), values[2]);
		}).collect(Collectors.toList());
	}

	private List<String> getFileLines() {
		try {
			return  IOUtils.readLines(new FileReader("src/main/resources/data.csv"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed read data from csv");
			throw new RuntimeException(e);
		}
	}

}
