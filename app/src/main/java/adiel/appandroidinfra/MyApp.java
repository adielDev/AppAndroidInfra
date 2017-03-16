package adiel.appandroidinfra;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;

import adiel.androidinframodule.activities.MyActivityLifecycleCallbacks;

/**
 * Created by recntrek7 on 15/03/17.
 */

public class MyApp extends Application{
    private MyActivityLifecycleCallbacks myActivityLifecycleCallbacks;
    @Override
    public void onCreate() {
        super.onCreate();
        myActivityLifecycleCallbacks = new MyActivityLifecycleCallbacks(getActivitiesNames());
        registerActivityLifecycleCallbacks(myActivityLifecycleCallbacks);
    }
    private ArrayList<String> getActivitiesNames(){
        ArrayList<String> activitiesNames = new ArrayList<>();
        activitiesNames.add(MainActivity.class.getName());
        return activitiesNames;
    }
    public @MyActivityLifecycleCallbacks.ActivityState String getActivityState(String activityName){
        return myActivityLifecycleCallbacks.getActivityState(activityName);
    }

}
