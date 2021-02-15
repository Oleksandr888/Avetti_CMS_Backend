package alphadevelopment.avetti.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ContentComponent {

    private String text;
    //private Byte[] image;

    public ContentComponent(String text) {
        this.text = text;
    }
}
