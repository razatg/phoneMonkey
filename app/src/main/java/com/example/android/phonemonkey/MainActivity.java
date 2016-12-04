package com.example.android.phonemonkey;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView numTextUnlocked;
    TextView calenderText;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView numTextUnlocked = (TextView) findViewById(R.id.numTextUnlocked);
        TextView calenderText = (TextView) findViewById(R.id.calenderText);

        SharedPreferences prefs = getSharedPreferences("UnlockSharedPref", MODE_PRIVATE);
        int numCount = prefs.getInt("numUnlock", 1);
        String currDate = prefs.getString("numUnlockTimeStamp", "Till Now");

        Log.v("###c", String.valueOf(numCount));
        numTextUnlocked.setText(String.valueOf(numCount));
        calenderText.setText("Unlocks till.. "+currDate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView numTextUnlocked = (TextView) findViewById(R.id.numTextUnlocked);
        TextView calenderText = (TextView) findViewById(R.id.calenderText);

        SharedPreferences prefs = getSharedPreferences("UnlockSharedPref", MODE_PRIVATE);
        int numCount = prefs.getInt("numUnlock", 0);
        String currDate = prefs.getString("numUnlockTimeStamp", "Till Now");

        Log.v("###r", String.valueOf(numCount));
        numTextUnlocked.setText(String.valueOf(numCount));
        calenderText.setText("Unlocks till.. "+currDate);
    }
}
