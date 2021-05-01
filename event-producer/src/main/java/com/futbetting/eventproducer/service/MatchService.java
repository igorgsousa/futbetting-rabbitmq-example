package com.futbetting.eventproducer.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futbetting.eventproducer.model.MatchEventType;
import com.futbetting.eventproducer.model.Match;

@Service
public class MatchService {
	
	private Random random;
	private List<MatchEventType> typesDistributed;
	
	@Autowired
	private MatchEventService matchEventService;
	
	@PostConstruct
	public void init() {
		this.random = new Random();
		this.typesDistributed = buildDistributedList(convertFileLinesToModel(getFileLines()));
	}

	public String startMatch(Match match) {
		
		match.setId(String.valueOf(Math.abs(this.random.nextLong())));
		
		this.matchEventService.generateMatchEvents(match, this.typesDistributed);
		
		return match.getId();
	}
	
	private List<MatchEventType> buildDistributedList(List<MatchEventType> types) {
		List<MatchEventType> ret = new ArrayList<>();
		
		types.forEach(e->{
			for(int i = 0; i < e.getPropability(); i++) {
				ret.add(new MatchEventType(e.getId(), e.getPropability(), e.getName(), e.isGoal()));
			}
		});
		
		return ret;
	}

	private List<MatchEventType> convertFileLinesToModel(List<String> dataList) {
		return dataList.stream().map(e-> {
			String[] values = e.split(";");
			
			return new MatchEventType(new Integer(values[0]), new Integer(values[1]), values[2], Boolean.parseBoolean(values[3]));
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
