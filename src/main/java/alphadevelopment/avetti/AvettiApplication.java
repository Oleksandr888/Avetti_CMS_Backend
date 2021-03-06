package alphadevelopment.avetti;

import graphql.schema.GraphQLScalarType;
import graphql.servlet.core.ApolloScalars;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class AvettiApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(AvettiApplication.class, args);
        ImageKit imageKit=ImageKit.getInstance();
        Configuration config= Utils.getSystemConfig(AvettiApplication.class);
        imageKit.setConfig(config);

    }

    @Bean
    public GraphQLScalarType uploadScalar() {
        return ApolloScalars.Upload;
    }

}
