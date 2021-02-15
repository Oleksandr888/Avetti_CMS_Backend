package alphadevelopment.avetti.resource;

import alphadevelopment.avetti.services.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("graphql")
@RestController
public class PageResource {

    private final GraphQLService graphQLService;

    public PageResource(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping
    public ResponseEntity<Object> getAllPages(@RequestBody String query){

        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);

    }
}
