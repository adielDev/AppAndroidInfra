package adiel.androidinframodule.activities;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by recntrek7 on 15/03/17.
 */

public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    @Retention(SOURCE)
    @IntDef({ACTIVITY_NOT_CREATED,ACTIVITY_CREATED, ACTIVITY_STARTED, ACTIVITY_RESUMED,
            ACTIVITY_PAUSED,ACTIVITY_STOPPED,ACTIVITY_SAVE_INSTANCE_STATE,ACTIVITY_DESTROYED})
    public @interface ActivityState {}
    public static final int ACTIVITY_NOT_CREATED= -1;
    public static final int ACTIVITY_CREATED= 0;
    public static final int ACTIVITY_STARTED= 1;
    public static final int ACTIVITY_RESUMED= 2;
    public static final int ACTIVITY_PAUSED= 3;
    public static final int ACTIVITY_STOPPED= 4;
    public static final int ACTIVITY_SAVE_INSTANCE_STATE= 5;
    public static final int ACTIVITY_DESTROYED= 6;


    HashMap<Class,Integer> activityStateMap ;
    public MyActivityLifecycleCallbacks(ArrayList<Class> activities){
        activityStateMap = new HashMap<>();
        for(Class activity : activities){
            activityStateMap.put(activity,-1);
        }
    }

    public int getActivityState(Class activity){
        return activityStateMap.get(activity);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityStateMap.put(activity.getClass(),ACTIVITY_CREATED);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityStateMap.put(activity.getClass(),ACTIVITY_STARTED);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        activityStateMap.put(activity.getClass(),ACTIVITY_RESUMED);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        activityStateMap.put(activity.getClass(),ACTIVITY_PAUSED);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityStateMap.put(activity.getClass(),ACTIVITY_STOPPED);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        activityStateMap.put(activity.getClass(),ACTIVITY_SAVE_INSTANCE_STATE);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityStateMap.put(activity.getClass(),ACTIVITY_DESTROYED);
    }
}
