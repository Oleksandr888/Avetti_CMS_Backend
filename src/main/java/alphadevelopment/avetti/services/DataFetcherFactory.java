package alphadevelopment.avetti.services;

import alphadevelopment.avetti.models.Page;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataFetcherFactory {

    private final PageService pageService;

    public DataFetcherFactory(PageService pageService) {
        this.pageService = pageService;
    }

    public DataFetcher<List<Page>> getAllPagesDataFether(){

        DataFetcher<List<Page>> dataFetcher = new DataFetcher() {
            @Override
            public List<Page> get(DataFetchingEnvironment environment) {
                return pageService.getPages();
            }
        };

        return dataFetcher;
    }

    public DataFetcher<Page> getPageDataFether(){

        DataFetcher<Page> dataFetcher = new DataFetcher() {
            @Override
            public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

                String title = dataFetchingEnvironment.getArgument("title");

                return pageService.getPageByTitle(title);
            }
        };

        return dataFetcher;
    }

    public DataFetcher<Page> createPageDataFether(){

        DataFetcher<Page> dataFetcher = new DataFetcher() {
            @Override
            public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

                String title = dataFetchingEnvironment.getArgument("title");
                return pageService.createPage(title);
            }
        };

        return dataFetcher;
    }

    public DataFetcher<Page> createRowDataFether() {

        DataFetcher<Page> dataFetcher = new DataFetcher() {
            @Override
            public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

                String title = dataFetchingEnvironment.getArgument("title");
                String pageId = dataFetchingEnvironment.getArgument("pageId");

                return pageService.addRow(title, pageId);
            }
        };

        return dataFetcher;
    }

    //Add 3 methods for creating datafetchers

        public DataFetcher<Page> deleteRowDataFether(){

                DataFetcher<Page> dataFetcher = new DataFetcher() {
                    @Override
                    public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

                        int rowIndex = dataFetchingEnvironment.getArgument("rowIndex");
                        String pageId = dataFetchingEnvironment.getArgument("pageId");

                        return pageService.deleteRow(rowIndex, pageId);
                    }
                };

                return dataFetcher;
        }
        public DataFetcher<Page> createComponent() {

            DataFetcher<Page> dataFetcher = new DataFetcher() {
                @Override
                public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

                    int rowIndex = dataFetchingEnvironment.getArgument("rowIndex");
                    String pageId = dataFetchingEnvironment.getArgument("pageId");

                    return pageService.createComponent(rowIndex, pageId);
                }
            };
            return dataFetcher;
        }

    public DataFetcher<Page> deleteComponent() {

        DataFetcher<Page> dataFetcher = new DataFetcher() {
            @Override
            public Page get(DataFetchingEnvironment dataFetchingEnvironment) {

                int rowIndex = dataFetchingEnvironment.getArgument("rowIndex");
                String pageId = dataFetchingEnvironment.getArgument("pageId");

                return pageService.deleteComponent(rowIndex, pageId);
            }
        };
        return dataFetcher;
    }



}
