package pennapps2015.mealshare;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.drive.metadata.internal.ParentDriveIdSet;
import com.parse.ParseGeoPoint;

/**
 * Created by Rob on 9/5/2015.
 */
public abstract class globals extends Application implements LocationListener {
    private static globals instance;
    private globals(){}


    public String usn;
    public void setUSN(String androidID) {
        usn = androidID;
    }

    public String getUSN() {
        return this.usn;
    }


    public ParseGeoPoint currentLocation;

    public void setCurrentLocation(Location location) {

        double lat = location.getLatitude();
        double longi = location.getLongitude();

        currentLocation = new ParseGeoPoint(lat,longi);
    }



    public ParseGeoPoint getCurrentLocation() {

        return currentLocation;
    }

    public static synchronized globals getInstance() {
        if (instance == null) {
            instance = new globals() {
                public void onLocationChanged(Location location) {
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
        }
        return instance;
    }
}
