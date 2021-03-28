package springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private LoadBalancerClient client;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("hello")
    public String helloGet() {
        ServiceInstance instance = client.choose("eureka-client");

        if (instance == null) {
            return "No available instances";
        }

        String target = String.format("http://%s:%s/sayHi",
                instance.getHost(),
                instance.getPort());
        System.out.println("url is {}"+ target);

        return restTemplate.getForObject(target,String.class);
    }

    @PostMapping("hello")
    public Friend helloPost() {
        ServiceInstance instance = client.choose("eureka-client");

        if (instance == null) {
            return null;
        }

        String target = String.format("http://%s:%s/sayHi",
                instance.getHost(),
                instance.getPort());
        System.out.println("url is {}"+ target);

        Friend friend=new Friend();
        friend.setName("Eureka Consumer");

        return restTemplate.postForObject(target,friend,Friend.class);
    }



}
