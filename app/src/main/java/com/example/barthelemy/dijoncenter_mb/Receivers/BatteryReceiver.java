package com.example.barthelemy.dijoncenter_mb.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.barthelemy.dijoncenter_mb.MainActivity;

/**
 * Created by Max on 25/10/2017.
 */

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Batterie Faible", Toast.LENGTH_SHORT).show();

    }
}
