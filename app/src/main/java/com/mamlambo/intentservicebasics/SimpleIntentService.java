package com.mamlambo.intentservicebasics;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class SimpleIntentService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
Log.d("com.mamlambo.intentservicebasics.SimpleIntentService", "onHandleIntent (line 20): ");
        String msg = intent.getStringExtra(PARAM_IN_MSG);
        //this will not be recognized as it is a process in background!
        Toast.makeText(this, "onHandleIntent", Toast.LENGTH_LONG).show();
        Log.d("com.mamlambo.intentservicebasics.SimpleIntentService", "onHandleIntent (line 23): ");
        SystemClock.sleep(1000); // 30 seconds
        String resultTxt = msg + " "
            + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
        Log.v("SimpleIntentService", "Handling msg: " + resultTxt);

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
        sendBroadcast(broadcastIntent);
    }

}
