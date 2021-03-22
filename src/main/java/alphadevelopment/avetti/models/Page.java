package alphadevelopment.avetti.models;

import kotlin.collections.ArrayDeque;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Document
@Data
public class Page{

    @Id
    private String id;
    private String title;
    private List<ContentRow> contentRows = new ArrayList<>();

    public Page(String title) {
        this.title = title;
    }

    public void addContentRow(ContentRow contentRow){
        contentRows.add(contentRow);
    }

    public void deleteContentRow(int rowIndex){
        contentRows.remove(rowIndex);
    }

    public ContentRow getContentRow(int rowIndex){
        return contentRows.get(rowIndex);
    }


}
