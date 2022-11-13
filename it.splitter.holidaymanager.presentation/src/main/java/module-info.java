module it.splitter.holidaymanager.presentation {
	exports it.splitter.holidaymanager.presentation;
	exports it.splitter.holidaymanager.presentation.configuration;

	requires lombok;
	requires org.slf4j;
	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	
	opens it.splitter.holidaymanager.presentation to spring.core, spring.beans, spring.context;
	opens it.splitter.holidaymanager.presentation.configuration to spring.core, spring.beans, spring.context;
}