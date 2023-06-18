package foundation.cmo.service.graphql.mappers;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import foundation.cmo.service.configurations.MServiceProperties;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.generator.mapping.TypeMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(MServiceProperties.class)
@ConditionalOnProperty(name = "foundation.cmo.service.mappers.enable", havingValue = "true", matchIfMissing = true)
public class GraphQLConfig {

	@Bean
	@ConditionalOnMissingBean
	ExtensionProvider<GeneratorConfiguration, TypeMapper> pageableInputField(MServiceProperties props) {
			log.info("Enabling default mapper's...");
			return (config, defaults) -> defaults.prepend(new MDateTypeMapper()).prepend(new MCaseTypeMapper());
	}
	
	
	
}