package uz.mh.getDriverLocation.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocation {
    private String ipAddress;
    private String device;
    private String city;
    private String fullLocation;
    private Double latitude;
    private Double longitude;

    public GeoLocation(String ipAddress){
        this.ipAddress = ipAddress;
    }

    public GeoLocation(String ipAddress,String city,Double latitude,Double longitude){
        this.ipAddress = ipAddress;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
