package com.dapr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.Metadata;
import static java.util.Collections.singletonMap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PubSubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubSubApplication.class, args);

	final Logger log = LoggerFactory.getLogger(PubSubApplication.class);
	String MESSAGE_TTL_IN_SECONDS = "1000";
	String TOPIC_NAME = "orders";
	String PUBSUB_NAME = "kafkapubsubnoauth";

	while(true) {
		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Random random = new Random();
		int orderId = random.nextInt(1000-1) + 1;
		DaprClient client = new DaprClientBuilder().build();
		//Using Dapr SDK to publish a topic
		//search for dapr sdk to know more
		client.publishEvent(
				PUBSUB_NAME,
				TOPIC_NAME,
				orderId,
				singletonMap(Metadata.TTL_IN_SECONDS, MESSAGE_TTL_IN_SECONDS)).block();
		log.info("Published data:" + orderId);
	}

}
}
