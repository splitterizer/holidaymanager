package it.splitter.holidaymanager.business.configuration;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BusinessMainConfiguration {

	public BusinessMainConfiguration() {
		log.info("Setup | Business | main configuration");
	}
	
}
