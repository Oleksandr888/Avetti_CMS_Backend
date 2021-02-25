package alphadevelopment.avetti.services;

import alphadevelopment.avetti.models.ContentComponent;
import alphadevelopment.avetti.models.ContentRow;
import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.repositories.PageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public List<Page> getPages() {
        return pageRepository.findAll();
    }

    @Override
    public Page getPageByTitle(String title) {
        return pageRepository.findByTitle(title);
    }

    @Override
    public Page createPage(String title) {
        Page page = new Page(title);
        return pageRepository.save(page);
    }

    @Override
    public Page addRow(String title, String pageId) {

        Page page = pageRepository.findById(pageId);
        ContentRow row = new ContentRow(title);

        page.addContentRow(row);
        return pageRepository.save(page);
    }

    //Implement 3 methods
    @Override
    public Page deleteRow(int rowIndex, String pageId) {
        Page page = pageRepository.findById(pageId);

        page.deleteContentRow(rowIndex);

        return pageRepository.save(page);
    }

    @Override
    public Page createComponent(String content, int rowIndex, String pageId) {
        Page page = pageRepository.findById(pageId);
        ContentRow row = page.getContentRow(rowIndex);
        ContentComponent component = new ContentComponent(content);

        row.addContentComponent(component);

        return pageRepository.save(page);
    }

    @Override
    public Page deleteComponent(int rowIndex, String pageId) {
        return null;
    }
}
