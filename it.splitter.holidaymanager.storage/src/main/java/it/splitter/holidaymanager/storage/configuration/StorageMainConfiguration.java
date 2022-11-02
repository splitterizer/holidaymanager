package it.splitter.holidaymanager.storage.configuration;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StorageMainConfiguration {

	public StorageMainConfiguration() {
		log.info("Setup | Storage | main configuration");
	}
	
}
