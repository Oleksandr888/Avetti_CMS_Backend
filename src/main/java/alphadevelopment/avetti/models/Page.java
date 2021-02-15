package alphadevelopment.avetti.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
public class Page {

    @Id
    private String id;
    private String title;
    private Set<ContentRow> contentRows = new HashSet<>();

    public Page(String title) {
        this.title = title;
    }

    public void addContentRow(ContentRow contentRow){
        contentRows.add(contentRow);
    }
}
