package foundation.cmo.service.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Configuration
@ConfigurationProperties(prefix = "foundation.cmo.service")
public class MServiceProperties {
	
	private MMappers mappers = new MMappers();
	
	
	@Getter 
	@Setter
	public static class MMappers {
		private boolean enable = false;
	}
	
}
