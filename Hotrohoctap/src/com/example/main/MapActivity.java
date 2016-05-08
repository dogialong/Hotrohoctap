package com.example.main;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

 public class MapActivity extends FragmentActivity  {
 
	    private GoogleMap mMap;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_map);
	        setUpMapIfNeeded();
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
	     * <p>
	     * If it isn't installed {@link SupportMapFragment} (and
	     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
	     * install/update the Google Play services APK on their device.
	     * <p>
	     * A user can return to this FragmentActivity after following the prompt and correctly
	     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not have been
	     * completely destroyed during this process (it is likely that it would only be stopped or
	     * paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in
	     * {@link #onResume()} to guarantee that it will be called.
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
	     * <p>
	     * This should only be called once and when we are sure that {@link #mMap} is not null.
	     */
	    private void setUpMap() {
	    	Uri gmmIntentUri = Uri.parse("geo:21.0272031,105.8123839?q=FPT Polytechnich Ha Noi");
	    	Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
	    	mapIntent.setPackage("com.google.android.apps.maps");
	    	startActivity(mapIntent);
	    }
 
}