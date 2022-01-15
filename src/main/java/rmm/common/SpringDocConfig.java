package rmm.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    // TODO: Secure Endpoints
    // TODO: Endpoints Implement:
    //          POST (ADD), PATCH (UPDATE) Devices
    //          DELETE, ADD (no duplicates) Device Service Plan endpoints
    // TODO: Determine how to insert into DB on runtime (use postgresql ??)
    // TODO: Test README instructions (update for any changes)
    // TODO: Implement Testing

    @Bean
    public OpenAPI initSpringDoc() {
        return new OpenAPI()
                .info(new Info().title("RMM API").version("v1"));
    }
}
