package uz.mh.getDriverLocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GetDriverLocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetDriverLocationApplication.class, args);
    }

}

@RestController
class HelloKnoldusController{
    @GetMapping("/")
    public String hello(){
        return "Hello knoldus";
    }
}
