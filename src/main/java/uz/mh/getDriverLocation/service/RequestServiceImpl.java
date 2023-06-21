package uz.mh.getDriverLocation.service;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.net.InetAddress;
import java.net.UnknownHostException;
@Service
public class RequestServiceImpl implements RequestService {

    private Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final String LOCALHOST_IPV4 = "127.0.0.1";

    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    private final String UNKNOWN = "unknown";

    @Override
    public String getClientIpAddress(HttpServletRequest request) {
        String clientIpAddress = request.getHeader("X-Forwarded-For");
        if (!StringUtils.hasLength(clientIpAddress) || UNKNOWN.equals(clientIpAddress)){
            clientIpAddress = request.getHeader("Proxy-Client-IP");
        }

        if (!StringUtils.hasLength(clientIpAddress) || UNKNOWN.equals(clientIpAddress)){
            clientIpAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (!StringUtils.hasLength(clientIpAddress) || UNKNOWN.equals(clientIpAddress)){
            clientIpAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(clientIpAddress) || LOCALHOST_IPV6.equals(clientIpAddress)){
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    clientIpAddress = inetAddress.getHostAddress();
                }catch (UnknownHostException e){
                    logger.error(e.getMessage(),e);
                }
            }
        }
        if (StringUtils.hasLength(clientIpAddress)
                && clientIpAddress.length() > 15
                && clientIpAddress.indexOf(',') > 0) {
            clientIpAddress = clientIpAddress.substring(0,clientIpAddress.indexOf(","));
        }
        return clientIpAddress;
    }
}
