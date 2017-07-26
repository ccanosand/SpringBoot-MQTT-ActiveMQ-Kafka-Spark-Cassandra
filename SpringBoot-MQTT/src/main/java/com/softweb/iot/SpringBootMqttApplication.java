package com.softweb.iot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;



@SpringBootApplication
public class SpringBootMqttApplication {

	public static void main(String[] args) {
		/*SpringApplication.run(SpringbootActiveMqApplication.class, args);*/
		
		new SpringApplicationBuilder(SpringBootMqttApplication.class)
					.web(false)
					.run(args);
	}
	
	 	@Bean
	    public MessageChannel mqttInputChannel() {
	    	return new DirectChannel();
	    }
	 	
	 	@Bean
	    public MessageProducer inbound() {
	    	MqttPahoMessageDrivenChannelAdapter adapter =
	    			new MqttPahoMessageDrivenChannelAdapter("tcp://192.168.4.41:1883", "testClient",
	    			                                 "16jnfbtopic");
	    	adapter.setCompletionTimeout(5000);
	    	adapter.setConverter(new DefaultPahoMessageConverter());
	    	adapter.setQos(1);
	    	adapter.setOutputChannel(mqttInputChannel());
	    	return adapter;
	    }

	    @Bean
	    @ServiceActivator(inputChannel = "mqttInputChannel")
	    public MessageHandler handler() {
	    	return new MessageHandler() {

	    		@Override
	    		public void handleMessage(Message<?> message) throws MessagingException {
	    			System.out.println(message.getPayload());
	    			
	    		}

	    	};
	    }
}