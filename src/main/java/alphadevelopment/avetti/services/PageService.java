package alphadevelopment.avetti.services;

import alphadevelopment.avetti.models.Page;

import java.util.List;

public interface PageService {

    List<Page> getPages();

    public Page getPageByTitle(String title);

    public Page createPage(String title);

    public Page addRow(String title, String pageTitle);

}
