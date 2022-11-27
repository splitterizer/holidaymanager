module it.splitterizer.holidayservice.application.service {
	exports it.splitterizer.holidayservice.domain.ports.output.repository;
	exports it.splitterizer.holidayservice.domain.dto;
	exports it.splitterizer.holidayservice.domain.ports.input.service;
	exports it.splitterizer.holidayservice.domain;

	requires it.splitterizer.common.domain;
	requires it.splitterizer.holidayservice.domain.core;
	requires jakarta.validation;
	requires lombok;
	requires org.slf4j;
	requires spring.context;
}