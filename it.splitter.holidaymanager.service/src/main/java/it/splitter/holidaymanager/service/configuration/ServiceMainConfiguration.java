package it.splitter.holidaymanager.service.configuration;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ServiceMainConfiguration {

	public ServiceMainConfiguration() {
		log.info("Setup | Service | main configuration");
	}
	
}
