package alphadevelopment.avetti.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
public class ContentRow {

    private String title;

    private Set<ContentComponent> contentComponents = new HashSet<>();

    public ContentRow(String title) {
        this.title = title;
    }

    public void addContentComponent(ContentComponent contentComponent){
        contentComponents.add(contentComponent);
    }

    public void deleteContentComponent(int componentIndex){
        contentComponents.remove(componentIndex);
    }
}
