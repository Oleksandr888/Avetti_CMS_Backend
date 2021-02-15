package alphadevelopment.avetti.services.datafetcher;

import alphadevelopment.avetti.models.ContentRow;
import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.services.PageService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class CreateRowDataFetcher implements DataFetcher<Page> {

    private final PageService pageService;

    public CreateRowDataFetcher(PageService pageService) {
        this.pageService = pageService;
    }

    @Override
    public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

        String title = dataFetchingEnvironment.getArgument("title");
        String pageTitle = dataFetchingEnvironment.getArgument("pageTitle");

        return pageService.addRow(title, pageTitle);
    }
}
