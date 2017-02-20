package com.example.gustavo.taskmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gustavo.taskmanager.model.Task;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements LocationListener {
    static int position;
    static Location loc;
    public final int LOCATION_REFRESH_TIME = 5000;
    public final int LOCATION_REFRESH_DISTANCE = 0;
    ListAdapter adaptador;
    Task[] tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRepeatingAsyncTask();
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, this);
        } catch (SecurityException s) {
            s.printStackTrace();
        }
    }

    public void onClickHandler(View view) {
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        position = listView.getPositionForView(parentRow);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            System.out.println(imageBitmap.getWidth());
            new UploadTask(this).execute(imageBitmap);
            new HttpRequestTask(this).execute();
        }
    }

    private void setRepeatingAsyncTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            new HttpRequestTask(MainActivity.this).execute();

                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 10 * 1000);  // interval of one minute

    }

    @Override
    public void onLocationChanged(Location location) {
        DecimalFormat df = new DecimalFormat("#.00");
        String msg = "New Latitude: " + df.format(location.getLatitude())
                + " New Longitude: " + df.format(location.getLongitude());

        System.out.println(msg);
    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        System.out.println("Gps is turned off!! " +
                Toast.LENGTH_SHORT);
    }

    @Override
    public void onProviderEnabled(String provider) {

        System.out.println("Gps is turned on!! " +
                Toast.LENGTH_SHORT);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

}
