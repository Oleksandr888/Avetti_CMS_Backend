package alphadevelopment.avetti.services.datafetcher;

import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.services.PageService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllPagesDataFetcher implements DataFetcher<List<Page>> {

    private final PageService pageService;

    public AllPagesDataFetcher(PageService pageService) {
        this.pageService = pageService;
    }

    @Override
    public List<Page> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return pageService.getPages();
    }
}
