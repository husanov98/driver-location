package uz.mh.getDriverLocation.service;

import uz.mh.getDriverLocation.model.GeoLocation;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface GeoIpLocationService {
    GeoLocation getGeoLocation(String ip, HttpServletRequest request) throws IOException, GeoIp2Exception;
}
