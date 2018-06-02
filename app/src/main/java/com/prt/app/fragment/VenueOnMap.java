package com.prt.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;

/**
 * @author Pratyesh Singh
 */
public class VenueOnMap extends Fragment implements OnMapReadyCallback {

    // Google Map
    private GoogleMap googleMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        ((HomeActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_venue_on_map, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
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

        // check if map is created successfully or not
        if (googleMap == null) {
            Toast.makeText(getActivity(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
        } else {
            // latitude and longitude
            double latitude = 13.912817;// 13.98204587;
            double longitude = 100.549049;// 100.85586548;

            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("IMPACT Exhibition and Convention Center, Hall 2, 3 & 4, Bangkok, Thailand");

            // ROSE color icon
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

            // adding marker
            googleMap.addMarker(marker);

            /*** ----------------------------------------- */
            // Changing Custom marker icon
            // marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker_icon)));

            // Showing Current Location
            // googleMap.setMyLocationEnabled(true); // false to disable

            // Zooming Buttons
            // googleMap.getUiSettings().setZoomControlsEnabled(false); // true to enable

            // Zooming Functionality
            // googleMap.getUiSettings().setZoomGesturesEnabled(false);

            // Compass Functionality
            // googleMap.getUiSettings().setCompassEnabled(true);

            // My Location Button
            // googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            // Map Rotate Gesture
            // googleMap.getUiSettings().setRotateGesturesEnabled(true);

            // Moving Camera to a Location with animation
            CameraPosition cameraPosition = new CameraPosition.Builder().target(marker.getPosition()).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            /*** ----------------------------------------- */
        }

    }
}
