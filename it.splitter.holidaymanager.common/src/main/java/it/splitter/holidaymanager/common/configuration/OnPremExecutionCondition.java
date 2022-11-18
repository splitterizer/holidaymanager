package it.splitter.holidaymanager.common.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnPremExecutionCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getProperty(
				"app.custom.system-props.on-off-feature.onprem-execution-enabled", Boolean.class, false);
	}

}
