package depth.mju.festival.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title("2024 MJU Festival 서버 Swagger")
                .description("2024 MJU Festival 서버 API 목록입니다.");
        return new OpenAPI()
                .info(info);
    }
}
