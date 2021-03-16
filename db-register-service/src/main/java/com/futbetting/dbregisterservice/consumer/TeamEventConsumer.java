package com.futbetting.dbregisterservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.futbetting.dbregisterservice.model.TeamEvent;

@Component
public class TeamEventConsumer {
	@RabbitListener(queues = {"${queue.register-service.name}"})
	public void receiveTeamEvent(@Payload TeamEvent payload) {
		System.out.println("Body: " + payload);
	}
}
