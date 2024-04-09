package com.aimachines.cdk.entry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableScheduling
@EnableSwagger2
@EntityScan("com.aimachines.cdk.model")
@EnableJpaRepositories(value = "com.aimachines.cdk.repository")
@ComponentScan({ "com.aimachines.cdk.controller", "com.aimachines.cdk.service", "com.aimachines.cdk.config",
	"com.aimachines.cdk.cron","com.aimachines.cdk.cron.config" })

@SpringBootApplication
public class ServiceEntryPoint {

	@Autowired
	private Environment env;

	private static final String BASE_PATH = "server.servlet.context-path";

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ServiceEntryPoint.class);
		springApplication.run(args);
	}

	/**
	 * @return
	 */
	@Bean
	public Docket api() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new BasicAuth("basicAuth"));
		Set<String> set = new HashSet<>();
		// un comment for local development
		set.add("http");
		set.add("https");
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.aimachines.cdk.controller")).paths(PathSelectors.regex("/.*"))
				.build().apiInfo(apiEndPointsInfo()).securitySchemes(schemeList)
				.pathProvider(new BasePathAwareRelativePathProvider(env.getProperty(BASE_PATH))).protocols(set)
				.tags(new Tag("Bridge", "API for bridging data from CDK to aimachines database")
						);
	}

	/**
	 * @return
	 */
	private ApiInfo apiEndPointsInfo() {

		return new ApiInfoBuilder().title("Address API").description("Address API Services").version("1.0").build();
	}

}
