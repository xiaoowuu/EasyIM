package win.smartown.easyim.ui.ysy.entity;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

public class Location implements Serializable {
    private double latitude;
    private double longitude;
    private String name;

    public Location(LatLng latLng, String name) {
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
        this.name = name;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}
