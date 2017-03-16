package adiel.androidinframodule.activities;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by recntrek7 on 15/03/17.
 */

public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Retention(SOURCE)
    @StringDef({ACTIVITY_NOT_CREATED,ACTIVITY_CREATED, ACTIVITY_STARTED, ACTIVITY_RESUMED,
            ACTIVITY_PAUSED,ACTIVITY_STOPPED,ACTIVITY_SAVE_INSTANCE_STATE,ACTIVITY_DESTROYED})
    public @interface ActivityState {}
    public static final String ACTIVITY_NOT_CREATED= "ACTIVITY_NOT_CREATED";
    public static final String ACTIVITY_CREATED= "ACTIVITY_CREATED";
    public static final String ACTIVITY_STARTED= "ACTIVITY_STARTED";
    public static final String ACTIVITY_RESUMED= "ACTIVITY_RESUMED";
    public static final String ACTIVITY_PAUSED= "ACTIVITY_PAUSED";
    public static final String ACTIVITY_STOPPED= "ACTIVITY_STOPPED";
    public static final String ACTIVITY_SAVE_INSTANCE_STATE= "ACTIVITY_SAVE_INSTANCE_STATE";
    public static final String ACTIVITY_DESTROYED= "ACTIVITY_DESTROYED";


    HashMap<String,String> activityStateMap ;
    public MyActivityLifecycleCallbacks(ArrayList<String> activitiesNames){
        activityStateMap = new HashMap<>();
        for(String activityName : activitiesNames){
            activityStateMap.put(activityName,ACTIVITY_NOT_CREATED);
        }
    }

    public @ActivityState String getActivityState(String activityName){
        @ActivityState String state = activityStateMap.get(activityName);
        return state;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityStateMap.put(activity.getClass().getName(),ACTIVITY_CREATED);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityStateMap.put(activity.getClass().getName(),ACTIVITY_STARTED);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        activityStateMap.put(activity.getClass().getName(),ACTIVITY_RESUMED);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        activityStateMap.put(activity.getClass().getName(),ACTIVITY_PAUSED);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityStateMap.put(activity.getClass().getName(),ACTIVITY_STOPPED);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        activityStateMap.put(activity.getClass().getName(),ACTIVITY_SAVE_INSTANCE_STATE);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityStateMap.put(activity.getClass().getName(),ACTIVITY_DESTROYED);
    }


}
