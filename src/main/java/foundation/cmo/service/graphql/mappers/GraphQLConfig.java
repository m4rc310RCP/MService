package foundation.cmo.service.graphql.mappers;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import foundation.cmo.service.configurations.MServiceProperties;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.generator.mapping.TypeMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(MServiceProperties.class)
public class GraphQLConfig {


	@Bean
	@ConditionalOnMissingBean
	ExtensionProvider<GeneratorConfiguration, TypeMapper> pageableInputField(MServiceProperties props) {
		if (props.getMappers().isEnable()) {
			log.info("Enable mappers!");
			return (config, defaults) -> defaults.prepend(new MDateTypeMapper()).prepend(new MCaseTypeMapper());
		}
		
		return (config, defaults) -> defaults.prepend();
	}
}