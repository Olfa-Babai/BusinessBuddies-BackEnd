package tn.esprit.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Api(tags="Pidev Management")
public class SwaggerConfig {
	@Bean
	public Docket Api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}
	private ApiInfo apiInfo(){ 
		return new ApiInfoBuilder() 
				.title("Swagger Configuration for pidev") 
				.description("\"Spring Boot Swagger configuration\"") 
				.version("1.1.0").build();
	}

}
