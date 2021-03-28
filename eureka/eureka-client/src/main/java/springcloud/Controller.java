package springcloud;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @Value("${server.port}")
    private String port;

    @GetMapping("sayHi")
    public String sayHi(){
        return "This is " + port;
    }

    @PostMapping
    public Friend sayHiPost(@RequestBody Friend friend){

        System.out.println("You are "+friend.getName());
        friend.setPort(port);
        return friend;
    }

}
