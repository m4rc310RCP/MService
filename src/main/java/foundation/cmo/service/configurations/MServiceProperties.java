package foundation.cmo.service.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Configuration
@ConfigurationProperties(prefix = "foundation.cmo.service")
@ConfigurationPropertiesScan
public class MServiceProperties {
	
	private boolean enableIndex    = true;
	private boolean enableMessages = true;
	private MMappers mappers = new MMappers();
	private MSales sales = new MSales();
	
	@Getter 
	@Setter
	public static class MMappers {
		private boolean enable = false;
	}
	
	
	@Getter 
	@Setter
	public static class MSales {
		private boolean enableEanApi = false;		
	}
	
}
