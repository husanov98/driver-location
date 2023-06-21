package uz.mh.getDriverLocation.controller;

import uz.mh.getDriverLocation.model.GeoLocation;
import uz.mh.getDriverLocation.service.GeoIpLocationService;
import uz.mh.getDriverLocation.service.RequestService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;


@Controller
public class HomeController {

    private final RequestService requestService;
    private final GeoIpLocationService geoIpLocationService;



    public HomeController(RequestService requestService, GeoIpLocationService geoIpLocationService) throws IOException{
        this.requestService = requestService;
        this.geoIpLocationService = geoIpLocationService;
    }

    @GetMapping(value = "/location/{ipAddress}")
    public String index(@PathVariable String ipAddress, HttpServletRequest request, Model model) throws IOException, GeoIp2Exception {
//        String ipAddress = requestService.getClientIpAddress(request);
        GeoLocation locationInf = getLocationInf(ipAddress,request);
        model.addAttribute("geoLocation",locationInf);
        return "index";
    }

    @GetMapping("/ip")
    public String get(HttpServletRequest request,Model model){
        String ipAddress = requestService.getClientIpAddress(request);
        model.addAttribute("ipAddress",ipAddress);
        return "ip";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    private GeoLocation getLocationInf(String ip,HttpServletRequest request) throws IOException, GeoIp2Exception {

        return geoIpLocationService.getGeoLocation(ip,request);
    }
}
