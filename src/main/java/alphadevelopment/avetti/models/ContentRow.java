package alphadevelopment.avetti.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContentRow {

    private String title;

    private List<ContentComponent> contentComponents = new ArrayList<>();

    public ContentRow(String title) {
        this.title = title;
    }

    public void addContentComponent(ContentComponent contentComponent){
        contentComponents.add(contentComponent);
    }

    public ContentComponent getContentComponent(int index){ return contentComponents.get(index); }

    public void deleteContentComponent(int componentIndex){
        contentComponents.remove(componentIndex);
    }
}
