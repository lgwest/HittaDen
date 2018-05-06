package me.noip.west.hittaden;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HittaDen extends Activity {

    private static final int REQUEST_LOCATION = 1;


    static int theBearing_deg = 0;
    static int theDistance_mm = 0;
    static int mapScale = 10000;
    static boolean nyKontroll = false;
    final int earthRadius = 6371000;
    Location theLocation; // orienteringskontrollens position
    LocationManager locationManager;
    TextView brView;

    public static void setTheBearing(int theBearing_deg) {
        HittaDen.theBearing_deg = theBearing_deg;
    }

    public static void setTheDistance(int theDistance_mm) {
        HittaDen.theDistance_mm = theDistance_mm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitta_den);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        int map_scale = HittaDen.mapScale;
        TextView scale = findViewById(R.id.kartSkala);
        scale.setText(Integer.toString(map_scale));

        theLocation = new Location("");

        brView = findViewById(R.id.b_r_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int map_scale = HittaDen.mapScale;
        TextView scale = findViewById(R.id.kartSkala);
        scale.setText("1:" + Integer.toString(map_scale));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (HittaDen.nyKontroll && HittaDen.theDistance_mm > 0) {
                HittaDen.nyKontroll = false;
                double bearing = HittaDen.theBearing_deg * Math.PI / 180.0;
                double distance = HittaDen.theDistance_mm * HittaDen.mapScale * 0.001;
                Location myLocation = getLocation();
                double lat1 = myLocation.getLatitude();
                double lon1 = myLocation.getLongitude();
//            http://www.movable-type.co.uk/scripts/latlong.html
                double lat2 = Math.asin(Math.sin(lat1) * Math.cos(distance / earthRadius) +
                        Math.cos(lat1) * Math.sin(distance / earthRadius) * Math.cos(bearing));
                double lon2 = lon1 + Math.atan2(Math.sin(bearing) * Math.sin(distance / earthRadius) * Math.cos(lat1),
                        Math.cos(distance / earthRadius) - Math.sin(lat1) * Math.sin(lat2));
                theLocation.setLatitude(lat2);
                theLocation.setLongitude(lon2);
            }
        }
    }

    public void onChangeMapScale(View view) {
        Intent intent = new Intent(this, KartSkala.class);
        startActivity(intent);
    }

    public void onNyKontroll(View view) {
        Intent intent = new Intent(this, NyKontroll.class);
        startActivity(intent);
    }

    public void onUppdatera(View view) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


            Location myLocation = getLocation();
            myLocation.distanceTo(theLocation);
            float bearing = myLocation.bearingTo(theLocation);
            float distance = myLocation.distanceTo(theLocation);
            String b_r_txt = "Bäring: " + Float.toString(bearing) + " grader\n" +
                    "Avstånd: " + Float.toString(distance) + " meter";
            brView.setText(b_r_txt);
        }
    }

    private Location getLocation() {
        Location theLocation = new Location("");
        theLocation.setLatitude(0.0d);
        theLocation.setLongitude(0.0d);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                theLocation = location;
            } else  if (location1 != null) {
                theLocation = location1;
            } else  if (location2 != null) {
                theLocation = location2;
            }else{
                Toast.makeText(this,"Unable to Trace your location",Toast.LENGTH_SHORT).show();
            }
        }
        return theLocation;
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
