package com.accion.local;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.Enumeration;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/poc/publish")
public class MsgProducer {
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	Queue queue;
	
	@GetMapping("/{message}")
	public String publish(@PathVariable("message")
							final String message) {
		
		template.convertAndSend(queue,message);
		return "published the message";
		
	}
	
	@GetMapping("/readIt/{boolParam}")
	public void JustReadIt(@PathVariable("boolParam") final boolean param) {
		
		if(!param) {
			
			//consume the message
			
			template.execute(new SCallback(queue), true);
		}else {
		template.browse(queue,new BrowserCallback() {

			@Override
			public Object doInJms(Session session, QueueBrowser browser) throws JMSException {
				// TODO Auto-generated method stub
				Enumeration<Message> et = browser.getEnumeration();
				while(et.hasMoreElements()) {
						TextMessage tm = (TextMessage)et.nextElement();
						System.out.println(tm);
				}
				return null;
			}
			
		} );
		}
	}

}

class SCallback implements SessionCallback{
	
	private Queue queue;
	public SCallback(Queue queue) {
		this.queue = queue;
	}

	@Override
	public Object doInJms(Session session) throws JMSException {
		// TODO Auto-generated method stub
		MessageConsumer consumer = session.createConsumer((Destination)queue);
		Message msg = consumer.receive();
		System.out.println("--------------------------");
		System.out.println("MEssage consumed");
		System.out.println(msg);
		return null;
	}
	
}
