package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;

import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.R;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.activities.ClusterInfoWindow;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.googleMaps.clustering.AppClusterItem;

/**
 * Created by kagan on 10.03.2016.
 */
public class MapFragment extends Fragment implements ClusterManager.OnClusterItemInfoWindowClickListener<AppClusterItem> {
    private ClusterManager<AppClusterItem> mClusterManager;
    private AppClusterItem clickedClusterItem;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, container,
                false);
        MapView mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMap = mMapView.getMap();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            setUpMap();
        }
    }

    private void setUpMap() {

        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mClusterManager = new ClusterManager<>(getActivity(), mMap);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.779977, -122.413742), 10));

        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());

        mMap.setOnInfoWindowClickListener(mClusterManager); //added
        mClusterManager.setOnClusterItemInfoWindowClickListener(this); //added

        mClusterManager
                .setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<AppClusterItem>() {
                    @Override
                    public boolean onClusterItemClick(AppClusterItem item) {
                        clickedClusterItem = item;
                        return false;
                    }
                });
        addItems();

        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(
                new MyCustomAdapterForItems());

    }
    private void addItems() {

        double latitude = 37.779977;
        double longitude = -122.413742;
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;

            double lat = latitude + offset;
            double lng = longitude + offset;
            AppClusterItem offsetItem = new AppClusterItem(lat, lng, "title " + i+1, "snippet " + i+1);
            mClusterManager.addItem(offsetItem);
        }
    }

    @Override
    public void onClusterItemInfoWindowClick(AppClusterItem appClusterItem) {

        //Cluster item InfoWindow clicked, set title as action
        Intent i = new Intent(getActivity(), ClusterInfoWindow.class);
        i.setAction(appClusterItem.getmTitle());
        startActivity(i);

        //You may want to do different things for each InfoWindow:
        if (appClusterItem.getmTitle().equals("some title")){

            //do something specific to this InfoWindow....

        }

    }

    public class MyCustomAdapterForItems implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        MyCustomAdapterForItems() {
            myContentsView = getActivity().getLayoutInflater().inflate(
                    R.layout.info_window, null);
        }
        @Override
        public View getInfoWindow(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView
                    .findViewById(R.id.txtTitle));
            TextView tvSnippet = ((TextView) myContentsView
                    .findViewById(R.id.txtSnippet));

            tvTitle.setText(clickedClusterItem.getmTitle());
            tvSnippet.setText(clickedClusterItem.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

}