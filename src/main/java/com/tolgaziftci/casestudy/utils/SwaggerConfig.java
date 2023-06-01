package com.tolgaziftci.casestudy.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI configOpenAPI(){
        Server devserver = new Server();
        devserver.setUrl("http://localhost:8080");
        devserver.setDescription("Development server URL");
        List<Server> serverList = new ArrayList<>();
        serverList.add(devserver);

        Contact contact = new Contact();
        contact.setName("Tolga Ziftci");
        contact.setEmail("tolga.ziftci@huawei.com");

        Info info = new Info()
                .title("Case Study - Movie Info API")
                .version("1.0")
                .contact(contact)
                .description("Expose endpoints for managing movie data");
        return new OpenAPI()
                .info(info)
                .servers(serverList);
    }
}
