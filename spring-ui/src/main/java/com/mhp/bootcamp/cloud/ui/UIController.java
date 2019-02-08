package com.mhp.bootcamp.cloud.ui;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Controller()
@EnableEurekaClient
public class UIController {

    @Value("${welcome.message:NOTAVAILABLE}")
    private String message;

    @Autowired
    private EurekaClient discoveryClient;

    @Autowired
    private RestTemplate rt;


    @GetMapping("/")
    public String color(Map<String, Object> model) {
        model.put("message", message);

        //Call Eureka directely: use Loadbalanced at the RestTemplate Bean
        String ressource = "http://SPRINGCAMP-COLOR";

        // Use Gateway without LoadBalanced

        ressource = "http://localhost:8080";
        //ressource = "http://localhost:8088/color";
        //ressource = serviceUrl();


        log.info("Serivce URL: " + ressource);
        ResponseEntity<String> response = rt.getForEntity(ressource + "/color", String.class);
        String sResponse = response.toString();

        log.info("Recieved: " + sResponse);

        String responseBody = response.getBody();
        log.info("Response Body: " + responseBody);

        model.put("param_color", responseBody);

        return "color";
    }


    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("springcamp-color", false);
        return instance.getHomePageUrl();
    }
}
