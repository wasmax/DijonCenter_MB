package com.example.barthelemy.dijoncenter_mb.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.barthelemy.dijoncenter_mb.Adapters.PoisAdapter;

/**
 * Created by Max on 25/10/2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                Toast.makeText(context, "Sms reçu !", Toast.LENGTH_SHORT).show();

                String msgData = smsMessage.getMessageBody();

                if(msgData.contains("Fiche:")){
                    String id = msgData.split("Fiche:")[1];

                    PoisAdapter pa = new PoisAdapter();
                    Boolean isRealPoi = pa.IsPoiExisting(id);

                    if(isRealPoi){

                    }
                }
            }
        }
    }
}
