package com.accion.local;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {
	
	@JmsListener(destination = "standalone.queue1")
	public void consumeMsg(String msg) {
		System.out.println("Received message: "+msg);
	}
	public MsgConsumer() {
		System.out.println("inside constrctor of consumer");	
	}

}
