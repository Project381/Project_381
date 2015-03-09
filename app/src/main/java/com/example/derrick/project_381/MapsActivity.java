package com.example.derrick.project_381;

/* Name: Derrick Johnstone
   NSID: daj113
   ST#   10920110
   and
   Name: Shea Meyers
   NSID: ssm904
   ST#   11051608
   CMPT 381
   Project_381
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileNotFoundException;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println("Clicked Marker! : " + marker.getId() + " : " + marker.getPosition() + " : " + marker.getSnippet());
                return false;
            }
        });

    }
    public boolean handleMarkerClick(){
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        // Set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Get latitude of current location needs phone and GPS
        //double latitude = myLocation.getLatitude();
        double latitude = 52.293121;


        // Get longitude of the current location needs phone and GPS
        //double longitude = myLocation.getLongitude();
        double longitude = -106.657838;
        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Set zoom in google maps
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Test markers
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title("You are here!")
                .snippet("Your current location on google maps"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(22.222855, -159.496132))
                .title("Pu'u Poa Condo")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.photo4))
                        //.icon(BitmapDescriptorFactory.fromBitmap(bm))
                .snippet("Population: 776733")
                .flat(false));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.133214, -106.670046))
                .title("Saskatoon")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.photo3))
                        //.icon(BitmapDescriptorFactory.fromBitmap(bm))
                .snippet("Mountains"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(22.220611, -159.583334))
                .title("Ke'e")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.photo4))
                        //.icon(BitmapDescriptorFactory.fromBitmap(bm))
                .snippet("Waves"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.318366, -106.575075))
                .title("Warman")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.photo5))
                        //.icon(BitmapDescriptorFactory.fromBitmap(bm))
                .snippet("Foot Prints"));
    }
}
/*
    private void loadLocations() {
        dataSource.open();
        ArrayList <LocationEntry> locations = dataSource.getLocationsFromTrack(trackId);
        dataSource.close();
        for(int i=0; i<locations.size(); i++)
        {
            map.addMarker(new MarkerOptions()
                    .position(locations.get(i).getCoordinates())
                    .title(locations.get(i).getName())
                    .snippet(locations.get(i).getSnippet())
                    .icon(BitmapDescriptorFactory.fromPath(locations.get(i).getPhotoURL()))); //here
        }
    }*/
