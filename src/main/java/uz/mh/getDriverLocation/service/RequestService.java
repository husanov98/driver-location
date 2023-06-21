package uz.mh.getDriverLocation.service;


import jakarta.servlet.http.HttpServletRequest;

public interface RequestService {
    String getClientIpAddress(HttpServletRequest request);
}
