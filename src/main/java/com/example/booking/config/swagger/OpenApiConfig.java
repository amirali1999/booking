package com.example.booking.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for booking",
                title = "booking OpenApi",
                version = "1.0",
                license = @License(
                        name = "license name"
                ),
                termsOfService = "ferdowsi"
        ),
        servers = {
                @Server(
                        description = "local environment",
                        url = "http://localhost:8080"
                )
        }
)
//@SecurityScheme(
//        name = "Pod Token",
//        description = "Authorize with Pod services",
//        scheme = "Bearer",
//        type = SecuritySchemeType.HTTP,
//        in = SecuritySchemeIn.HEADER
//)
public class OpenApiConfig {
}
