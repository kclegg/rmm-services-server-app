package rmm.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    // TODO: Secure Endpoints
    // TODO: Determine how to insert into DB on runtime (use postgresql ??)
    // TODO: Test README instructions (update for any changes)
    // TODO: Implement GET, DELETE, ADD (no duplicates) Device Services endpoints
    // TODO: Implement Testing

    @Bean
    public OpenAPI initSpringDoc() {
        return new OpenAPI()
                .info(new Info().title("RMM API").version("v1"));
    }
}
