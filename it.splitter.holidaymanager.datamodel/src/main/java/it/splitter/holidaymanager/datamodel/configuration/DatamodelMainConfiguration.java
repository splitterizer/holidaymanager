package it.splitter.holidaymanager.datamodel.configuration;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DatamodelMainConfiguration {

	public DatamodelMainConfiguration() {
		log.info("Setup | Datamodel | main configuration");
	}
	
}
