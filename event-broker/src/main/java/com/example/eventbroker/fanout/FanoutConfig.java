package com.example.eventbroker.fanout;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import com.example.eventbroker.queues.Queues;

@Configuration
@EnableRabbit
public class FanoutConfig implements RabbitListenerConfigurer {
	
	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange(Queues.SIMPLE_FANOUT_QUEUE);
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		// TODO Auto-generated method stub
		registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
	}

	@Bean
	public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(new MappingJackson2MessageConverter());
		return factory;
	}
}
