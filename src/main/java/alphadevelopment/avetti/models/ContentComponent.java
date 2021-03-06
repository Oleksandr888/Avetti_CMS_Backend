package alphadevelopment.avetti.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ContentComponent {

    private String type;
    private String content;

    public ContentComponent(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
