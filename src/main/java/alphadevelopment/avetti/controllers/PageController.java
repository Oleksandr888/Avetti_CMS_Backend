package alphadevelopment.avetti.controllers;


import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.repositories.PageRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {

    private final PageRepository pageRepository;

    public PageController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }


    public List<Page> getAllPages(){
        return null;
    }

    @PostMapping(value = "/createPage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createPage(@RequestBody Page page){
        Page insertedPage = pageRepository.insert(page);
        return null;
    }
}
