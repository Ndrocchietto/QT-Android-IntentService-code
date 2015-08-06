package com.mamlambo.intentservicebasics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
* Created by ivano on 5/31/2015.
*/
public class ResponseReceiver extends BroadcastReceiver {
    public static final String ACTION_RESP = "com.mamlambo.intent.action.piirlED";
    private IntentServiceBasicsActivity intentServiceBasicsActivity;

    public ResponseReceiver(IntentServiceBasicsActivity intentServiceBasicsActivity) {
        this.intentServiceBasicsActivity = intentServiceBasicsActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
        Log.d("com.mamlambo.intentservicebasics.ResponseReceiver", "onReceive (line 23): ");
        // Update UI, new "message" processed by SimpleIntentService
        TextView result = (TextView) intentServiceBasicsActivity.findViewById(R.id.txt_result);
        String text = intent.getStringExtra(SimpleIntentService.PARAM_OUT_MSG);
        result.setText(text);
    }

}
