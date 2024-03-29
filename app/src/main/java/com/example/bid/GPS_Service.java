package com.example.bid;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class GPS_Service extends Service {
    private LocationListener listener;
    private LocationManager locationManager;

   public int onStartCommand(Intent intent, int flags, int startId) {


       return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        final SharedPreferences prefs = this.getSharedPreferences(
                "com.example.bid", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("p","zap");

                editor.putString("latitude",""+location.getLatitude());
                editor.putString("longitude",""+location.getLongitude());
                editor.apply();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Log.i("p","zapping");
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0,listener);

    }

    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
        if(locationManager != null){
            locationManager.removeUpdates(listener);
        }


    }
}