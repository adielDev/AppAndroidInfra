package adiel.appandroidinfra.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import adiel.appandroidinfra.R;
import adiel.appandroidinfra.receivers.GateReceiver;
import adiel.appandroidinfra.receivers.ReceiverConst;

public class ExampleActivity extends AppCompatActivity {


    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        tv1 = (TextView) findViewById(R.id.tv1);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent(this,GateReceiver.class);
        sendBroadcast(intent);
    }
}
