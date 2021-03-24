package alphadevelopment.avetti.graphqlresolvers;

import alphadevelopment.avetti.helpers.InvalidContentTypeException;
import alphadevelopment.avetti.models.ContentRow;
import alphadevelopment.avetti.models.Page;
import alphadevelopment.avetti.services.PageService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.FileUpdateRequest;
import io.imagekit.sdk.models.results.Result;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    private final PageService pageService;
    private final ResourceLoader resourceLoader;

//    createPage(title: String!): Page!
//    createRow(title: String!, pageId: String!): Page!
//    deleteRow(rowIndex: String!, pageId: String!): Page!
//    createComponent(rowIndex: String!, pageId: String!): Page!
//    deleteComponent(rowIndex: String!, pageId: String!): Page!

    public Page createPage(String title){
        return pageService.createPage(title);
    }

    public Page createRow(String title, String pageId){
        return pageService.addRow(title, pageId);
    }

    public Page deleteRow(int rowIndex, String pageId){
        return pageService.deleteRow(rowIndex,pageId);
    }

    public Page createTextComponent(String text, int rowIndex, String pageId){
        return pageService.createTextComponent(text, rowIndex, pageId);
    }

    public Page editTextComponent(String text, int componentIndex, int rowIndex, String pageId){
        return pageService.editTextComponent(text, componentIndex, rowIndex, pageId);
    }

    public Page createImageComponent(Part image, int rowIndex, String pageId,
                                           DataFetchingEnvironment environment) throws IOException, InvalidContentTypeException {
        //Getting image from request
        Part actualImage = environment.getArgument("image");
        byte[] byteArray = IOUtils.toByteArray(actualImage.getInputStream());

        //Generating filename
        String name = UUID.randomUUID().toString();
        String type = getType(actualImage.getContentType());
        String fileName = name + "." + type;

        //Creating request to imagekit.io
        FileCreateRequest fileCreateRequest = new FileCreateRequest(byteArray, fileName);

        //Uploading image
        Result result = ImageKit.getInstance().upload(fileCreateRequest);

        System.out.println(result.getMap());

        //Getting url to image with default size of 200px
        String filePath = result.getMap().get("filePath").toString();
        List<Map<String,String>> transformation = new ArrayList<Map<String,String>>();
        Map<String, String> scale=new HashMap<>();
        scale.put("width","200");
        transformation.add(scale);

        Map<String, Object> options = new HashMap();
        options.put("path", filePath);
        options.put("transformation", transformation);
        String url = ImageKit.getInstance().getUrl(options);

        //Getting FileId
        String fileId = result.getMap().get("fileId").toString();

        //Save image url to database
        return pageService.createImageComponent(fileId, url, rowIndex, pageId);
    }

    private File getLocation(String filename) throws IOException {
        File directory = resourceLoader.getResource("file:./filestorage/").getFile();
        return new File(directory, filename);
    }

    private String getType(String mimetype) throws InvalidContentTypeException {
        MediaType mediaType = MediaType.parseMediaType(mimetype);
        if (!isImage(mediaType)) throw new InvalidContentTypeException("Invalid content-type");
        else if (isJpeg(mediaType)) return "jpg";
        else return mediaType.getSubtype();
    }

    private boolean isJpeg(MediaType mediaType) {
        return "jpeg".equalsIgnoreCase(mediaType.getSubtype());
    }

    private boolean isImage(MediaType mediaType) {
        return "image".equalsIgnoreCase(mediaType.getType());
    }

    public String resizeImageComponent(int newWidth, int componentIndex, int rowIndex, String pageId){
        return pageService.resizeImageComponent(newWidth, componentIndex, rowIndex, pageId);
    }

    public ContentRow deleteComponent(int componentIndex, int rowIndex, String pageId){
        return pageService.deleteComponent(componentIndex, rowIndex, pageId);
    }

}
