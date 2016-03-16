package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.googleMaps.clustering;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Dimitar Danailov on 6/3/15.
 * email: dimityr.danailov@gmail.com
 *
 * Documentation: https://developers.google.com/maps/documentation/android/utility/marker-clustering
 */
public class AppClusterItem implements ClusterItem {

    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;


    public AppClusterItem(double latitude, double longitude,String t, String s) {
        mPosition = new LatLng(latitude, longitude);
        mTitle = t;
        mSnippet = s;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getSnippet(){
        return mSnippet;
    }



}
