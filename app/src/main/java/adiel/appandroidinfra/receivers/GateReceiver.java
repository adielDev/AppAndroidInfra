package adiel.appandroidinfra.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import adiel.appandroidinfra.MyApp;
import adiel.appandroidinfra.activities.ExampleActivity;

public class GateReceiver extends BroadcastReceiver {
    public GateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("adiel","GateReceiver onReceive ");
        MyApp myApp = (MyApp) context.getApplicationContext();
        String activityState = myApp.getActivityState(ExampleActivity.class.getName());
        Log.d("adiel","activityState "+activityState);
    }
}
