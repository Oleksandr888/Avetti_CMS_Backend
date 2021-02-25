package alphadevelopment.avetti.graphqlresolvers;

import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.services.PageService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final PageService pageService;

    public MutationResolver(PageService pageService) {
        this.pageService = pageService;
    }

//    createPage(title: String!): Page!
//    createRow(title: String!, pageId: String!): Page!
//    deleteRow(rowIndex: String!, pageId: String!): Page!
//    createComponent(rowIndex: String!, pageId: String!): Page!
//    deleteComponent(rowIndex: String!, pageId: String!): Page!

    public Page createPage(String title){
        return pageService.createPage(title);
    }

    public Page createRow(String title, String pageId){
        return pageService.addRow(title, pageId);
    }

    public Page deleteRow(int rowIndex, String pageId){
        return pageService.deleteRow(rowIndex,pageId);
    }

    public Page createComponent(String content, int rowIndex, String pageId){
        return pageService.createComponent(content, rowIndex, pageId);
    }

    public Page deleteComponent(int rowIndex, String pageId){
        return pageService.deleteComponent(rowIndex, pageId);
    }

}
