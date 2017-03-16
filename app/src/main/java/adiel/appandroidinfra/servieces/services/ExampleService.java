package adiel.appandroidinfra.servieces.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import adiel.androidinframodule.activities.MyActivityLifecycleCallbacks;
import adiel.appandroidinfra.MyApp;
import adiel.appandroidinfra.activities.ExampleActivity;
import adiel.appandroidinfra.receivers.ReceiverConst;
import adiel.appandroidinfra.servieces.DTOS.ExampleDto;
import adiel.appandroidinfra.servieces.interfaces.GateServerDto;

public class ExampleService implements IExampleService{
    public static final String RESULT="result";

    ExampleDto dto;

    @Override
    public void setDto(GateServerDto dto){
        this.dto = (ExampleDto) dto;
    }

    @Override
    public void execute(Context context) {
        Log.d("adiel",dto.toString());
        MyApp myApp = (MyApp) context.getApplicationContext();
        String activityState = myApp.getActivityState(ExampleActivity.class.getName());
        if(activityState== MyActivityLifecycleCallbacks.ACTIVITY_RESUMED) {
            Intent intent = new Intent(ReceiverConst.LBActions.ExampleAction);
            intent.putExtra(RESULT, dto);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
