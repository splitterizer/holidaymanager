package it.splitter.holidaymanager.common.configuration;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CommonMainConfiguration {

	public CommonMainConfiguration() {
		log.info("Setup | Common | main configuration");
	}
	
}
