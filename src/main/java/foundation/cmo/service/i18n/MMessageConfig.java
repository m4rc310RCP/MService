package foundation.cmo.service.i18n;

import java.util.Locale;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import foundation.cmo.service.configurations.MServiceProperties;
import io.leangen.graphql.metadata.messages.MessageBundle;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(MServiceProperties.class)
@ConditionalOnProperty(name = "foundation.cmo.service.enable-messages", havingValue = "true", matchIfMissing = true)
public class MMessageConfig {
	
	@Bean
	MessageBundle messageBundle() {
		return key -> getString(key);
	}
	
	public String getString(String pattern, Object... args) {
		try {
			return messageSource().getMessage(pattern, args, Locale.forLanguageTag("pt-BR"));
		} catch (Exception e) {
			String regex = "[^a-zA-Z0-9_]+";
			return pattern.replaceAll(regex, "_");
		}
	}

	
	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("pt-BR"));
		return sessionLocaleResolver;
	}

	@Bean("messageSource")
	MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages/message");
		return messageSource;
	}
}
