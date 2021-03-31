package alphadevelopment.avetti.controllers;

import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.services.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pages")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping(path = "/{title}", produces = "application/json")
    public Page getPage(@PathVariable String title){
        return pageService.getPageByTitle(title);
    }
}
