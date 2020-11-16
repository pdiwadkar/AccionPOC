package com.accion.local;

import java.time.LocalDateTime;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;

@SpringBootApplication
@EnableJms
public class UrgentPocApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(UrgentPocApplication.class, args);
	}
		
	

}
