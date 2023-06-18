package foundation.cmo.service.web;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebAutoConfiguration {
	
	@Bean
//	@ConditionalOnProperty(value = "foundation.cmo.service.enable-index", havingValue = "false")
	WebController webController() {
		return new WebController();
	}
}
