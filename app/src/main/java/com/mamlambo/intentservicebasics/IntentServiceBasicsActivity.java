package com.mamlambo.intentservicebasics;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IntentServiceBasicsActivity extends Activity {
    /** Called when the activity is first created. */
    
    private ResponseReceiver receiver;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver(this);
        Log.d("com.mamlambo.intentservicebasics.IntentServiceBasicsActivity", "onCreate (line 26): ");
        registerReceiver(receiver, filter);
        Log.d("com.mamlambo.intentservicebasics.IntentServiceBasicsActivity", "onCreate (line 29): ");
                
    }
    
    @Override
    public void onDestroy() {
        this.unregisterReceiver(receiver);
        super.onDestroy();
    }

    public void myServiceButtonClickHandler(View target) {
        // Launch an intent service to do some async work

        EditText input = (EditText) findViewById(R.id.txt_input);
        String strInputMsg = input.getText().toString();
Log.d("com.mamlambo.intentservicebasics.IntentServiceBasicsActivity", "myServiceButtonClickHandler (line 44): ");

        Intent msgIntent = new Intent(this, SimpleIntentService.class);
        msgIntent.putExtra(SimpleIntentService.PARAM_IN_MSG, strInputMsg);
        Toast.makeText(this, "onClick", Toast.LENGTH_LONG).show();
        startService(msgIntent);

    }

    public void myBadButtonClickHandler(View target) {

        // Do "lots" of work on the main thread
        EditText input = (EditText) findViewById(R.id.txt_input);
        String strInputMsg = input.getText().toString();

        SystemClock.sleep(1000); // 30 seconds, makes app unresponsive, does not queue up, bad developer, no donut

        TextView result = (TextView) findViewById(R.id.txt_result);
        result.setText(strInputMsg + " " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
    }

}