package com.ubs.drm;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ubs.drm.configuration.AxonConfiguration;
import com.ubs.drm.configuration.PersistenceJPAConfig;
import com.ubs.drm.configuration.WebSecurityConfiguration;

@Configuration
@Import(value = {AxonConfiguration.class, PersistenceJPAConfig.class, LiquibaseAutoConfiguration.class, FreeMarkerAutoConfiguration.class})
@ComponentScan
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, ManagementSecurityAutoConfiguration.class})
@EnableConfigurationProperties
public class ApplicationImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationImpl.class);

	public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ApplicationImpl.class, args);

        LOGGER.info("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
	}

}
