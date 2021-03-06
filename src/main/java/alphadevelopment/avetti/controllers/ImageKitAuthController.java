package alphadevelopment.avetti.controllers;

import io.imagekit.sdk.ImageKit;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController()
public class ImageKitAuthController {

    @GetMapping("/auth")
    public Map<String, String> getAuthParameters(){
        Map<String, String> parameters = new HashMap<>();
        String token= UUID.randomUUID().toString();
        long expire= System.currentTimeMillis() / 1000L + 30 * 60;
        parameters = ImageKit.getInstance().getAuthenticationParameters(token, expire);
        return parameters;
    }

}
