package com.example.bid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean first_opening=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.bid", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        first_opening=prefs.getBoolean("firstOpening",true);
        if(first_opening==true)
        {
            setContentView(R.layout.activity_main);
            first_opening=false;
            editor.putBoolean("firstOpening",first_opening);
            editor.apply();
        }else
        {
            setContentView(R.layout.activity_main__window);
        }

    }
    public void onClick(View v){
        Intent intent = new Intent(this, Agreement.class);
        startActivity(intent);

    }

}
