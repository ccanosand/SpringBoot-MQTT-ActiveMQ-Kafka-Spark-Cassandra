package com.softweb.iot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kafka.serializer.StringDecoder;

@SpringBootApplication
public class SpringbootKafkaSparkV1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaSparkV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		  String topics = "springtopic";
		final SparkConf sparkConf = new SparkConf()
	            .setMaster("local[*]")
	            .setAppName("testSpringboot");
				
	        // Create the context with 2 seconds batch size
	        final JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(2));
		
	        Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));

	        Map<String, Object> kafkaParams = new HashMap<>();
	        kafkaParams.put("bootstrap.servers", "localhost:9092");
	        kafkaParams.put("key.deserializer", StringDeserializer.class);
	        kafkaParams.put("value.deserializer", StringDeserializer.class);
	        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
	        kafkaParams.put("auto.offset.reset", "earliest"); //earliest , latest
	        kafkaParams.put("enable.auto.commit", false);
	        
	       /* JavaPairInputDStream<String, String> strem = KafkaUtils.createDirectStream(
	        		 	jssc,
	        	        String.class,
	        	        String.class,
	        	        StringDecoder.class,
	        	        StringDecoder.class,
	        	        kafkaParams,
	        	        topicsSet
	        	    );*/
	        		
	        
		jssc.start();              // Start the computation
		jssc.awaitTermination();   // Wait for the computation to terminate
	}
	
	
}
