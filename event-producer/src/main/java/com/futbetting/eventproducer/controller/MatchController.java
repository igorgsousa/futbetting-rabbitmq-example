package com.futbetting.eventproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futbetting.eventproducer.model.Match;
import com.futbetting.eventproducer.service.MatchService;


@RestController
@RequestMapping("/match")
public class MatchController {
	
	@Autowired 
	private MatchService matchService;
	
	@PostMapping("/start")
	public String startMatch(@RequestBody Match match) {
		return this.matchService.startMatch(match);
	}
}
