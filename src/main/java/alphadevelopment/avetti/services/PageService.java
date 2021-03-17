package alphadevelopment.avetti.services;

import alphadevelopment.avetti.models.ContentComponent;
import alphadevelopment.avetti.models.ContentRow;
import alphadevelopment.avetti.models.Page;

import java.util.List;

public interface PageService {

    List<Page> getPages();

    public Page getPageByTitle(String title);

    public Page createPage(String title);

    public Page addRow(String title, String pageId);

    public Page deleteRow(int rowIndex, String pageId);

    public Page createTextComponent(String content, int rowIndex, String pageId);

    public Page createImageComponent(String fileId, String url, int rowIndex, String pageId);

    public String resizeImageComponent(int newWidth, int componentIndex, int rowIndex, String pageId);

    public ContentRow deleteComponent(int componentIndex, int rowIndex, String pageId);

}
