package foundation.cmo.service.graphql.sales;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import foundation.cmo.service.MService;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(name = "foundation.cmo.service.sales.enable-ean-api", havingValue = "true", matchIfMissing = true)
public class MEanApiService extends MService{
	
	@GraphQLQuery(name = "${query.sales}", description = "${query.sales.desc}")
	public String salesTest() {
		return "Sales";
	}
	
	@Bean void status() {
		log.info("Stating ean API...");
	}
	
}
