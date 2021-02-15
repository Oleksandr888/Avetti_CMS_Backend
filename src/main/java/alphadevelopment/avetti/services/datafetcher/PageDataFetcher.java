package alphadevelopment.avetti.services.datafetcher;

import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.services.PageService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageDataFetcher implements DataFetcher<Page> {

    private final PageService pageService;

    public PageDataFetcher(PageService pageService) {
        this.pageService = pageService;
    }

    @Override
    public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

        String title = dataFetchingEnvironment.getArgument("title");

        return pageService.getPageByTitle(title);
    }
}
