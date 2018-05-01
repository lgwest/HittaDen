package me.noip.west.hittaden;

public class MyPosition {
    static int mapScale = 10000;
    private static double myBearing = 0.0;
    private static double myDistance = 0.0;

    static private double myLat;
    static private double myLon;
    static private double pointLat;
    static private double pointLon;

    public static void setMyBearing(double myBearing) {
        MyPosition.myBearing = myBearing;
    }

    public static void setMyDistance(double myDistance) {
        MyPosition.myDistance = myDistance;
    }

    static void setNyKontroll() {
        // get and save my position

        // calculate and save Kontroll position

    }
}

//    String context = Context.LOCATION_SERVICE;
//        mLocationManager = (LocationManager)getSystemService(context);
//                Criteria criteria = new Criteria();
//                mBestProvider = mLocationManager.getBestProvider(criteria, true);
//                Location location = mLocationManager.getLastKnownLocation(mBestProvider);
//
//                double lat = location.getLatitude();
//                double lon = location.getLongitude();
