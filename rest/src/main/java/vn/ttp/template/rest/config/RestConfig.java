package vn.ttp.template.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vn.ttp.template.service.ServiceConfig;

@Configuration
@ComponentScan
@Import(ServiceConfig.class)
public class RestConfig {
}
