package it.splitter.holidaymanager.presentation.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ComponentScan("it.splitter.holidaymanager")
public class PresentationMainConfiguration {

	public PresentationMainConfiguration() {
		log.info("Setup | Presentation | main configuration");
	}

}
