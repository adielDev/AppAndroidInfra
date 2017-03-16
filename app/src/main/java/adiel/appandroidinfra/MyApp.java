package adiel.appandroidinfra;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Map;

import adiel.androidinframodule.activities.MyActivityLifecycleCallbacks;

import static adiel.androidinframodule.utils.MyUtils.getHashMapResource;

/**
 * Created by recntrek7 on 15/03/17.
 */

public class MyApp extends Application{
    private MyActivityLifecycleCallbacks myActivityLifecycleCallbacks;
    private Map<String,String> serviceMap;
    @Override
    public void onCreate() {
        super.onCreate();
        myActivityLifecycleCallbacks = new MyActivityLifecycleCallbacks(getActivitiesNames());
        serviceMap= getHashMapResource(getApplicationContext(), R.xml.gate_server_confgs);
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
    public Map<String,String> getServiceMap(){
        return serviceMap;
    }

}
