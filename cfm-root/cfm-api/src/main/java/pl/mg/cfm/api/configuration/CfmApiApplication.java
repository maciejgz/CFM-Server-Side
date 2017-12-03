package pl.mg.cfm.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pl.mg.cfm")
public class CfmApiApplication {

    private static Logger LOG = LoggerFactory.getLogger(CfmApiApplication.class);

    public static void main(String[] args) {

        LOG.debug("Starting app...");
        System.out.println("Starting app console...");

        SpringApplication.run(CfmApiApplication.class, args);
    }
}
