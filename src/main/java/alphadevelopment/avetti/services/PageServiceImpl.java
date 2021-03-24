package alphadevelopment.avetti.services;

import alphadevelopment.avetti.models.ContentComponent;
import alphadevelopment.avetti.models.ContentRow;
import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.repositories.PageRepository;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.results.ResultMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Page createTextComponent(String content, int rowIndex, String pageId) {
        Page page = pageRepository.findById(pageId);
        ContentRow row = page.getContentRow(rowIndex);
        ContentComponent component = new ContentComponent("text", content);

        row.addContentComponent(component);

        return pageRepository.save(page);
    }

    @Override
    public Page editTextComponent(String content, int componentIndex, int rowIndex, String pageId) {
        Page page = pageRepository.findById(pageId);
        ContentRow row = page.getContentRow(rowIndex);
        ContentComponent component = row.getContentComponent(componentIndex);

        component.setContent(content);

        return pageRepository.save(page);
    }

    @Override
    public Page createImageComponent(String fileId, String url, int rowIndex, String pageId) {
        Page page = pageRepository.findById(pageId);
        ContentRow row = page.getContentRow(rowIndex);
        ContentComponent component = new ContentComponent("image", url);
        component.setFileId(fileId);

        row.addContentComponent(component);

        return pageRepository.save(page);
    }

    @Override
    public String resizeImageComponent(int newWidth, int componentIndex, int rowIndex, String pageId) {
        Page page = pageRepository.findById(pageId);
        ContentRow row = page.getContentRow(rowIndex);
        ContentComponent component = row.getContentComponent(componentIndex);

        //Getting filePath from url
        String filePath = getFilePathFromUrl(component.getContent());

        //Creating new url with updated width
        List<Map<String,String>> transformation = new ArrayList<Map<String,String>>();
        Map<String, String> scale=new HashMap<>();
        scale.put("width", String.valueOf(newWidth));
        transformation.add(scale);

        Map<String, Object> options = new HashMap();
        options.put("path", filePath);
        options.put("transformation", transformation);
        String url = ImageKit.getInstance().getUrl(options);

        //Saving new url to database
        component.setContent(url);
        pageRepository.save(page);

        return url;
    }

    @Override
    public ContentRow deleteComponent(int componentIndex, int rowIndex, String pageId) {
        Page page = pageRepository.findById(pageId);
        ContentRow row = page.getContentRow(rowIndex);
        ContentComponent component = row.getContentComponent(componentIndex);

        //Deleting image from imagekit
        if (component.getType().equals("image")){
            ImageKit.getInstance().deleteFile(component.getFileId());
        }

        //Saving changes to database
        row.deleteContentComponent(componentIndex);
        pageRepository.save(page);

        return row;
    }

    private String getFilePathFromUrl(String url){
        int pos1 = url.lastIndexOf("/");
        int pos2 = url.lastIndexOf("?");
        String filePath = url.substring(pos1, pos2);
        return filePath;
    }
}
