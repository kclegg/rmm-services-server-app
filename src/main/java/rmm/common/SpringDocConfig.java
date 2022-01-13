package rmm.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    // TODO: Secure Endpoints
    // TODO: Add Gitignore
    // TODO: Determine how to insert into DB on runtime (use postgresql ??)
    // TODO: Implement monthly bill endpoint for current state of customer data
    // TODO: Update README
    // TODO: Implement GET, DELETE, ADD (no duplicates) Device Services endpoints
    // http://localhost:8080/swagger-ui/index.html

    @Bean
    public OpenAPI initSpringDoc() {
        return new OpenAPI()
                .info(new Info().title("RMM API").version("v1"));
    }
}
