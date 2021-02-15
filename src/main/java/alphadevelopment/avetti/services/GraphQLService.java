package alphadevelopment.avetti.services;

import alphadevelopment.avetti.services.datafetcher.AllPagesDataFetcher;
import alphadevelopment.avetti.services.datafetcher.CreatePageDataFetcher;
import alphadevelopment.avetti.services.datafetcher.CreateRowDataFetcher;
import alphadevelopment.avetti.services.datafetcher.PageDataFetcher;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:pages.graphql")
    Resource resource;

    private GraphQL graphQL;

    private final DataFetcherFactory dataFetcherFactory;

    public GraphQLService(DataFetcherFactory dataFetcherFactory) {
        this.dataFetcherFactory = dataFetcherFactory;
    }

    @PostConstruct
    private void loadSchema() throws IOException {

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    //Add 3 mutations: deleteRow, createComponent, deleteComponent
    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                    .dataFetcher("allPages", dataFetcherFactory.getAllPagesDataFether())
                    .dataFetcher("page", dataFetcherFactory.getPageDataFether()))
                .type("Mutation", typeWiring -> typeWiring
                    .dataFetcher("createPage", dataFetcherFactory.createPageDataFether())
                    .dataFetcher("createRow", dataFetcherFactory.createRowDataFether())
                )
                .build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }


}
