package rmm.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI initSpringDoc() {
        return new OpenAPI()
                .info(new Info().title("RMM API").version("v1"));
    }
}
