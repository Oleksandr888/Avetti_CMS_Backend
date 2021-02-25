package alphadevelopment.avetti.graphqlresolvers;

import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.services.PageService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final PageService pageService;

    public QueryResolver(PageService pageService) {
        this.pageService = pageService;
    }

    public List<Page> getAllPages(){
        return pageService.getPages();
    }

    public Page getPage(String title){
        return pageService.getPageByTitle(title);
    }
}
