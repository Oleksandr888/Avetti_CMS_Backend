package alphadevelopment.avetti.bootstrap;

import alphadevelopment.avetti.models.ContentComponent;
import alphadevelopment.avetti.models.ContentRow;
import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.repositories.PageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PageBootstrap implements CommandLineRunner {

    private final PageRepository pageRepository;

    public PageBootstrap(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        ContentComponent cc1 = new ContentComponent("first component");
//        ContentComponent cc2 = new ContentComponent("second component");
//        ContentComponent cc3 = new ContentComponent("third component");
//
//        ContentRow cr1 = new ContentRow("1st block");
//        ContentRow cr2 = new ContentRow("2nd block");
//
//        cr1.addContentComponent(cc1);
//        cr1.addContentComponent(cc2);
//        cr2.addContentComponent(cc3);
//
//        Page page1 = new Page("page1");
//        Page page2 = new Page("page2");
//
//
//        pageRepository.save(page1);
//        pageRepository.save(page2);

    }
}
