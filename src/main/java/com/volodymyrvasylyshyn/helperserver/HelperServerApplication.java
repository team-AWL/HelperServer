package com.volodymyrvasylyshyn.helperserver;

import com.volodymyrvasylyshyn.helperserver.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties(AppProperties.class)
public class HelperServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelperServerApplication.class, args);
    }

}
