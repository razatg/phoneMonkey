package com.example.android.phonemonkey;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CheckBox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by MMT4828 on 10/2/2016.
 */
public class PhoneUnlockReceiver extends BroadcastReceiver {
    int numberUnlock;

    String currDate;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            numberUnlock =context.getSharedPreferences("UnlockSharedPref", Context.MODE_PRIVATE).getInt("numUnlock",1);
            currDate = context.getSharedPreferences("UnlockSharedPref", Context.MODE_PRIVATE).getString("numUnlockTimeStamp",getDateTime());
            SharedPreferences.Editor editor = context.getSharedPreferences("UnlockSharedPref", Context.MODE_PRIVATE).edit();
            if (dateFormatter(currDate).compareTo(dateFormatter(getDateTime()))<0){
                editor.putInt("numUnlock", 1);
            }else {
                editor.putInt("numUnlock", ++numberUnlock);
            }
            editor.putString("numUnlockTimeStamp", getDateTime());
            editor.commit();

            Log.v("##u", String.valueOf(numberUnlock));
            Log.v("##d", currDate);
        }

    }

    private String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Date dateFormatter(String date) {
        SimpleDateFormat tillYearFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date dateD = null;
        try {
            dateD = tillYearFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateD;
    }

}
