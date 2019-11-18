package com.example.bid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Extendet_settings extends AppCompatActivity {
    EditText timeBetweenSending;
    View v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences prefs = this.getSharedPreferences(
                "com.example.bid", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extendet_settings);
        timeBetweenSending=(EditText)findViewById(R.id.editText);
        timeBetweenSending.setText(prefs.getString("sendtime",null));
        v2=(View)findViewById(R.id.view2);
        final Intent intent = new Intent(this, Settings.class);




            v2.setOnTouchListener(new OnSwipeTouchListener(Extendet_settings.this) {
                public void onSwipeRight() {
                    if(Integer.parseInt(timeBetweenSending.getText().toString())>=5) {
                    Log.i("lol",timeBetweenSending.getText().toString());
                    System.out.println("haahahahah");
                    System.out.println(timeBetweenSending.getText().toString());
                    startActivity(intent);
                    editor.putString("sendtime", timeBetweenSending.getText().toString());
                    editor.apply();
                    }else{
                         Toast.makeText(getApplicationContext(),"The number must be greater than 5",Toast.LENGTH_SHORT).show();
                    }
                }

            });

    }

}
