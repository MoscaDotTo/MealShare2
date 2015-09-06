package pennapps2015.mealshare;


import android.content.Intent;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.parse.FindCallback;
import android.location.Location;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;
import java.util.logging.Handler;

import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final globals gVars = globals.getInstance();
        gVars.setUSN(Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID));
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        TextView txtOut = (TextView) findViewById(R.id.txtOut);
        Button btnMkPost = (Button) findViewById(R.id.btnMkPost);
        btnMkPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Submit.class);
                startActivity(intent);
            }});

        Button showPosts = (Button) findViewById(R.id.btnMine);
        showPosts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                globals locTemp = globals.getInstance();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("entry");
                query.whereWithinMiles("pinPoint", locTemp.getCurrentLocation(), 1.0);
                query.orderByDescending("createdAt");
                query.findInBackground(
                        new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> entryList, com.parse.ParseException e) {
                                if (e != null) {
                                    Log.d("out:", "error" + e.getMessage());
                                } else {
                                    final ListView lvout = (ListView)findViewById(R.id.lvOut);
                                    lvDataParser lvData = new lvDataParser();
                                    List<lvDataParser> lvDataList = lvData.getLvData(entryList);
                                    ListViewAdapter adapter = new ListViewAdapter(lvDataList);
                                    lvout.setAdapter(adapter);
                                }
                            }
                        });
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
        updateLV(globals.getInstance().getCurrentLocation());
    }


    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            // Blank for a moment...
        }
        else {
            handleNewLocation(location);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void updateLV(ParseGeoPoint location) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("entry");
        query.whereWithinMiles("pinPoint",location, 1.0);
        query.orderByDescending("createdAt");
        query.findInBackground(
                new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> entryList, com.parse.ParseException e) {
                        if (e != null) {
                            Log.d("out:", "error" + e.getMessage());
                        } else {
                            final ListView lvout = (ListView) findViewById(R.id.lvOut);
                            lvDataParser lvData = new lvDataParser();
                            List<lvDataParser> lvDataList = lvData.getLvData(entryList);
                            ListViewAdapter adapter = new ListViewAdapter(lvDataList);
                            lvout.setAdapter(adapter);
                        }
                    }
                });
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
        globals locTemp = globals.getInstance();
        locTemp.setCurrentLocation(location);
        updateLV(locTemp.getCurrentLocation());
    }



}
