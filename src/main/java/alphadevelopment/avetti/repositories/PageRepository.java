package alphadevelopment.avetti.repositories;

import alphadevelopment.avetti.models.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends MongoRepository<Page, Long> {

    @Query (value = "{ 'title' : ?0 }")
    Page findByTitle(String title);

    @Query (value = "{ 'id' : ?0 }")
    Page findById(String Id);
}
