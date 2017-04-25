package android.learn.com.tripmanager;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class TripDetailsMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder geocoder = new Geocoder(this);
        Intent tempIntent=getIntent();
        LatLng sourcecity=new LatLng(32.7767,96.7970);
        LatLng destinationcity=new LatLng(32.7767,96.7970);
        List<Address> addresses;
        try {
            addresses=geocoder.getFromLocationName(tempIntent.getStringExtra("sourcecity"),1);
            //addresses=geocoder.getFromLocationName("Dallas",1);
            sourcecity=new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
            addresses=geocoder.getFromLocationName(tempIntent.getStringExtra("destinationcity"),1);
            //addresses=geocoder.getFromLocationName("Chicago",1);
            destinationcity=new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.addMarker(new MarkerOptions().position(sourcecity).title("Dallas").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sourcecity));

        mMap.addMarker(new MarkerOptions().position(destinationcity).title("Chicago").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        //mMap.getMinZoomLevel();
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 4.0f ) );
    }
}
