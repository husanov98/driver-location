package uz.mh.getDriverLocation.controller;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;

public class Main {
    public static void main(String[] args) {

        IPGeolocationAPI api = new IPGeolocationAPI("22a99683b1f44c798f4a4e73fa5d5889");

        getLocation(api,"188.113.195.94");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getLocation(api,"2.2.2.2");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getLocation(api,"3.3.3.3");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getLocation(api,"192.168.0.201");

    }

    private static void getLocation(IPGeolocationAPI api,String ip) {
        GeolocationParams params = new GeolocationParams();
        params.setIPAddress(ip);
        params.setFields("geo,time_zone,currency");
        Geolocation geolocation = api.getGeolocation(params);

        if (geolocation.getStatus() == 200){
            System.out.println(geolocation.getCountryName());
            System.out.println(geolocation.getCurrency().getName());
            System.out.println(geolocation.getTimezone().getCurrentTime());
            System.out.println(geolocation.getLatitude());
            System.out.println(geolocation.getLongitude());
        }else {
            System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(),geolocation.getMessage());
        }
    }
}
